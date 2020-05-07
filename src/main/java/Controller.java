import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Controller {
    public TextField usernameTxtFld;
    public TextField passwordTxtFld;
    public TextField usernameTxtFld1;
    public TextField passwordTxtFld1;
    public Button createAccount;
    public Label usernameLabel;
    public Label passwordLabel;
    public Label passwordLabel1;
    public Label errorLabel;
    public Label errorLabel1;
    public Button create;
    public Button exitButton;
    public Button logInButton;
    public Label usernameNewLabel;

    private static final String DATA_FILE = "src/main/resources/systemData.json";
    public ShopOrSwap system;
    public User currentUser;

    public Controller() throws IOException{
        try {
            try {
                system = new ShopOrSwap(DATA_FILE);
            } catch (FileNotFoundException e) {
                system = generateData();
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
    public void logIn(ActionEvent event) throws IOException{
        if(system.signIn(usernameTxtFld.getText(), passwordTxtFld.getText()) == null){
            System.out.println("No User found with those credentials");
            errorLabel.setVisible(true);
        }
        else {
            errorLabel.setVisible(false);
            currentUser = system.signIn(usernameTxtFld.getText(), passwordLabel.getText());
            Parent productViewParent = FXMLLoader.load(getClass().getResource("/homepage.fxml"));
            Scene productViewScene = new Scene(productViewParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(productViewScene);
            window.show();
        }


    }
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

    private static ShopOrSwap generateData() throws IOException {
        try {
            String dataFile = DATA_FILE;
            ShopOrSwap dataShopOrSwap = new ShopOrSwap();
            dataShopOrSwap.createAccount("user1", "pass1");
            dataShopOrSwap.createSellProduct("product 1", "description for product 1", "10", dataShopOrSwap.findAccount("user1"));
            dataShopOrSwap.createSellProduct("product 2", "description for product 2", "20", dataShopOrSwap.findAccount("user1"));
            dataShopOrSwap.createSellProduct("product 3", "description for product 3", "30", dataShopOrSwap.findAccount("user1"));
            dataShopOrSwap.createSellProduct("product 4", "description for product 4", "40", dataShopOrSwap.findAccount("user1"));
            dataShopOrSwap.createSellProduct("product 5", "description for product 5", "50", dataShopOrSwap.findAccount("user1"));
            dataShopOrSwap.createSellProduct("product 6", "description for product 6", "10", dataShopOrSwap.findAccount("user1"));
            dataShopOrSwap.createSellProduct("product 7", "description for product 7", "20", dataShopOrSwap.findAccount("user1"));
            dataShopOrSwap.createSellProduct("product 8", "description for product 8", "30", dataShopOrSwap.findAccount("user1"));
            dataShopOrSwap.createSellProduct("product 9", "description for product 9", "40", dataShopOrSwap.findAccount("user1"));
            dataShopOrSwap.createSellProduct("product 10", "description for product 10", "50", dataShopOrSwap.findAccount("user1"));
            dataShopOrSwap.createSwapProduct("product 51", "description for product 51", "10", dataShopOrSwap.findAccount("user1"));
            dataShopOrSwap.createSwapProduct("product 52", "description for product 52", "20", dataShopOrSwap.findAccount("user1"));
            dataShopOrSwap.createSwapProduct("product 53", "description for product 53", "30", dataShopOrSwap.findAccount("user1"));
            dataShopOrSwap.createSwapProduct("product 54", "description for product 54", "40", dataShopOrSwap.findAccount("user1"));
            dataShopOrSwap.createSwapProduct("product 55", "description for product 55", "50", dataShopOrSwap.findAccount("user1"));
            dataShopOrSwap.createSwapProduct("product 56", "description for product 56", "10", dataShopOrSwap.findAccount("user1"));
            dataShopOrSwap.createSwapProduct("product 57", "description for product 57", "20", dataShopOrSwap.findAccount("user1"));
            dataShopOrSwap.createSwapProduct("product 58", "description for product 58", "30", dataShopOrSwap.findAccount("user1"));
            dataShopOrSwap.createSwapProduct("product 59", "description for product 59", "40", dataShopOrSwap.findAccount("user1"));
            dataShopOrSwap.createSwapProduct("product 60", "description for product 60", "50", dataShopOrSwap.findAccount("user1"));

            dataShopOrSwap.createAccount("user2", "pass2");
            dataShopOrSwap.createSellProduct("product 11", "description for product 11", "10", dataShopOrSwap.findAccount("user2"));
            dataShopOrSwap.createSellProduct("product 12", "description for product 12", "20", dataShopOrSwap.findAccount("user2"));
            dataShopOrSwap.createSellProduct("product 13", "description for product 13", "30", dataShopOrSwap.findAccount("user2"));
            dataShopOrSwap.createSellProduct("product 14", "description for product 14", "40", dataShopOrSwap.findAccount("user2"));
            dataShopOrSwap.createSellProduct("product 15", "description for product 15", "50", dataShopOrSwap.findAccount("user2"));
            dataShopOrSwap.createSellProduct("product 16", "description for product 16", "10", dataShopOrSwap.findAccount("user2"));
            dataShopOrSwap.createSellProduct("product 17", "description for product 17", "20", dataShopOrSwap.findAccount("user2"));
            dataShopOrSwap.createSellProduct("product 18", "description for product 18", "30", dataShopOrSwap.findAccount("user2"));
            dataShopOrSwap.createSellProduct("product 19", "description for product 19", "40", dataShopOrSwap.findAccount("user2"));
            dataShopOrSwap.createSellProduct("product 20", "description for product 20", "50", dataShopOrSwap.findAccount("user2"));
            dataShopOrSwap.createSwapProduct("product 61", "description for product 61", "10", dataShopOrSwap.findAccount("user2"));
            dataShopOrSwap.createSwapProduct("product 62", "description for product 62", "20", dataShopOrSwap.findAccount("user2"));
            dataShopOrSwap.createSwapProduct("product 63", "description for product 63", "30", dataShopOrSwap.findAccount("user2"));
            dataShopOrSwap.createSwapProduct("product 64", "description for product 64", "40", dataShopOrSwap.findAccount("user2"));
            dataShopOrSwap.createSwapProduct("product 65", "description for product 65", "50", dataShopOrSwap.findAccount("user2"));
            dataShopOrSwap.createSwapProduct("product 66", "description for product 66", "10", dataShopOrSwap.findAccount("user2"));
            dataShopOrSwap.createSwapProduct("product 67", "description for product 67", "20", dataShopOrSwap.findAccount("user2"));
            dataShopOrSwap.createSwapProduct("product 68", "description for product 68", "30", dataShopOrSwap.findAccount("user2"));
            dataShopOrSwap.createSwapProduct("product 69", "description for product 69", "40", dataShopOrSwap.findAccount("user2"));
            dataShopOrSwap.createSwapProduct("product 70", "description for product 70", "50", dataShopOrSwap.findAccount("user2"));

            dataShopOrSwap.createAccount("user3", "pass3");
            dataShopOrSwap.createSellProduct("product 21", "description for product 21", "10", dataShopOrSwap.findAccount("user3"));
            dataShopOrSwap.createSellProduct("product 22", "description for product 22", "20", dataShopOrSwap.findAccount("user3"));
            dataShopOrSwap.createSellProduct("product 23", "description for product 23", "30", dataShopOrSwap.findAccount("user3"));
            dataShopOrSwap.createSellProduct("product 24", "description for product 24", "40", dataShopOrSwap.findAccount("user3"));
            dataShopOrSwap.createSellProduct("product 25", "description for product 25", "50", dataShopOrSwap.findAccount("user3"));
            dataShopOrSwap.createSellProduct("product 26", "description for product 26", "10", dataShopOrSwap.findAccount("user3"));
            dataShopOrSwap.createSellProduct("product 27", "description for product 27", "20", dataShopOrSwap.findAccount("user3"));
            dataShopOrSwap.createSellProduct("product 28", "description for product 28", "30", dataShopOrSwap.findAccount("user3"));
            dataShopOrSwap.createSellProduct("product 29", "description for product 29", "40", dataShopOrSwap.findAccount("user3"));
            dataShopOrSwap.createSellProduct("product 30", "description for product 30", "50", dataShopOrSwap.findAccount("user3"));
            dataShopOrSwap.createSwapProduct("product 71", "description for product 71", "10", dataShopOrSwap.findAccount("user3"));
            dataShopOrSwap.createSwapProduct("product 72", "description for product 72", "20", dataShopOrSwap.findAccount("user3"));
            dataShopOrSwap.createSwapProduct("product 73", "description for product 73", "30", dataShopOrSwap.findAccount("user3"));
            dataShopOrSwap.createSwapProduct("product 74", "description for product 74", "40", dataShopOrSwap.findAccount("user3"));
            dataShopOrSwap.createSwapProduct("product 75", "description for product 75", "50", dataShopOrSwap.findAccount("user3"));
            dataShopOrSwap.createSwapProduct("product 76", "description for product 76", "10", dataShopOrSwap.findAccount("user3"));
            dataShopOrSwap.createSwapProduct("product 77", "description for product 77", "20", dataShopOrSwap.findAccount("user3"));
            dataShopOrSwap.createSwapProduct("product 78", "description for product 78", "30", dataShopOrSwap.findAccount("user3"));
            dataShopOrSwap.createSwapProduct("product 79", "description for product 79", "40", dataShopOrSwap.findAccount("user3"));
            dataShopOrSwap.createSwapProduct("product 80", "description for product 80", "50", dataShopOrSwap.findAccount("user3"));

            dataShopOrSwap.createAccount("user4", "pass4");
            dataShopOrSwap.createSellProduct("product 31", "description for product 31", "10", dataShopOrSwap.findAccount("user4"));
            dataShopOrSwap.createSellProduct("product 32", "description for product 32", "20", dataShopOrSwap.findAccount("user4"));
            dataShopOrSwap.createSellProduct("product 33", "description for product 33", "30", dataShopOrSwap.findAccount("user4"));
            dataShopOrSwap.createSellProduct("product 34", "description for product 34", "40", dataShopOrSwap.findAccount("user4"));
            dataShopOrSwap.createSellProduct("product 35", "description for product 35", "50", dataShopOrSwap.findAccount("user4"));
            dataShopOrSwap.createSellProduct("product 36", "description for product 36", "10", dataShopOrSwap.findAccount("user4"));
            dataShopOrSwap.createSellProduct("product 37", "description for product 37", "20", dataShopOrSwap.findAccount("user4"));
            dataShopOrSwap.createSellProduct("product 38", "description for product 38", "30", dataShopOrSwap.findAccount("user4"));
            dataShopOrSwap.createSellProduct("product 39", "description for product 39", "40", dataShopOrSwap.findAccount("user4"));
            dataShopOrSwap.createSellProduct("product 40", "description for product 40", "50", dataShopOrSwap.findAccount("user4"));
            dataShopOrSwap.createSwapProduct("product 81", "description for product 81", "10", dataShopOrSwap.findAccount("user4"));
            dataShopOrSwap.createSwapProduct("product 82", "description for product 82", "20", dataShopOrSwap.findAccount("user4"));
            dataShopOrSwap.createSwapProduct("product 83", "description for product 83", "30", dataShopOrSwap.findAccount("user4"));
            dataShopOrSwap.createSwapProduct("product 84", "description for product 84", "40", dataShopOrSwap.findAccount("user4"));
            dataShopOrSwap.createSwapProduct("product 85", "description for product 85", "50", dataShopOrSwap.findAccount("user4"));
            dataShopOrSwap.createSwapProduct("product 86", "description for product 86", "10", dataShopOrSwap.findAccount("user4"));
            dataShopOrSwap.createSwapProduct("product 87", "description for product 87", "20", dataShopOrSwap.findAccount("user4"));
            dataShopOrSwap.createSwapProduct("product 88", "description for product 88", "30", dataShopOrSwap.findAccount("user4"));
            dataShopOrSwap.createSwapProduct("product 89", "description for product 89", "40", dataShopOrSwap.findAccount("user4"));
            dataShopOrSwap.createSwapProduct("product 90", "description for product 90", "50", dataShopOrSwap.findAccount("user4"));

            dataShopOrSwap.createAccount("user5", "pass5");
            dataShopOrSwap.createSellProduct("product 41", "description for product 41", "10", dataShopOrSwap.findAccount("user5"));
            dataShopOrSwap.createSellProduct("product 42", "description for product 42", "20", dataShopOrSwap.findAccount("user5"));
            dataShopOrSwap.createSellProduct("product 43", "description for product 43", "30", dataShopOrSwap.findAccount("user5"));
            dataShopOrSwap.createSellProduct("product 44", "description for product 44", "40", dataShopOrSwap.findAccount("user5"));
            dataShopOrSwap.createSellProduct("product 45", "description for product 45", "50", dataShopOrSwap.findAccount("user5"));
            dataShopOrSwap.createSellProduct("product 46", "description for product 46", "10", dataShopOrSwap.findAccount("user5"));
            dataShopOrSwap.createSellProduct("product 47", "description for product 47", "20", dataShopOrSwap.findAccount("user5"));
            dataShopOrSwap.createSellProduct("product 48", "description for product 48", "30", dataShopOrSwap.findAccount("user5"));
            dataShopOrSwap.createSellProduct("product 49", "description for product 49", "40", dataShopOrSwap.findAccount("user5"));
            dataShopOrSwap.createSellProduct("product 50", "description for product 50", "50", dataShopOrSwap.findAccount("user5"));
            dataShopOrSwap.createSwapProduct("product 91", "description for product 91", "10", dataShopOrSwap.findAccount("user5"));
            dataShopOrSwap.createSwapProduct("product 92", "description for product 92", "20", dataShopOrSwap.findAccount("user5"));
            dataShopOrSwap.createSwapProduct("product 93", "description for product 93", "30", dataShopOrSwap.findAccount("user5"));
            dataShopOrSwap.createSwapProduct("product 94", "description for product 94", "40", dataShopOrSwap.findAccount("user5"));
            dataShopOrSwap.createSwapProduct("product 95", "description for product 95", "50", dataShopOrSwap.findAccount("user5"));
            dataShopOrSwap.createSwapProduct("product 96", "description for product 96", "10", dataShopOrSwap.findAccount("user5"));
            dataShopOrSwap.createSwapProduct("product 97", "description for product 97", "20", dataShopOrSwap.findAccount("user5"));
            dataShopOrSwap.createSwapProduct("product 98", "description for product 98", "30", dataShopOrSwap.findAccount("user5"));
            dataShopOrSwap.createSwapProduct("product 99", "description for product 99", "40", dataShopOrSwap.findAccount("user5"));
            dataShopOrSwap.createSwapProduct("product 100", "description for product 100", "50", dataShopOrSwap.findAccount("user5"));

            dataShopOrSwap.exit(dataFile);

            ShopOrSwap importedShopOrSwap = new ShopOrSwap(dataFile);
            System.out.println("Imported ShopOrSwap data with User count " + importedShopOrSwap.getUserList().size() + " and Product count " + importedShopOrSwap.getProductList().size());
            return importedShopOrSwap;
        }catch(Exception e){
            System.out.println("Cannot import data");
            System.out.println(e.getMessage());
            System.exit(1);
            return null;
        }
    }

}

