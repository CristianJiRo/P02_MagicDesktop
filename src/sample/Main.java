package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class Main extends Application {

       @Override

    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Magic The Gathering. Desktop");

        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.setMinHeight(580);
        primaryStage.setMinWidth(780);

        //primaryStage.getIcons().add(new Image("icon.png"));

        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);



    }
}


