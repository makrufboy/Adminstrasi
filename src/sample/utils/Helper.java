package sample.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import sample.modal.Surat;

import java.io.IOException;

public class Helper {
    protected void changePage(ActionEvent event, String page){
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../view/"+ page + ".fxml"));
        }catch (IOException e){
            e.printStackTrace();
        }

//        stage.setScene(new Scene(root, 1200, 800));
        stage.getScene().setRoot(root);
        stage.setMaximized(true);
        stage.show();
    }
}
