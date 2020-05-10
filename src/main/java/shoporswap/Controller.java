package shoporswap;

import io.ShopOrSwapRecord;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import util.JsonUtil;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML Button signInButton;
    @FXML private TextField signInAccountName;
    @FXML private PasswordField signInPassword;
    @FXML private Label signInInvalidLabel;

    private final String dataFileName = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "systemData.json";
    private ShopOrSwap system;

    public Controller() throws IOException {
        this.system = JsonUtil.fromJsonFile(dataFileName, ShopOrSwapRecord.class).toShopOrSwap();
    }

    public void signIn(ActionEvent event) throws IOException{
        if(this.system.signIn(this.signInAccountName.getText(), this.signInPassword.getText()) == null){
            this.signInInvalidLabel.setVisible(true);
        }
    }

}
