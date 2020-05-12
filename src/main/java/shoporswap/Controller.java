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
import java.util.NoSuchElementException;

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
    @FXML private TextField addFundsTxtFld;
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

    @FXML private ListView clientHomeProductsListView;
    @FXML private Label clientHomeProductsHeader;
    @FXML private Label clientHomeGoToProductOwnerLabel;
    @FXML private Label clientHomeGoToProductNameLabel;

    @FXML private Label productInfoLabel;
    @FXML private Label errorLabel2;
    @FXML private Button acquireProduct;

    @FXML private Label myFundsLabel2;

    // swap product home items
    @FXML private Label instructionLabel;
    @FXML private Button swapConfirm;

    //my client home page items
    @FXML private Button deleteProduct;

    private final String dataFileName = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "systemData.json";
    private ShopOrSwap system;
    private Account currentUser;

    private Storefront selectedStorefront;
    private AbstractProduct selectedProduct;
    private Account selectedUser;

    private Storefront storefrontWant;
    private Storefront storefrontGive;
    private AbstractProduct productWant;
    private AbstractProduct giveProduct;

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
        selectedStorefront = null;
        selectedProduct = null;
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
            if(instance==null) {
                Controller.getInstance(attemptedSignInAccount);
                instance.system = this.system;
                System.out.println("Instance null" +instance.getCurrentUser().getAccountName());
            }
            else{
                instance.currentUser=attemptedSignInAccount;
                System.out.println("Instance not null" +instance.getCurrentUser().getAccountName());
            }
            this.currentUser = attemptedSignInAccount;
            if (this.currentUser.getClass().getName().contains("Client")) {
                Parent homepage = FXMLLoader.load(getClass().getResource("/homepage2.fxml"));
            Scene homepageScene = new Scene(homepage, 600, 400);
            Stage homepageWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            homepageWindow.setTitle("Home: " + instance.getCurrentUser().getAccountName());
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
                this.signInInvalidLabel.setText("Account created successfully.\nPlease sign in using your new credentials.");
                this.signInInvalidLabel.setTextFill(Color.web("#008000"));
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

        this.myFundsLabel2 = (Label) clientHomepageScene.lookup("#myFundsLabel2");
        this.myFundsLabel2.setText("Account funds: " + ((Client) instance.getCurrentUser()).getWallet());

        this.clientHomeStorefrontsListView = (ListView) clientHomepageScene.lookup("#clientHomeStorefrontsListView");
        ObservableList<String> storefrontStrings = this.makeOtherStorefrontObservableList(instance.system.findAllStorefronts());
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
                        Controller.this.selectedUser = Controller.instance.system.findAccount(storefrontOwner);
                        Controller.this.clientHomeGoToStorefrontOwnerLabel.setText(storefrontOwner);
                        instance.selectedStorefront = Controller.instance.system.findStorefront(storefrontName, (Client) Controller.this.selectedUser);
                        Controller.this.selectedStorefront= instance.selectedStorefront;
                        if(instance.selectedStorefront.getClass().getTypeName().contains("Sell")){
                            Controller.this.clientHomeGoToStorefrontOwnerLabel.setText("Type: Sell");
                        }
                        else{
                            Controller.this.clientHomeGoToStorefrontOwnerLabel.setText("Type: Swap");
                        }
                    }
                }
        );

        clientHomepageWindow.show();

    }
    public void goToStorefront(ActionEvent event) {
        //this.clientHomeProductsListView.getItems().removeAll();
        this.clientHomeProductsListView.getItems().clear();

        try {
            ObservableList<String> productStrings = this.makeProductsObservableList(instance.selectedStorefront.getStorefrontProducts());
            this.clientHomeProductsListView.getItems().addAll(productStrings);
            this.clientHomeProductsHeader.setText("Products in storefront: " + productStrings.size());
        } catch (Exception e) {
            this.clientHomeProductsHeader.setText("Select a Storefront First.");
        }


        this.clientHomeProductsListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>(){
                    @Override
                    public void changed(ObservableValue<? extends String> observableValue, String old_val, String new_val) {
                        //Controller.this.clientHomeGoToStorefrontNameLabel = (Label) clientHomepageScene.lookup("#clientHomeGoToStorefrontNameLabel");
                        //Controller.this.clientHomeGoToStorefrontOwnerLabel = (Label) clientHomepageScene.lookup("#clientHomeGoToStorefrontOwnerLabel");
                        String[] recordStringComponents = ((String) clientHomeProductsListView.getSelectionModel().getSelectedItem()).split(":");
                        String productOwner = recordStringComponents[0].trim();
                        String productName = recordStringComponents[1].trim();
                        int productNumber= (clientHomeProductsListView.getSelectionModel().getSelectedIndex());
                        AbstractProduct product=instance.selectedStorefront.getStorefrontProducts().get(productNumber);
                        Controller.this.clientHomeGoToProductNameLabel.setText(productName);
                        Controller.this.clientHomeGoToProductOwnerLabel.setText(productOwner);
                        Controller.this.selectedUser = Controller.instance.system.findAccount(productOwner);
                        //Controller.this.selectedProduct = Controller.instance.system.findStorefront(storefrontName, (Client) Controller.this.selectedUser).getStorefrontProducts().indexOf();
                        Controller.this.selectedProduct= product;
                        instance.selectedProduct=Controller.this.selectedProduct;
                    }
                }
        );

    }

    public void goToProduct(ActionEvent event){
        productInfoLabel.setVisible(true);
        try {
            productInfoLabel.setText("Name:\n" + instance.selectedProduct.getProductName() + "\n\nProduct Description:\n" + instance.selectedProduct.getProductDescription() + "\n\nProduct tags:\n" + instance.selectedProduct.getProductTagStrings() + "\n\nPrice:" + instance.selectedProduct.getProductValue());
            acquireProduct.setVisible(true);
            if (instance.selectedStorefront.getClass().getTypeName().contains("Sell")) {
                acquireProduct.setText("Purchase Product");
            } else {
                acquireProduct.setText("Swap Product for...");
            }
        }catch (Exception e){
            productInfoLabel.setText("No Product Selected");
            acquireProduct.setVisible(false);
            deleteProduct.setVisible(false);
        }
    }
    public void goToProduct2(ActionEvent event){
        productInfoLabel.setVisible(true);
        productInfoLabel.setText("Name:\n" +instance.selectedProduct.getProductName() + "\n\nProduct Description:\n" +instance.selectedProduct.getProductDescription() +"\n\nProduct tags:\n" +instance.selectedProduct.getProductTagStrings() +"\n\nPrice:" +instance.selectedProduct.getProductValue());
        swapConfirm.setVisible(true);

    }
    public void goToProduct3(ActionEvent event){
        try {
            productInfoLabel.setVisible(true);
            productInfoLabel.setText("Name:\n" + instance.selectedProduct.getProductName() + "\n\nProduct Description:\n" + instance.selectedProduct.getProductDescription() + "\n\nProduct tags:\n" + instance.selectedProduct.getProductTagStrings() + "\n\nPrice:" + instance.selectedProduct.getProductValue());
            deleteProduct.setVisible(true);
        }catch(Exception e){
            productInfoLabel.setText("No Product Selected.");
            deleteProduct.setVisible(false);
        }
    }


    public void swapConfirmClicked(ActionEvent event) throws IOException{

        try {
            instance.storefrontGive =instance.selectedStorefront;
            instance.giveProduct = instance.system.findInStorefront(instance.selectedProduct.getProductName(), instance.selectedStorefront);
            errorLabel2.setText("Success! Message sent");
            errorLabel2.setTextFill(Color.web("#008000", 0.8));
            errorLabel2.setVisible(true);
        } catch (Exception e1) {
            System.out.print("\nError: " + e1.getMessage());
            errorLabel2.setText("Error: " + e1.getMessage());
            errorLabel2.setTextFill(Color.web("#FF0000", 0.8));
            errorLabel2.setVisible(true);
        }
        try {
            String subjectIn = "Transaction: " + instance.productWant.getProductName() + " for " + instance.giveProduct.getProductName();
            String contentIn = instance.getCurrentUser().getAccountName() + " has completed a transaction to get " + instance.productWant.getProductName() + " from " + instance.productWant.getProductMerchant().getAccountName() + " by giving " + instance.giveProduct.getProductName();
            instance.system.sendMessage("User", instance.storefrontWant.retrieveStorefrontOwner().getAccountName(), instance.getCurrentUser().getAccountName(), subjectIn, contentIn);
            instance.system.sendMessage("User", instance.getCurrentUser().getAccountName(), instance.storefrontWant.retrieveStorefrontOwner().getAccountName(), subjectIn, contentIn);
            instance.system.swapProducts(instance.storefrontWant, instance.productWant, instance.storefrontGive, instance.giveProduct);
            System.out.print("\nTransaction complete");

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

            this.errorLabel = (Label) homepageScene.lookup("#errorLabel");
            this.errorLabel.setText("Success!");
            this.errorLabel.setVisible(true);

        }catch(Exception e){
            System.out.print("\nError: " + e);
        }
    }
    public void acquireProductClicked(ActionEvent event) throws IOException{
        errorLabel2.setVisible(false);
        if(instance.selectedStorefront.getClass().getName().contains("Sell")) {
            try {
                AbstractProduct buyProduct = instance.system.findInStorefront(instance.selectedProduct.getProductName(), instance.selectedStorefront);
                instance.system.buyProduct(instance.selectedStorefront, (SellProduct) buyProduct, (Client) instance.getCurrentUser());
                String subjectIn = "Transaction: " + buyProduct.getProductName();
                String contentIn = instance.getCurrentUser().getAccountName() + " has completed a transaction to buy " + buyProduct.getProductName() + " from " + buyProduct.getProductMerchant().getAccountName();
                instance.system.sendMessage("User", instance.selectedStorefront.retrieveStorefrontOwner().getAccountName(), instance.currentUser.getAccountName(), subjectIn, contentIn);
                instance.system.sendMessage("User", instance.getCurrentUser().getAccountName(), instance.selectedStorefront.retrieveStorefrontOwner().getAccountName(), subjectIn, contentIn);
                System.out.print("\nTransaction complete");
                errorLabel2.setText("Success!");
                errorLabel2.setTextFill(Color.web("#008000", 0.8));
                errorLabel2.setVisible(true);
            } catch (Exception e1) {
                System.out.print("\nError: " + e1.getMessage());
                errorLabel2.setText("Error: " + e1.getMessage());
                errorLabel2.setTextFill(Color.web("#FF0000", 0.8));
                errorLabel2.setVisible(true);

            }
        }
        else{
            boolean validProduct=false;
            try {
                instance.storefrontWant= instance.selectedStorefront;
                instance.productWant = instance.system.findInStorefront(instance.selectedProduct.getProductName(), instance.selectedStorefront);
                errorLabel2.setText("Success!");
                errorLabel2.setTextFill(Color.web("#008000", 0.8));
                errorLabel2.setVisible(true);
                validProduct=true;
            } catch (Exception e1) {
                System.out.print("\nError: " + e1.getMessage());
                errorLabel2.setText("Error: " + e1.getMessage());
                errorLabel2.setTextFill(Color.web("#FF0000", 0.8));
                errorLabel2.setVisible(true);
            }
            if(validProduct){
                Parent clientHomepage = FXMLLoader.load(getClass().getResource("/swapProductHome.fxml"));
                Scene clientHomepageScene = new Scene(clientHomepage, 600, 400);
                Stage clientHomepageWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                clientHomepageWindow.setScene(clientHomepageScene);
                clientHomepageWindow.setResizable(false);

                this.clientHomeStorefrontsListView = (ListView) clientHomepageScene.lookup("#clientHomeStorefrontsListView");
                ObservableList<String> storefrontStrings = this.makeSwapStorefrontObservableList(instance.system.findStorefronts((Client) instance.currentUser));
                this.clientHomeStorefrontsListView.getItems().addAll(storefrontStrings);
                this.clientHomeStorefrontsHeader = (Label) clientHomepageScene.lookup("#clientHomeStorefrontsHeader");
                this.clientHomeStorefrontsHeader.setText("My Swap Storefronts: " + storefrontStrings.size());


                this.instructionLabel = (Label) clientHomepageScene.lookup("#instructionLabel");
                this.instructionLabel.setText("SELECT THE PRODUCT YOU WISH TO SWAP FOR " + instance.productWant.getProductName());


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
                                Controller.this.selectedUser = Controller.instance.system.findAccount(storefrontOwner);
                                Controller.this.clientHomeGoToStorefrontOwnerLabel.setText(storefrontOwner);
                                Controller.this.selectedStorefront = Controller.instance.system.findStorefront(storefrontName, (Client) Controller.this.selectedUser);
                                instance.selectedStorefront= Controller.this.selectedStorefront;
                                if(instance.selectedStorefront.getClass().getTypeName().contains("Sell")){
                                    Controller.this.clientHomeGoToStorefrontOwnerLabel.setText("Type: Sell");
                                }
                                else{
                                    Controller.this.clientHomeGoToStorefrontOwnerLabel.setText("Type: Swap");
                                }
                            }
                        }
                );
            }

        }

        this.system = instance.system;
        instance.selectedStorefront = null;
        instance.selectedProduct = null;

    }
    public void createStorefront(ActionEvent event){
        errorLabel.setTextFill(Color.web("#FF0000"));
        errorLabel.setVisible(false);
        String nameIn = this.createStorefrontTxtFld.getText();
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
        System.out.println(nameIn);
        System.out.println(typeIn);
        if(rbSell.isSelected() || rbSwap.isSelected()) {
            Storefront storefront;
            try {
                storefront = instance.system.addStorefront(typeIn, nameIn, (Client) instance.system.findAccount(instance.getCurrentUser()));
                //System.out.println(instance.system.findAllStorefronts());
                this.system = instance.system;
                System.out.print("\nStorefront " + storefront.getStorefrontName() + " has been successfully created for products to " + typeIn);
                errorLabel.setText("Storefront \"" + storefront.getStorefrontName() + "\"\nhas been successfully created for\nproducts to " + typeIn);
                errorLabel.setTextFill(Color.web("#008000"));
                errorLabel.setVisible(true);
            } catch (IllegalArgumentException e) {
                errorLabel.setText("\nError: " + e.getMessage());
                errorLabel.setVisible(true);
            }
        }
        this.system = instance.system;
    }
    public void postProductToStorefront(ActionEvent event){
        instance.successLabel1.setVisible(false);
        try {
            System.out.println(instance.selectedStorefront.getClass());
        }catch(Exception e){
            instance.successLabel1.setText("Error: Select a Storefront first.");
            instance.successLabel1.setTextFill(Color.web("#FF0000"));
            instance.successLabel1.setVisible(true);
        }
        System.out.println(instance.productNametxtFld.getText());
        System.out.println(instance.productDescriptionTxtArea.getText());
        System.out.println(instance.productPriceTxtFld.getText());
        try {
            AbstractProduct productAdded = instance.system.addToStorefront(
                    instance.productNametxtFld.getText(),
                    instance.productDescriptionTxtArea.getText(),
                    Double.parseDouble(instance.productPriceTxtFld.getText()),
                    instance.selectedStorefront
            );
            String[] tagsString = instance.productTagsTxtFld.getText().split(",");
            for(String tagString : tagsString){
                instance.system.addTagToProduct(tagString.trim(), productAdded);
            }
            instance.successLabel1.setText("Product " + productAdded.getProductName() + " has been successfully added to " + instance.selectedStorefront.getStorefrontName());
            errorLabel.setTextFill(Color.web("#008000"));
            instance.successLabel1.setVisible(true);
            this.system = instance.system;

        }catch(IllegalArgumentException e){
            instance.successLabel1.setText("Error: Check that a storefront has been selected and all fields have valid input.");
            instance.successLabel1.setTextFill(Color.web("#FF0000"));
            instance.successLabel1.setVisible(true);
        }catch(NullPointerException e2){
            instance.successLabel1.setText("Error: Check that a storefront has been selected and all fields have valid input.");
            instance.successLabel1.setTextFill(Color.web("#FF0000"));
            instance.successLabel1.setVisible(true);
        }

        this.system = instance.system;

    }

    public void viewMyStorefrontsClicked(ActionEvent event)throws IOException{
        Parent clientHomepage = FXMLLoader.load(getClass().getResource("/myClientHome.fxml"));
        Scene clientHomepageScene = new Scene(clientHomepage, 600, 400);
        Stage clientHomepageWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        clientHomepageWindow.setScene(clientHomepageScene);
        clientHomepageWindow.setResizable(false);

        this.clientHomeStorefrontsListView = (ListView) clientHomepageScene.lookup("#clientHomeStorefrontsListView");
        ObservableList<String> storefrontStrings = instance.makeStorefrontObservableList(instance.system.findStorefronts((Client) instance.getCurrentUser()));
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
                        Controller.this.selectedUser = Controller.instance.system.findAccount(storefrontOwner);
                        Controller.this.clientHomeGoToStorefrontOwnerLabel.setText(storefrontOwner);
                        Controller.this.selectedStorefront = Controller.instance.system.findStorefront(storefrontName, (Client) Controller.this.selectedUser);
                        instance.selectedStorefront= Controller.this.selectedStorefront;
                        if(instance.selectedStorefront.getClass().getTypeName().contains("Sell")){
                            Controller.this.clientHomeGoToStorefrontOwnerLabel.setText("Type: Sell");
                        }
                        else{
                            Controller.this.clientHomeGoToStorefrontOwnerLabel.setText("Type: Swap");
                        }
                    }
                }
        );

        clientHomepageWindow.show();
    }
    public void deleteProductClicked(ActionEvent event) {
        errorLabel2.setVisible(false);
        try {
            instance.system.removeFromStorefront(instance.selectedProduct.getProductName(), instance.selectedStorefront);
            errorLabel2.setText("Success! Product Deleted");
            errorLabel2.setTextFill(Color.web("#008000", 0.8));
            errorLabel2.setVisible(true);
            deleteProduct.setVisible(false);
        } catch (NoSuchElementException e) {
            errorLabel2.setText("Error: " + e.getMessage());
            errorLabel2.setTextFill(Color.web("#FF0000", 0.8));
            errorLabel2.setVisible(true);
        }
        this.system = instance.system;
        instance.selectedStorefront = null;
        instance.selectedProduct = null;
        return;

    }

    public void addFundsClicked(ActionEvent event){
        errorLabel.setVisible(false);
        try {
            ((Client) instance.getCurrentUser()).addWallet(Double.parseDouble(this.addFundsTxtFld.getText()));
        }catch(Exception e){
            errorLabel.setText("\nError: " + e.getMessage());
            errorLabel.setVisible(true);
        }
        this.myFundsLabel.setText("Account funds: " + ((Client) instance.getCurrentUser()).getWallet());
    }
    public void withdrawFundsClicked(ActionEvent event){
        errorLabel.setVisible(false);
        try {
            ((Client) instance.getCurrentUser()).subtractWallet(Double.parseDouble(this.addFundsTxtFld.getText()));
        }catch(Exception e){
            errorLabel.setText("\nError: " + e.getMessage());
            errorLabel.setVisible(true);
        }
        this.myFundsLabel.setText("Account funds: " + ((Client) instance.getCurrentUser()).getWallet());
    }


    public void viewMessagesClicked(ActionEvent event){}

    public void reportUserClicked(ActionEvent event){}

    public void postProductClicked(ActionEvent event)throws IOException{
        Parent postProductPage = FXMLLoader.load(getClass().getResource("/postProduct.fxml"));
        Scene postProductScene = new Scene(postProductPage, 600, 400);
        Stage postProductWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        postProductWindow.setScene(postProductScene);
        postProductWindow.setResizable(false);

        this.clientHomeStorefrontsListView = (ListView) postProductScene.lookup("#clientHomeStorefrontsListView");
        ObservableList<String> myStorefrontStrings = this.makeStorefrontObservableList(instance.system.findStorefronts((Client) instance.currentUser));
        this.clientHomeStorefrontsListView.getItems().addAll(myStorefrontStrings);

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
                        Controller.this.selectedUser = Controller.instance.system.findAccount(storefrontOwner);
                        Controller.this.clientHomeGoToStorefrontOwnerLabel.setText(storefrontOwner);
                        Controller.this.selectedStorefront = Controller.instance.system.findStorefront(storefrontName, (Client) Controller.this.selectedUser);
                        instance.selectedStorefront = Controller.this.selectedStorefront;
                        if(instance.selectedStorefront.getClass().getTypeName().contains("Sell")){
                            Controller.this.clientHomeGoToStorefrontOwnerLabel.setText("Type: Sell");
                        }
                        else{
                            Controller.this.clientHomeGoToStorefrontOwnerLabel.setText("Type: Swap");
                        }
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
        instance.errorLabel1.setVisible(false);
        instance.successLabel1 = (Label) postProductScene.lookup("#successLabel");
        instance.successLabel1.setVisible(false);
        this.system=instance.system;

        postProductWindow.show();
    }


    private ObservableList<String> makeStorefrontObservableList(List<Storefront> storefrontListIn){
        ObservableList<String> observationsOut = FXCollections.<String>observableArrayList();
        for(Storefront storefront : storefrontListIn){
            observationsOut.add(storefront.retrieveStorefrontOwner().getAccountName() + ": " + storefront.getStorefrontName());
        }
        return observationsOut.sorted();
    }
    private ObservableList<String> makeOtherStorefrontObservableList(List<Storefront> storefrontListIn){
        ObservableList<String> observationsOut = FXCollections.<String>observableArrayList();
        for(Storefront storefront : storefrontListIn) {
            if (instance.getCurrentUser() != storefront.retrieveStorefrontOwner()) {
                observationsOut.add(storefront.retrieveStorefrontOwner().getAccountName() + ": " + storefront.getStorefrontName());
            }
        }
        return observationsOut.sorted();
    }
    private ObservableList<String> makeSwapStorefrontObservableList(List<Storefront> storefrontListIn){
        ObservableList<String> observationsOut = FXCollections.<String>observableArrayList();
        for(Storefront storefront : storefrontListIn) {
            if (storefront.getClass().getTypeName().contains("Swap")) {
                observationsOut.add(storefront.retrieveStorefrontOwner().getAccountName() + ": " + storefront.getStorefrontName());
            }
        }
        return observationsOut.sorted();
    }

    private ObservableList<String> makeProductsObservableList(List<AbstractProduct> abstractProductListIn){
        ObservableList<String> observationsOut = FXCollections.<String>observableArrayList();
        for(AbstractProduct product : abstractProductListIn){
            observationsOut.add(product.getProductMerchant().getAccountName() + ": " + product.getProductName()); }
        //return observationsOut.sorted();
        return observationsOut;
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

        instance.selectedProduct = null;
        instance.selectedStorefront = null;

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
