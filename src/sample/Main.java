package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Magic The Gathering. Desktop");
        primaryStage.setScene(new Scene(root, 1280, 840));
        primaryStage.setMinHeight(860);
        primaryStage.setMinWidth(840);

        primaryStage.show();
        primaryStage.getIcons().add(new Image("file:./src/sample/images/icon.png"));

    }


    public static void main(String[] args) {
        launch(args);
    }

}


