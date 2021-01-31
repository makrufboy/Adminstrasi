package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.utils.Helper;

public class FormController extends Helper {
    @FXML
    private void back_home (ActionEvent event){
        changePage(event, "Main");
    }
}
