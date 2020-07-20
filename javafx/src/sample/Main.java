package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage primaryStage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));

        Parent root = (Parent) fxmlLoader.load();
       // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        Controller controller = (Controller)fxmlLoader.getController();
        controller.setStage(primaryStage.getScene()); // or what you want to do

    }


    public static void main(String[] args) {
        launch(args);
    }
}
