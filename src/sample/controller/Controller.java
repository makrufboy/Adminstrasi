package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sample.utils.Helper;

public class Controller extends Helper {
    @FXML
    private void enter_form (ActionEvent event){
        changePage(event, "Form");
    }
}
