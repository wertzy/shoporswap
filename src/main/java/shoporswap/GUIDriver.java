package shoporswap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GUIDriver extends Application {

    //KNOWN BUGS:

    //IF YOU change the selectedStorefront and then try to buy a product, it will give you an error. Same if you change selectedUser

    //If you go to a storefront, select a product, then go to a different store front, the listener will no longer have a selected product to listen to and the program will complain.

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/signIn.fxml"));
        primaryStage.setTitle("Shop or Swap: Sign In");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException {
        launch(args);
    }
}

