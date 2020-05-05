package gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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



    public void newAccount(){
        create.setVisible(true);
        passwordLabel1.setVisible(true);
        usernameNewLabel.setVisible(true);
        usernameTxtFld1.setVisible(true);
        passwordTxtFld1.setVisible(true);

    }
    public void exitClicked(){

    }
    public void logIn(){

    }
    public void create(){
        usernameNewLabel.setVisible(false);
    }

}

