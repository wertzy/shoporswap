package shoporswap;

import io.ShopOrSwapRecord;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import util.JsonUtil;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Controller {
    public TextField usernameTxtFld;
    public TextField passwordTxtFld;
    public TextField usernameTxtFld1;
    public TextField passwordTxtFld1;
    public TextField searchTxtFld;
    public Label productName;
    public Label productOwner;
    public Label storefrontName;
    public Label productDescription;
    public Label productTags;
    public Label price;
    public Label priceInt;
    public Label productName1;
    public Label productOwner1;
    public Label storefrontName1;
    public Label productDescription1;
    public Label productTags1;
    public Label price1;
    public Label priceInt1;
    public Label usernameNewLabel;
    public Label usernameLabel;
    public Label passwordLabel;
    public Label passwordLabel1;
    public Label errorLabel;
    public Label errorLabel1;
    public Button viewMyProducts;
    public Button viewSellProducts;
    public Button viewSwapProducts;
    public Button viewStorefronts;
    public Button viewMyStorefronts;
    public Button viewMessages;
    public Button postProduct;
    public Button create;
    public Button exitButton;
    public Button logInButton;
    public Button createAccount;
    public RadioButton rbSell;
    public RadioButton rbSwap;

    private static final String DATA_FILE = "src/main/resources/systemData.json";
    public ShopOrSwap system;
    public Client currentUser;

    public Controller() throws IOException{
        try {
            System.out.println("Hi");
            try {
                system = JsonUtil.fromJsonFile(DATA_FILE, ShopOrSwapRecord.class).toShopOrSwap();
                System.out.println("Howdy");
            } catch (FileNotFoundException e) {
                system = generateData();
                System.out.println("catch");
            }
            System.out.println("Hello");
        }catch (Exception e){
            System.out.println("Could not start system");
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public void newAccount(){
        create.setVisible(true);
        passwordLabel1.setVisible(true);
        usernameNewLabel.setVisible(true);
        usernameTxtFld1.setVisible(true);
        passwordTxtFld1.setVisible(true);

    }
    public void exitClicked(){
        // get a handle to the stage
        Stage stage = (Stage) exitButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
//    public void logIn(ActionEvent event) throws IOException{
//        if(system.signIn(usernameTxtFld.getText(), passwordTxtFld.getText()) == null){
//            System.out.println("No User found with those credentials");
//            errorLabel.setVisible(true);
//        }
//        else {
//            errorLabel.setVisible(false);
//            currentUser = system.signIn(usernameTxtFld.getText(), passwordLabel.getText());
//            Parent productViewParent = FXMLLoader.load(getClass().getResource("/homepage.fxml"));
//            Scene productViewScene = new Scene(productViewParent);
//
//            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//            window.setScene(productViewScene);
//            window.show();
//        }


//    }
    public void create(){
        System.out.println(passwordTxtFld1.getText());
//        try{
//            return shopOrSwap.createAccount(usernameTxtFld1.getText(),passwordTxtFld1.getText());
//        }catch(Exception e){
//            System.out.println("Cannot create account");
//            return null;
//        }
//    }
    }
    public void viewMyProductsClicked(){

    }
    public void viewSellProductsClicked(){

    }
    public void viewSwapProductsClicked(){

    }
    public void viewMessagesClicked(){

    }
    public void postProductClicked(){

    }
    public void viewMyStorefrontClicked(){

    }
    public void viewStorefrontsClicked(){

    }


    private static ShopOrSwap generateData() throws IOException {
        try {

            String dataFile = DATA_FILE;
            ShopOrSwap dataShopOrSwap = new ShopOrSwap();
            System.out.println("Howdy0");
            Account dataClient1 = dataShopOrSwap.addAccount("Client", "client1", "client1");
            Account dataClient2 = dataShopOrSwap.addAccount("Client", "client2", "client2");
            Account dataAdmin = dataShopOrSwap.addAccount("Admin", "admin1", "admin1");
            System.out.println("Howdy1");
            Storefront dataSellStorefront = dataShopOrSwap.addStorefront("Sell", "sell storefront 1", (Client) dataClient1);
            Storefront dataSwapStorefront1 = dataShopOrSwap.addStorefront("Swap", "swap storefront 1", (Client) dataClient1);
            Storefront dataSwapStorefront2 = dataShopOrSwap.addStorefront("Swap", "swap storefront 2", (Client) dataClient1);
            System.out.println("Howdy1.1");
            AbstractProduct dataSellProduct = dataShopOrSwap.addToStorefront("sell product 1", "description 1", 20, dataSellStorefront);
            AbstractProduct dataSwapProduct1 = dataShopOrSwap.addToStorefront("swap product 1", "description 1", 20, dataSwapStorefront1);
            AbstractProduct dataSwapProduct2 = dataShopOrSwap.addToStorefront("swap product 2", "description 2", 20, dataSwapStorefront2);
            dataShopOrSwap.addTagToProduct("tag1", dataSellProduct);
            dataShopOrSwap.addTagToProduct("tag2", dataSwapProduct1);
            dataShopOrSwap.addTagToProduct("tag2", dataSwapProduct2);
            System.out.println("Howdy2");


            JsonUtil.toJsonFile(DATA_FILE, new ShopOrSwapRecord(dataShopOrSwap));

            ShopOrSwap importedShopOrSwap = JsonUtil.fromJsonFile(DATA_FILE, ShopOrSwapRecord.class).toShopOrSwap();
            System.out.println("Howdy3");
            System.out.println("Imported ShopOrSwap data with User count " + importedShopOrSwap.getAccountCollection().size());
            return importedShopOrSwap;
        }catch(Exception e){
            System.out.println("Cannot import data");
            System.out.println(e.getMessage());
            System.exit(1);
            return null;
        }
    }
    private  static ShopOrSwap hotFixDataGeneration() throws IOException {
        ShopOrSwap dataShopOrSwap = new ShopOrSwap();
        System.out.println("Howdy0");
        Account dataClient1 = dataShopOrSwap.addAccount("Client", "client1", "client1");
        Account dataClient2 = dataShopOrSwap.addAccount("Client", "client2", "client2");
        //Account dataAdmin = dataShopOrSwap.addAccount("Admin", "admin1", "admin1");
        System.out.println("Howdy1");
        Storefront dataSellStorefront = dataShopOrSwap.addStorefront("Sell", "sell storefront 1", (Client) dataClient1);
        Storefront dataSwapStorefront1 = dataShopOrSwap.addStorefront("Swap", "swap storefront 1", (Client) dataClient1);
        Storefront dataSwapStorefront2 = dataShopOrSwap.addStorefront("Swap", "swap storefront 2", (Client) dataClient1);
        System.out.println("Howdy1.1");
        AbstractProduct dataSellProduct = dataShopOrSwap.addToStorefront("sell product 1", "description 1", 20, dataSellStorefront);
        AbstractProduct dataSwapProduct1 = dataShopOrSwap.addToStorefront("swap product 1", "description 1", 20, dataSwapStorefront1);
        AbstractProduct dataSwapProduct2 = dataShopOrSwap.addToStorefront("swap product 2", "description 2", 20, dataSwapStorefront2);
        dataShopOrSwap.addTagToProduct("tag1", dataSellProduct);
        dataShopOrSwap.addTagToProduct("tag2", dataSwapProduct1);
        dataShopOrSwap.addTagToProduct("tag2", dataSwapProduct2);
        System.out.println("Howdy2");

        return dataShopOrSwap;
    }

}

