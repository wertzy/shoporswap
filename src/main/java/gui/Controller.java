package gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        // get a handle to the stage
        Stage stage = (Stage) exitButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
    public void logIn(){
        System.out.println(usernameTxtFld.getText());

//        if(shopOrSwap.signIn(usernameTxtFld.getText(), passwordTxtFld.getText()) == null){
//            System.out.println("No User found with those credentials");
//            return null;
//        }
//        return shopOrSwap.signIn(usernameTxtFld.getText(), passwordLabel.getText());

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

}

