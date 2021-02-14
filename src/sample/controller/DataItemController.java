package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sample.modal.Surat;
import sample.utils.MyListener;

public class DataItemController {
    @FXML
    private ImageView imgSurat;

    @FXML
    private Label namaSurat;

    @FXML
    private void  click(MouseEvent mouseEvent){
        myListener.onClickListener(surat, mouseEvent);
    }

    private Surat surat;
    private MyListener myListener;

    public void setData(Surat surat, MyListener myListener){
        this.surat = surat;
        this.myListener = myListener;
        namaSurat.setText(surat.getNamaSurat());
        Image image = new Image(getClass().getResourceAsStream(surat.getImgSrc()));
        imgSurat.setImage(image);
    }



}
