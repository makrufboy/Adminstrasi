package sample.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sample.modal.ExcelData;
import sample.modal.Surat;
import sample.utils.MyListener;
import sample.utils.NewMyListener;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {
    @FXML
    private TextField search;

    @FXML
    private GridPane grid;

    @FXML
    void chooser(ActionEvent event) throws IOException {
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        f.showSaveDialog(null);

        FileWriter myWriter = new FileWriter("excel.txt");

        myWriter.write(String.valueOf(f.getSelectedFile()));
        myWriter.close();

        int banyak = nama_surat.length;
        System.out.println("jumlah "+banyak);
        System.out.println("isi "+nama_surat[0]);
        for(int i =0;i<banyak;i++){
            FileInputStream file = new FileInputStream(new File("src/sample/template/"+nama_surat[i]+".xlsx"));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            try
            {
                FileInputStream fis=new FileInputStream("excel.txt");
                Scanner sc=new Scanner(fis);    //file to be scanned
                String dir=sc.nextLine();
                sc.close();

                FileOutputStream out2 = new FileOutputStream(new File(dir+"\\"+nama_surat[i]+".xlsx"));
                workbook.write(out2);
                out2.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void pageLaporan(ActionEvent event) {
        isSurat = false;
        grid.getChildren().clear();

        newMyListener = new NewMyListener() {
            @Override
            public void onClickListener(ExcelData ED, MouseEvent mouseEvent) {
                String nama=ED.getDataSuratKeterangan();
                String dir="";
                try {
                    dir=direk()+"\\";
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Desktop desktop = Desktop.getDesktop();
                try {
                    File f = new File(dir+nama+".xlsx");
                    desktop.open(f);  // opens application (MSWord) associated with .doc file
                }
                catch(Exception ex) {
                    // WordDocument.this is to refer to outer class's instance from inner class
                }
            }
        };

        int col = 0;
        int row = 1;

        try {
            for (int i=0; i< excelDatas.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/sample/view/Excel.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                ExcelController excelController = fxmlLoader.getController();
                excelController.setExcel(excelDatas.get(i), newMyListener);

                if(col == 4){
                    col = 0;
                    row++;
                }

                grid.add(anchorPane, col++, row);

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);


                GridPane.setMargin(anchorPane, new Insets(15));

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    String direk() throws FileNotFoundException {
        FileInputStream fis=new FileInputStream("excel.txt");
        Scanner sc=new Scanner(fis);    //file to be scanned
        String dir=sc.nextLine();
        sc.close();
        return dir;
    }

    @FXML
    void pageSurat(ActionEvent event) {
        isSurat = true;
        grid.getChildren().clear();


        int col = 0;
        int row = 1;

        try {
            for (int i=0; i< surats.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/sample/view/Data_item.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                DataItemController dataItemController = fxmlLoader.getController();
                dataItemController.setData(surats.get(i), myListener);

                if(col == 4){
                    col = 0;
                    row++;
                }

                grid.add(anchorPane, col++, row);

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);


                GridPane.setMargin(anchorPane, new Insets(15));

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private boolean isSurat = true;
    private MyListener myListener;
    private NewMyListener newMyListener;
    private List<Surat> surats = new ArrayList<>();
    private List<ExcelData> excelDatas = new ArrayList<>();
    private String [] nama_surat = new String[]{
            "Surat Keterangan Usaha",
            "Surat Keterangan Kematian",
            "Surat Keterangan Beda Nama",
            "Surat Keterangan Beda Tanggal Lahir",
            "Surat Keterangan Belum Memiliki Rumah",
            "Surat Keterangan Identitas Orang Tua",
            "Surat Keterangan Duda",
            "Surat Keterangan Janda",
            "Surat Keterangan Menikah",
            "Surat Keterangan Numpang Nikah",
            "Surat Keterangan Belum Menikah",
            "Surat Keterangan Penghasilan",
            "Surat Keterangan Tanah Tidak Dalam Sengketa",
            "Surat Keterangan Tidak Mampu",
            "Surat Keterangan Catatan Kepolisian",
            "Surat Keterangan Izin Berkunjung",
            "Surat Keterangan Kehilangan",
            "Surat Keterangan Bepergian",
            "Surat Keterangan Kepemilikan Sepeda Motor",
            "Surat Keterangan Telah Melakukan Penelitian",
            "Surat Keterangan Perwalian",
            "Surat Keterangan Terdaftar",
            "Surat Keterangan Domisili Usaha",
            "Surat Keterangan Kelahiran"
    };

    private String [] nama_data_surat = new String[]{
            "Surat Keterangan Usaha",
            "Surat Keterangan Kematian",
            "Surat Keterangan Beda Nama",
            "Surat Keterangan Beda Tanggal Lahir",
            "Surat Keterangan Belum Memiliki Rumah",
            "Surat Keterangan Identitas Orang Tua",
            "Surat Keterangan Duda",
            "Surat Keterangan Janda",
            "Surat Keterangan Menikah",
            "Surat Keterangan Numpang Nikah",
            "Surat Keterangan Belum Menikah",
            "Surat Keterangan Penghasilan",
            "Surat Keterangan Tanah Tidak Dalam Sengketa",
            "Surat Keterangan Tidak Mampu",
            "Surat Keterangan Catatan Kepolisian",
            "Surat Keterangan Izin Berkunjung",
            "Surat Keterangan Kehilangan",
            "Surat Keterangan Bepergian",
            "Surat Keterangan Kepemilikan Sepeda Motor",
            "Surat Keterangan Telah Melakukan Penelitian",
            "Surat Keterangan Perwalian/Pengampu",
            "Surat Keterangan Terdaftar",
            "Surat Keterangan Domisili Usaha",
            "Surat Keterangan Kelahiran"
    };


    private  List<Surat> getData() {
        List<Surat> surats = new ArrayList<>();
        Surat surat;

        for (int i=0; i<nama_surat.length; i++){
            surat = new Surat();
            surat.setNamaSurat(nama_surat[i]);
            surat.setImgSrc("/sample/images/img_form.png");
            surats.add(surat);
        }

        return surats;
    }

    private  List<ExcelData> getDataExcel() {
        List<ExcelData> excelDatas = new ArrayList<>();
        ExcelData excelData;

        for (int i=0; i<nama_data_surat.length; i++){
            excelData = new ExcelData();
            excelData.setDataSuratKeterangan(nama_data_surat[i]);
            excelData.setImgDataSuratKeterangan("/sample/images/excel_logo.png");
            excelDatas.add(excelData);
        }

        return excelDatas;
    }

    private List<Surat> getSpecificData(String data){
        List<Surat> surats = new ArrayList<>();
        Surat surat;

        try {
            for (int i=0; i<nama_surat.length; i++){
                if (nama_surat[i].toLowerCase().contains(data)){
                    surat = new Surat();
                    surat.setNamaSurat(nama_surat[i]);
                    surat.setImgSrc("/sample/images/img_form.png");
                    surats.add(surat);
                }
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return surats;
    }

    private List<ExcelData> getSpecificDataExcel(String data){
        List<ExcelData> excelDatas = new ArrayList<>();
        ExcelData excelData;

        try {
            for (int i=0; i<nama_surat.length; i++){
                if (nama_surat[i].toLowerCase().contains(data)){
                    excelData = new ExcelData();
                    excelData.setDataSuratKeterangan(nama_data_surat[i]);
                    excelData.setImgDataSuratKeterangan("/sample/images/excel_logo.png");
                    excelDatas.add(excelData);
                }
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return excelDatas;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        surats.addAll(getData());
        excelDatas.addAll(getDataExcel());

        myListener = new MyListener() {
            @Override
            public void onClickListener(Surat surat, MouseEvent mouseEvent) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/Form.fxml"));
                    Parent root = loader.load();

                    Node node = (Node) mouseEvent.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();

                    FormController formController = loader.getController();
                    formController.setLabelSurat(surat);
                    formController.init(surat.getNamaSurat());



                    stage.getScene().setRoot(root);
                    stage.setMaximized(true);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };



        int col = 0;
        int row = 1;

        try {
            for (int i=0; i< surats.size(); i++){

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/sample/view/Data_item.fxml"));
//                System.out.println(fxmlLoader.getLocation());
                AnchorPane anchorPane = fxmlLoader.load();

                DataItemController dataItemController = fxmlLoader.getController();
                dataItemController.setData(surats.get(i), myListener);

                if(col == 4){
                    col = 0;
                    row++;
                }

                grid.add(anchorPane, col++, row);

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);


                GridPane.setMargin(anchorPane, new Insets(15));

            }
        }catch (IOException e){
            e.printStackTrace();
        }

        search.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    grid.getChildren().clear();

                    if (isSurat == true) {
                        surats.clear();

                        surats.addAll(getSpecificData(search.getText()));

                        myListener = new MyListener() {
                            @Override
                            public void onClickListener(Surat surat, MouseEvent mouseEvent) {
                                try {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/Form.fxml"));
                                    Parent root = loader.load();

                                    Node node = (Node) mouseEvent.getSource();
                                    Stage stage = (Stage) node.getScene().getWindow();

                                    FormController formController = loader.getController();
                                    formController.setLabelSurat(surat);
                                    formController.init(surat.getNamaSurat());


                                    stage.getScene().setRoot(root);
                                    stage.setMaximized(true);
                                    stage.show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        };

                        int col = 0;
                        int row = 1;

                        try {
                            for (int i = 0; i < surats.size(); i++) {
                                FXMLLoader fxmlLoader = new FXMLLoader();
                                fxmlLoader.setLocation(getClass().getResource("/sample/view/Data_item.fxml"));

                                AnchorPane anchorPane = fxmlLoader.load();

                                DataItemController dataItemController = fxmlLoader.getController();
                                dataItemController.setData(surats.get(i), myListener);

                                if (col == 4) {
                                    col = 0;
                                    row++;
                                }

                                grid.add(anchorPane, col++, row);

                                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                                grid.setMaxWidth(Region.USE_PREF_SIZE);

                                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                                grid.setMaxHeight(Region.USE_PREF_SIZE);


                                GridPane.setMargin(anchorPane, new Insets(15));

                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if(isSurat == false){
                        excelDatas.clear();

                        excelDatas.addAll(getSpecificDataExcel(search.getText()));

                        newMyListener = new NewMyListener() {
                            @Override
                            public void onClickListener(ExcelData ED, MouseEvent mouseEvent) {
                                String nama=ED.getDataSuratKeterangan();
                                String dir="";
                                try {
                                    dir = direk()+"\\";
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                                Desktop desktop = Desktop.getDesktop();
                                try {
                                    File f = new File(dir+nama+".xlsx");
                                    desktop.open(f);  // opens application (MSWord) associated with .doc file
                                }
                                catch(Exception ex) {
                                    // WordDocument.this is to refer to outer class's instance from inner class
                                }
                            }
                        };

                        int col = 0;
                        int row = 1;

                        try {
                            for (int i = 0; i < excelDatas.size(); i++) {
                                FXMLLoader fxmlLoader = new FXMLLoader();
                                fxmlLoader.setLocation(getClass().getResource("/sample/view/Excel.fxml"));

                                AnchorPane anchorPane = fxmlLoader.load();

                                ExcelController excelController = fxmlLoader.getController();
                                excelController.setExcel(excelDatas.get(i), newMyListener);

                                if (col == 4) {
                                    col = 0;
                                    row++;
                                }

                                grid.add(anchorPane, col++, row);

                                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                                grid.setMaxWidth(Region.USE_PREF_SIZE);

                                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                                grid.setMaxHeight(Region.USE_PREF_SIZE);


                                GridPane.setMargin(anchorPane, new Insets(15));

                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

}
