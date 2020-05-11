package shoporswap;

import io.ShopOrSwapRecord;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import util.JsonUtil;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Controller {

    // sign in items
    @FXML Button signInButton;
    @FXML private TextField signInAccountName;
    @FXML private PasswordField signInPassword;
    @FXML private Label signInInvalidLabel;

    // home page items
    @FXML private Button viewStorefrontsClicked;
    @FXML private Label welcomeLabel;
    @FXML private Label accountRatingLabel;
    @FXML private Label errorLabel;
    @FXML private Label myFundsLabel;
    @FXML private TextField createStorefrontTxtFld;
    @FXML private RadioButton rbSell;
    @FXML private RadioButton rbSwap;

    // post product items
    @FXML private TextField productNametxtFld;
    @FXML private TextField productPriceTxtFld;
    @FXML private TextArea productDescriptionTxtArea;
    @FXML private TextField productTagsTxtFld;
    @FXML private Label errorLabel1;
    @FXML private Label successLabel1;

    // client home page items
    @FXML private Label clientHomeTitle;
    @FXML private Label clientHomeAccountInfo;

    @FXML private ListView clientHomeStorefrontsListView;
    @FXML private Label clientHomeStorefrontsHeader;

    @FXML private Label clientHomeGoToStorefrontNameLabel;
    @FXML private Label clientHomeGoToStorefrontOwnerLabel;
    @FXML private Button clientHomeGoToStorefrontButton;

    @FXML private ListView clientHomeMessagesListView;
    @FXML private Label clientHomeMessagesHeader;

    private final String dataFileName = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "systemData.json";
    private ShopOrSwap system;
    private Account currentUser;

    private Storefront selectedStorefront;
    private Account selectedUser;

    private static Controller instance;


    private Controller(Account currentUser) {
        this.currentUser=currentUser;
    }

    public static Controller getInstance(Account currentUser) {
        if(instance == null) {
            instance = new Controller(currentUser);
        }
        return instance;
    }

    public Account getCurrentUser() {
        return currentUser;
    }

    public void cleanInstance() {
        currentUser = null;
    }


    public Controller() throws IOException {
        try {
            this.system = JsonUtil.fromJsonFile(dataFileName, ShopOrSwapRecord.class).toShopOrSwap();
        }catch(Exception e){
            this.system = new ShopOrSwap();
            this.system.addAccount(new Admin("admin1", "admin1"));
            JsonUtil.toJsonFile(this.dataFileName, new ShopOrSwapRecord(this.system));
            this.system = JsonUtil.fromJsonFile(this.dataFileName, ShopOrSwapRecord.class).toShopOrSwap();
        }
    }


    public void signIn(ActionEvent event) throws IOException {
        Account attemptedSignInAccount = this.system.signIn(this.signInAccountName.getText(), this.signInPassword.getText());
        if (attemptedSignInAccount == null) {
            this.signInInvalidLabel.setVisible(true);
        } else {
            Controller.getInstance(attemptedSignInAccount);
            this.currentUser = attemptedSignInAccount;
            if (this.currentUser.getClass().getName().contains("Client")) {
                Parent homepage = FXMLLoader.load(getClass().getResource("/homepage2.fxml"));
            Scene homepageScene = new Scene(homepage, 600, 400);
            Stage homepageWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            homepageWindow.setTitle("Home: " + this.currentUser.getAccountName());
            homepageWindow.setScene(homepageScene);
            homepageWindow.setResizable(false);
            homepageWindow.show();

            this.welcomeLabel = (Label) homepageScene.lookup("#welcomeLabel");
            this.welcomeLabel.setText("WELCOME: " + this.currentUser.getAccountName());

            this.accountRatingLabel = (Label) homepageScene.lookup("#accountRatingLabel");
            this.accountRatingLabel.setText("Account rating: " + ((Client) this.currentUser).calculateRating());

            this.myFundsLabel = (Label) homepageScene.lookup("#myFundsLabel");
            this.myFundsLabel.setText("Account funds: " + ((Client) this.currentUser).getWallet());
        }

        }

    }

    public void createAccount(ActionEvent event) throws IOException{
        Account attemptedMakeAccount;
        try{
            attemptedMakeAccount = this.system.findAccount(this.signInAccountName.getText());
            this.signInInvalidLabel.setText("Account name is already taken.");
            this.signInInvalidLabel.setTextFill(Color.web("#FF0000"));
            this.signInInvalidLabel.setVisible(true);
        }catch(Exception e){
            try {
                attemptedMakeAccount = this.system.addAccount("Client", this.signInAccountName.getText(), this.signInPassword.getText());
                this.signInInvalidLabel.setText("Account created successfully. Please sign in using your new credentials.");
                this.signInInvalidLabel.setTextFill(Color.web("#00FF00"));
                this.signInInvalidLabel.setVisible(true);
                JsonUtil.toJsonFile(this.dataFileName, new ShopOrSwapRecord(this.system));
                this.system = JsonUtil.fromJsonFile(this.dataFileName, ShopOrSwapRecord.class).toShopOrSwap();
            }catch(Exception e2){
                this.signInInvalidLabel.setText("Cannot create account with credentials.");
                this.signInInvalidLabel.setTextFill(Color.web("#FF0000"));
                this.signInInvalidLabel.setVisible(true);
            }
        }
        this.signInAccountName.setText("");
        this.signInPassword.setText("");
    }
    public void viewStorefrontsClicked(ActionEvent event)throws IOException{
        Parent clientHomepage = FXMLLoader.load(getClass().getResource("/clientHome2.fxml"));
        Scene clientHomepageScene = new Scene(clientHomepage, 600, 400);
        Stage clientHomepageWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        clientHomepageWindow.setScene(clientHomepageScene);
        clientHomepageWindow.setResizable(false);

        this.clientHomeStorefrontsListView = (ListView) clientHomepageScene.lookup("#clientHomeStorefrontsListView");
        ObservableList<String> storefrontStrings = this.makeStorefrontObservableList(this.system.findAllStorefronts());
        this.clientHomeStorefrontsListView.getItems().addAll(storefrontStrings);
        this.clientHomeStorefrontsHeader = (Label) clientHomepageScene.lookup("#clientHomeStorefrontsHeader");
        this.clientHomeStorefrontsHeader.setText("All Storefronts: " + storefrontStrings.size());

        this.clientHomeStorefrontsListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>(){
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue, String old_val, String new_val) {
                        Controller.this.clientHomeGoToStorefrontNameLabel = (Label) clientHomepageScene.lookup("#clientHomeGoToStorefrontNameLabel");
                        Controller.this.clientHomeGoToStorefrontOwnerLabel = (Label) clientHomepageScene.lookup("#clientHomeGoToStorefrontOwnerLabel");
                        String[] recordStringComponents = ((String) clientHomeStorefrontsListView.getSelectionModel().getSelectedItem()).split(":");
                        String storefrontOwner = recordStringComponents[0].trim();
                        String storefrontName = recordStringComponents[1].trim();
                        Controller.this.clientHomeGoToStorefrontNameLabel.setText(storefrontName);
                        Controller.this.selectedUser = Controller.this.system.findAccount(storefrontOwner);
                        Controller.this.clientHomeGoToStorefrontOwnerLabel.setText(storefrontOwner);
                        Controller.this.selectedStorefront = Controller.this.system.findStorefront(storefrontName, (Client) Controller.this.selectedUser);
                    }
                }
        );

        clientHomepageWindow.show();

    }
    public void createStorefront(ActionEvent event){
        errorLabel.setVisible(false);
        String nameIn = createStorefrontTxtFld.getText();
        String typeIn ="";
        if(rbSell.isSelected()){
            typeIn ="Sell";
        }
        else if(rbSwap.isSelected()){
            typeIn ="Swap";
        }
        else{
            errorLabel.setText("Error: Please select a type for the new storefront");
            errorLabel.setVisible(true);
        }
        if(rbSell.isSelected() || rbSwap.isSelected()) {
            Storefront storefront;
            try {
                storefront = system.addStorefront(typeIn, nameIn, (Client) system.findAccount(instance.getCurrentUser()));
                System.out.print("\nStorefront " + storefront.getStorefrontName() + " has been successfully created for products to " + typeIn);
            } catch (IllegalArgumentException e) {
                errorLabel.setText("\nError: " + e.getMessage());
                errorLabel.setVisible(true);
            }
        }
    }
    public void postProductToStorefront(ActionEvent event){
        System.out.println(instance.selectedStorefront.getClass());
        System.out.println(instance.productNametxtFld.getText());
        System.out.println(instance.productDescriptionTxtArea.getText());
        System.out.println(instance.productPriceTxtFld.getText());
        AbstractProduct productAdded = instance.system.addToStorefront(
            instance.productNametxtFld.getText(),
            instance.productDescriptionTxtArea.getText(),
            Double.parseDouble(instance.productPriceTxtFld.getText()),
            instance.selectedStorefront
        );
        instance.successLabel1.setText("Product " + productAdded.getProductName() + " has been successfully added to " + instance.selectedStorefront.getStorefrontName());
        instance.successLabel1.setVisible(true);

        this.system = instance.system;

    }

    public void viewMyStorefrontsClicked(ActionEvent event){}

    public void addFundsClicked(ActionEvent event){}

    public void viewMessagesClicked(ActionEvent event){}

    public void viewMyProductsClicked(ActionEvent event){}

    public void postProductClicked(ActionEvent event)throws IOException{
        Parent postProductPage = FXMLLoader.load(getClass().getResource("/postProduct.fxml"));
        Scene postProductScene = new Scene(postProductPage, 600, 400);
        Stage postProductWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        postProductWindow.setScene(postProductScene);
        postProductWindow.setResizable(false);

        this.clientHomeStorefrontsListView = (ListView) postProductScene.lookup("#clientHomeStorefrontsListView");
        ObservableList<String> storefrontStrings = this.makeStorefrontObservableList(this.system.findAllStorefronts());
        this.clientHomeStorefrontsListView.getItems().addAll(storefrontStrings);

        this.clientHomeStorefrontsListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>(){
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue, String old_val, String new_val) {
                        Controller.this.clientHomeGoToStorefrontNameLabel = (Label) postProductScene.lookup("#clientHomeGoToStorefrontNameLabel");
                        Controller.this.clientHomeGoToStorefrontOwnerLabel = (Label) postProductScene.lookup("#clientHomeGoToStorefrontOwnerLabel");
                        String[] recordStringComponents = ((String) clientHomeStorefrontsListView.getSelectionModel().getSelectedItem()).split(":");
                        String storefrontOwner = recordStringComponents[0].trim();
                        String storefrontName = recordStringComponents[1].trim();
                        Controller.this.clientHomeGoToStorefrontNameLabel.setText(storefrontName);
                        Controller.this.selectedUser = Controller.this.system.findAccount(storefrontOwner);
                        Controller.this.clientHomeGoToStorefrontOwnerLabel.setText(storefrontOwner);
                        Controller.this.selectedStorefront = Controller.this.system.findStorefront(storefrontName, (Client) Controller.this.selectedUser);
                        instance.selectedStorefront = Controller.this.selectedStorefront;
                    }
                }
        );

        instance.productNametxtFld = (TextField) postProductScene.lookup("#productNametxtFld");
        instance.productPriceTxtFld = (TextField) postProductScene.lookup("#productPriceTxtFld");
        instance.productDescriptionTxtArea = (TextArea) postProductScene.lookup("#productDescriptionTxtArea");
        instance.productTagsTxtFld = (TextField) postProductScene.lookup("#productTagsTxtFld");
        instance.clientHomeGoToStorefrontButton = (Button) postProductScene.lookup("#clientHomeGoToStorefrontButton");
        instance.rbSell = (RadioButton) postProductScene.lookup("#rbSell");
        instance.rbSwap = (RadioButton) postProductScene.lookup("#rbSwap");
        instance.errorLabel1 = (Label) postProductScene.lookup("#errorLabel1");
        instance.successLabel1 = (Label) postProductScene.lookup("#successLabel");
        instance.system = this.system;

        postProductWindow.show();
    }


    private ObservableList<String> makeStorefrontObservableList(List<Storefront> storefrontListIn){
        ObservableList<String> observationsOut = FXCollections.<String>observableArrayList();
        for(Storefront storefront : storefrontListIn){
            observationsOut.add(storefront.retrieveStorefrontOwner().getAccountName() + ": " + storefront.getStorefrontName());
        }
        return observationsOut.sorted();
    }

    private ObservableList<String> makeMessageObservableList(List<AbstractMessage> messageListIn){
        ObservableList<String> observationsOut = FXCollections.<String>observableArrayList();
        String messageRecordString;
        for(AbstractMessage message : messageListIn){
            messageRecordString = "From: " + message.getSender() + "\n\tSubject: " + message.getSubject() + "\n\tContent: " + message.getContent();
            observationsOut.add(messageRecordString);
        }
        return observationsOut.sorted();
    }

    public void goToStorefront(ActionEvent event){

    }
    public void goToProduct(ActionEvent event){

    }
    public void back(ActionEvent event)throws IOException{

        Parent homepage = FXMLLoader.load(getClass().getResource("/homepage2.fxml"));
        Scene homepageScene = new Scene(homepage, 600, 400);
        Stage homepageWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        homepageWindow.setScene(homepageScene);
        homepageWindow.setResizable(false);
        homepageWindow.show();

        this.welcomeLabel = (Label) homepageScene.lookup("#welcomeLabel");
        this.welcomeLabel.setText("WELCOME: " + instance.getCurrentUser().getAccountName());

        this.accountRatingLabel = (Label) homepageScene.lookup("#accountRatingLabel");
        this.accountRatingLabel.setText("Account rating: " + ((Client) instance.getCurrentUser()).calculateRating());

        this.myFundsLabel = (Label) homepageScene.lookup("#myFundsLabel");
        this.myFundsLabel.setText("Account funds: " + ((Client) instance.getCurrentUser()).getWallet());
    }

    public void signOut(ActionEvent event) throws IOException{
        instance.cleanInstance();
        Parent signInPage = FXMLLoader.load(getClass().getResource("/signIn.fxml"));
        Scene signInPageScene = new Scene(signInPage, 600, 400);
        Stage signInPageStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        signInPageStage.setTitle("Shop or Swap: Sign In");
        signInPageStage.setScene(signInPageScene);
        signInPageStage.setResizable(false);
        signInPageStage.show();
    }

    public void saveData(ActionEvent event) throws IOException{
        try {
            JsonUtil.toJsonFile(dataFileName, new ShopOrSwapRecord(instance.system));
            System.out.println("Data Export Success");
            System.exit(0);
        }catch(Exception e){
            System.exit(1);
        }
    }

}
