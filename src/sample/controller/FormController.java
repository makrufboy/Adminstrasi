package sample.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import sample.modal.FieldSurat;
import sample.modal.Surat;
import sample.utils.Helper;

import java.io.IOException;
import java.util.*;

public class FormController extends Helper{

    @FXML
    private void back_home (ActionEvent event){
        changePage(event, "Main");
    }

    @FXML
    private void pagePrint() {
        ObservableList<Node> childrens = formGrid.getChildren();
        String judul = "";
        String value;
        for (Node nodes : childrens){
            ObservableList<Node> parent = ((AnchorPane) nodes).getChildren();
            for (Node node : parent){
                ObservableList<Node> child = ((VBox) node).getChildren();
                for (Node data : child){
                    if (data instanceof Label){
                        judul = (((Label)data).getText());
                    }
                    else if (data instanceof TextField){
                        value = (((TextField)data).getText());
                        fieldList.put(judul,value);
                    }
                }
            }
        }
        fieldList.forEach((key, value1) -> System.out.println(key + " " + value1));
    }

    @FXML
    private void reset(){
        ObservableList<Node> childrens = formGrid.getChildren();
        for (Node nodes : childrens){
            ObservableList<Node> parent = ((AnchorPane) nodes).getChildren();
            for (Node node : parent){
                ObservableList<Node> child = ((VBox) node).getChildren();
                for (Node data : child){
                    if (data instanceof TextField){
                        ((TextField)data).setText("");
                    }
                }
            }
        }
        fieldList.clear();
    }

    @FXML
    private Label labelSurat;

    @FXML
    private GridPane formGrid;

    @FXML
    private HBox boxButton;

    private List<FieldSurat> fieldSurats = new ArrayList<>();
    private Map<String, String> fieldList = new LinkedHashMap<>();
    private String[] fieldKolomSurat;

    public void setColumnName(String testText) {
        System.out.println(testText);

//        check list data
        if (testText.equals("Surat Keterangan Usaha")){
            this.fieldKolomSurat = FieldSurat.getFieldSuratKeteranganUsaha();
        }else if(testText.equals("Surat Keterangan Kematian")){
            this.fieldKolomSurat = FieldSurat.getFieldSuratKeteranganKematian();
        }else if(testText.equals("Surat Keterangan Beda Nama")){
            this.fieldKolomSurat = FieldSurat.getFieldSuratKeteranganBedaNama();
        }else if(testText.equals("Surat Keterangan Beda Tanggal Lahir")){
            this.fieldKolomSurat = FieldSurat.getFieldSuratKeteranganBedaTanggalLahir();
        }else if(testText.equals("Surat Keterangan Belum Memiliki Rumah")){
            this.fieldKolomSurat = FieldSurat.getFieldSuratKeteranganBelumMemilikiRumah();
        }else if(testText.equals("Surat Keterangan Identitas Orang Tua")){
            this.fieldKolomSurat = FieldSurat.getFieldSuratKeteranganIdentitasOrangTua();
        }else if(testText.equals("Surat Keterangan Duda")){
            this.fieldKolomSurat = FieldSurat.getFieldSuratKeteranganDuda();
        }else if(testText.equals("Surat Keterangan Menikah")){
            this.fieldKolomSurat = FieldSurat.getFieldSuratKeteranganMenikah();
        }else if(testText.equals("Surat Keterangan Numpang Nikah")){
            this.fieldKolomSurat = FieldSurat.getFieldSuratKeteranganNumpangNikah();
        }else if(testText.equals("Surat Keterangan Belum Menikah")){
            this.fieldKolomSurat = FieldSurat.getFieldSuratKeteranganBelumMenikah();
        }else if(testText.equals("Surat Keterangan Penghasilan")){
            this.fieldKolomSurat = FieldSurat.getFieldSuratKeteranganPenghasilan();
        }else if(testText.equals("Surat Keterangan Tanah Tidak Dalam Sengketa")){
            this.fieldKolomSurat = FieldSurat.getFieldSuratKeteranganTanahTidakDalamSengketa();
        }else if(testText.equals("Surat Keterangan Tidak Mampu")){
            this.fieldKolomSurat = FieldSurat.getFieldSuratKeteranganTidakMampu();
        }else if(testText.equals("Surat Keterangan Cerai Lingkungan")){
            this.fieldKolomSurat = FieldSurat.getFieldSuratKeteranganCeraiLingkungan();
        }else if(testText.equals("Surat Keterangan Catatan Kepolisian")){
            this.fieldKolomSurat = FieldSurat.getFieldSuratKeteranganCatatanKepolisian();
        }else if(testText.equals("Surat Keterangan Izin Berkunjung")){
            this.fieldKolomSurat = FieldSurat.getFieldSuratKeteranganIzinBerkunjung();
        }else if(testText.equals("Surat Keterangan Kehilangan")){
            this.fieldKolomSurat = FieldSurat.getFieldSuratKeteranganKehilangan();
        }else if(testText.equals("Surat Keterangan Bepergian")){
            this.fieldKolomSurat = FieldSurat.getFieldSuratKeteranganBepergian();
        }else if(testText.equals("Surat Keterangan Kepemilikan Sepeda Motor")){
            this.fieldKolomSurat = FieldSurat.getFieldSuratKepemilikanSepedaMotor();
        }else if(testText.equals("Surat Keterangan Telah Melakukan Penelitian")){
            this.fieldKolomSurat = FieldSurat.getFieldSuratKeteranganMelakukanPenelitian();
        }else if(testText.equals("Surat Keterangan Perwalian/Pengampu")){
            this.fieldKolomSurat = FieldSurat.getFieldSuratKeteranganPerwalian();
        }else if(testText.equals("Surat Keterangan Terdaftar")){
            this.fieldKolomSurat = FieldSurat.getFieldSuratKeteranganTerdaftar();
        }else if(testText.equals("Surat Keterangan Domisili Usaha")){
            this.fieldKolomSurat = FieldSurat.getFieldSuratKeteranganDomisiliUsaha();
        }else if(testText.equals("Surat Keterangan Kelahiran")){
            this.fieldKolomSurat = FieldSurat.getFieldSuratKeteranganKelahiran();
        }
    }

    private List<FieldSurat> getFieldSurats() {
        List<FieldSurat> fieldSurats = new ArrayList<>();
        FieldSurat fieldSurat;

        for (int i = 0; i< fieldKolomSurat.length; i++){
            fieldSurat = new FieldSurat();
            fieldSurat.setFieldFormSurat(fieldKolomSurat[i]);
            fieldSurats.add(fieldSurat);
        }
        return fieldSurats;
    }

    public void setLabelSurat(Surat text){
        labelSurat.setText(text.getNamaSurat());
    }

    public void init(String text){
        setColumnName(text);
        fieldSurats.addAll(getFieldSurats());


        int col = 0;
        int row = 1;

        try {
            for (int i=0; i<fieldSurats.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../view/Field_item.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                FieldFormController fieldFormController = fxmlLoader.getController();
                fieldFormController.setFieldSurat(fieldSurats.get(i));

                if (col == 3){
                    col = 0;
                    row++;
                }

                formGrid.add(anchorPane, col++, row);

                formGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                formGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                formGrid.setMaxWidth(Region.USE_PREF_SIZE);

                formGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
                formGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                formGrid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        boxButton.setAlignment(Pos.CENTER);
    }
}
