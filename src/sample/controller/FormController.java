package sample.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sample.modal.FieldSurat;
import sample.modal.Surat;
import sample.utils.Helper;
import com.spire.doc.*;


import java.awt.*;
import java.io.*;

import java.util.*;
import java.util.List;

public class FormController extends Helper{

    @FXML
    private void back_home (ActionEvent event){
        changePage(event, "Main");
    }

    @FXML
    private void pagePrint() throws IOException {
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
        replace(fieldList);
        excel(fieldList);
//        fieldList.forEach((key, value1) -> System.out.println(key + " " + value1));
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

    public void open(String direktori){
        Desktop desktop = Desktop.getDesktop();
        try {
            File f = new File(direktori);
            desktop.open(f);  // opens application (MSWord) associated with .doc file
        }
        catch(Exception ex) {
            // WordDocument.this is to refer to outer class's instance from inner class
        }
    }

    public void excel(Map<String, String> peta) throws IOException {

        FileInputStream file = new FileInputStream(new File("D:\\netbean\\tes\\test1.xlsx"));

        //Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        //Get first/desired sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);

        //Iterate over data and write to sheet
        int rowCount = sheet.getLastRowNum();
        Row row = sheet.createRow(++rowCount);

        Iterator iterator = peta.keySet().iterator();
        int cellnum = 0;

        while(iterator.hasNext()){
            Object key   = iterator.next();
//            System.out.println("ini dia judul "+ (String) key);
            Object value = peta.get(key);
            Cell cell = row.createCell(cellnum++);
            cell.setCellValue((String) value);
//            System.out.println("ini dia hasil "+ (String) value);
        }

        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("D:\\netbean\\tes\\test1.xlsx"));
            workbook.write(out);
            out.close();
//            System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void replace(Map<String, String> peta){
        String input = "D:\\netbean\\tes\\dok1.docx";
        String output = "D:\\netbean\\tes\\keluar.docx";
        //load Word document
        Document document = new Document();
        document.loadFromFile(input, FileFormat.Docx);

        Iterator iterator = peta.keySet().iterator();

        while(iterator.hasNext()){
            Object key   = iterator.next();
            Object value = peta.get(key);
            switch((String) key) {
                case "Nama":
                    document.replace("namx", (String) value, false, true);
                    break;
                case "Jenis Kelamin":
                    document.replace("jkx", (String) value, false, true);
                    break;
                case "Tempat / Tgl Lahir":
                    document.replace("ttlx", (String) value, false, true);
                    break;
                case "Agama / Kebangsaan":
                    document.replace("agx", (String) value, false, true);
                    break;
                case "Pekerjaan":
                    document.replace("pjx", (String) value, false, true);
                    break;
                case "NIK":
                    document.replace("nikx", (String) value, false, true);
                    break;
                case "Alamat":
                    document.replace("alx", (String) value, false, true);
                    break;
                case "Nomer Lingkungan":
                    document.replace("nlx", (String) value, false, true);
                    break;
                default:
                    break;
            }
        }

        //save the document
        document.saveToFile(output,FileFormat.Docx);
        open(output);
    }

    public void setColumnName(String testText) {
        System.out.println(testText);

//        check list data
        if (testText.equals("Surat Keterangan Usaha")){
            this.fieldKolomSurat = FieldSurat.getFieldSuratKeteranganUsaha();
        }else if(testText.equals("Surat Keterangan Menikah")){
            this.fieldKolomSurat = FieldSurat.getFieldSuratKeteranganMenikah();
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
