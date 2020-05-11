package shoporswap;

import io.ShopOrSwapRecord;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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

    public Controller() throws IOException{
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
        this.currentUser = this.system.findAccount(this.welcomeLabel.getText().split(": ")[1]);
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

//                this.clientHomeMessagesListView = (ListView) clientHomepageScene.lookup("#clientHomeMessagesListView");
//                ObservableList<String> messageStrings = this.makeMessageObservableList(this.system.findMessagesByRecipient(this.currentUser));
//                this.clientHomeMessagesListView.getItems().addAll(messageStrings);
//                this.clientHomeMessagesHeader = (Label) clientHomepageScene.lookup("#clientHomeMessagesHeader");
//                this.clientHomeMessagesHeader.setText("My Messages: " + messageStrings.size());


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
            this.currentUser = this.system.findAccount(this.welcomeLabel.getText().split(": ")[1]);
            Storefront storefront;
            try {
                storefront = system.addStorefront(typeIn, nameIn, (Client) system.findAccount(this.currentUser.getAccountName()));
                System.out.print("\nStorefront " + storefront.getStorefrontName() + " has been successfully created for products to " + typeIn);
                System.out.println("We did it");
            } catch (IllegalArgumentException e) {
                errorLabel.setText("\nError: " + e.getMessage());
                errorLabel.setVisible(true);
            }
        }
    }

    public void viewMyStorefrontsClicked(ActionEvent event){}

    public void addFundsClicked(ActionEvent event){}

    public void viewMessagesClicked(ActionEvent event){}

    public void viewMyProductsClicked(ActionEvent event){}

    public void postProductClicked(ActionEvent event){}


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

        //this.current User gives an error, not sure why.

//        this.welcomeLabel = (Label) homepageScene.lookup("#welcomeLabel");
//        this.welcomeLabel.setText("WELCOME: " + this.currentUser.getAccountName());
//
//        this.accountRatingLabel.setText("Account rating: " + ((Client) this.currentUser).calculateRating());
//
//        this.myFundsLabel.setText("Account funds: " + ((Client) this.currentUser).getWallet());


    }


    public void signOut(ActionEvent event) throws IOException{
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
            JsonUtil.toJsonFile(dataFileName, new ShopOrSwapRecord(this.system));
            System.out.println("Data Export Success");
            System.exit(0);
        }catch(Exception e){
            System.exit(1);
        }
    }
}
