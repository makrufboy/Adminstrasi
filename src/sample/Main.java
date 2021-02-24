package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/Main.fxml"));
//            System.out.println(loader.getLocation());
//            Parent root = FXMLLoader.load(getClass().getResource("/sample/view/Main.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Pelayanan");
            primaryStage.setScene(new Scene(root, 600, 500));
            primaryStage.setMaximized(true);
//        primaryStage.setResizable(true);
            primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
