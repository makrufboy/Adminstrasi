package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.modal.FieldSurat;

public class FieldFormController {
    @FXML
    private Label fieldNama;

    @FXML
    private TextField fieldText;

    private FieldSurat fieldSurat;

    public void setFieldSurat(FieldSurat fieldSurat){
        this.fieldSurat = fieldSurat;
        fieldNama.setText(fieldSurat.getFieldFormSurat());
    }


}
