package shoporswap;

import io.ShopOrSwapRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
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

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Controller {

    // sign in items
    @FXML Button signInButton;
    @FXML private TextField signInAccountName;
    @FXML private PasswordField signInPassword;
    @FXML private Label signInInvalidLabel;

    // client home page items
    @FXML private Label clientHomeTitle;
    @FXML private ListView clientHomeMyStorefrontsListView;
    @FXML private Label clientHomeMyStorefrontsHeader;
    @FXML private ListView clientHomeMyMessagesListView;
    @FXML private Label clientHomeMyMessagesHeader;

    private final String dataFileName = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "systemData.json";
    private ShopOrSwap system;
    private Account currentUser;

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

    public void signIn(ActionEvent event) throws IOException{
        Account attemptedSignInAccount = this.system.signIn(this.signInAccountName.getText(), this.signInPassword.getText());
        if(attemptedSignInAccount == null){
            this.signInInvalidLabel.setVisible(true);
        }else{
            this.currentUser = attemptedSignInAccount;
            if(this.currentUser.getClass().getName().contains("Client")){
                Parent clientHomepage = FXMLLoader.load(getClass().getResource("/clientHome.fxml"));
                Scene clientHomepageScene = new Scene(clientHomepage, 600, 400);
                Stage clientHomepageWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                clientHomepageWindow.setTitle("Home: " + this.currentUser.getAccountName());
                clientHomepageWindow.setScene(clientHomepageScene);
                clientHomepageWindow.setResizable(false);

                this.clientHomeTitle = (Label) clientHomepageScene.lookup("#clientHomeTitle");
                this.clientHomeTitle.setText("Home: " + this.currentUser.getAccountName());

                this.clientHomeMyStorefrontsListView = (ListView) clientHomepageScene.lookup("#clientHomeMyStorefrontsListView");
                ObservableList<String> storefrontStrings = this.makeStorefrontObservableList(this.system.findStorefronts((Client) this.currentUser));
                this.clientHomeMyStorefrontsListView.getItems().addAll(storefrontStrings);
                this.clientHomeMyStorefrontsHeader = (Label) clientHomepageScene.lookup("#clientHomeMyStorefrontsHeader");
                this.clientHomeMyStorefrontsHeader.setText("My Storefronts: " + storefrontStrings.size());

                this.clientHomeMyMessagesListView = (ListView) clientHomepageScene.lookup("#clientHomeMyMessagesListView");
                ObservableList<String> messageStrings = this.makeMessageObservableList(this.system.findMessagesByRecipient(this.currentUser));
                this.clientHomeMyMessagesListView.getItems().addAll(messageStrings);
                this.clientHomeMyMessagesHeader = (Label) clientHomepageScene.lookup("#clientHomeMyMessagesHeader");
                this.clientHomeMyMessagesHeader.setText("My Messages: " + messageStrings.size());

                clientHomepageWindow.show();
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
                this.signInInvalidLabel.setText("Account created successfully.");
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

    private ObservableList<String> makeStorefrontObservableList(List<Storefront> storefrontListIn){
        ObservableList<String> observationsOut = FXCollections.<String>observableArrayList();
        String storefrontRecordString;
        for(Storefront storefront : storefrontListIn){
            if(storefront instanceof SellStorefront){
                storefrontRecordString = "Sell: ";
            }else{
                storefrontRecordString = "Swap: ";
            }
            storefrontRecordString = storefrontRecordString + storefront.getStorefrontName() + " (" + storefront.getStorefrontProducts().size() + " Products)";
            observationsOut.add(storefrontRecordString);
        }
        return observationsOut;
    }

    private ObservableList<String> makeMessageObservableList(List<AbstractMessage> messageListIn){
        ObservableList<String> observationsOut = FXCollections.<String>observableArrayList();
        String messageRecordString;
        for(AbstractMessage message : messageListIn){
            messageRecordString = "From: " + message.getSender() + "\n\tSubject: " + message.getSubject() + "\n\tContent: " + message.getContent();
            observationsOut.add(messageRecordString);
        }
        return observationsOut;
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
