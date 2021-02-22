package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sample.modal.ExcelData;
import sample.utils.NewMyListener;



public class ExcelController {
    @FXML
    private ImageView imgExcel;

    @FXML
    private Label namaDataSurat;

    @FXML
    private void clicked(MouseEvent mouseEvent){
        newMyListener.onClickListener(mouseEvent);
    }

    private NewMyListener newMyListener;
    private ExcelData excelData;

    public void setExcel(ExcelData excelData, NewMyListener newMyListener){
        this.excelData = excelData;
        this.newMyListener = newMyListener;
        namaDataSurat.setText(excelData.getDataSuratKeterangan());
        Image image = new Image(getClass().getResourceAsStream(excelData.getImgDataSuratKeterangan()));
        imgExcel.setImage(image);
    }

}
