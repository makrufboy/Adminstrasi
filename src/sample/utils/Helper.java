package sample.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class Helper {
    protected void changePage(ActionEvent event, String page){
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/sample/view/" + page + ".fxml"));
        }catch (IOException e){
            e.printStackTrace();
        }

//        stage.setScene(new Scene(root, 1200, 800));
        stage.getScene().setRoot(root);
        stage.setMaximized(true);
        stage.show();
    }
}
