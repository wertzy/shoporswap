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

                this.clientHomeAccountInfo = (Label) clientHomepageScene.lookup("#clientHomeAccountInfo");
                this.clientHomeAccountInfo.setText(
                        "\tAccount name: " + this.currentUser.getAccountName() +
                        "\n\tAccount password: " + this.currentUser.getAccountPassword() +
                        "\n\tAccount funds: " + ((Client) this.currentUser).getWallet() +
                        "\n\tAccount rating: " + ((Client) this.currentUser).calculateRating()
                );

                this.clientHomeStorefrontsListView = (ListView) clientHomepageScene.lookup("#clientHomeStorefrontsListView");
                ObservableList<String> storefrontStrings = this.makeStorefrontObservableList(this.system.findAllStorefronts());
                this.clientHomeStorefrontsListView.getItems().addAll(storefrontStrings);
                this.clientHomeStorefrontsHeader = (Label) clientHomepageScene.lookup("#clientHomeStorefrontsHeader");
                this.clientHomeStorefrontsHeader.setText("All Storefronts: " + storefrontStrings.size());

                this.clientHomeMessagesListView = (ListView) clientHomepageScene.lookup("#clientHomeMessagesListView");
                ObservableList<String> messageStrings = this.makeMessageObservableList(this.system.findMessagesByRecipient(this.currentUser));
                this.clientHomeMessagesListView.getItems().addAll(messageStrings);
                this.clientHomeMessagesHeader = (Label) clientHomepageScene.lookup("#clientHomeMessagesHeader");
                this.clientHomeMessagesHeader.setText("My Messages: " + messageStrings.size());


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
