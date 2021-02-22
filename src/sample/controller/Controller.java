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
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import sample.modal.ExcelData;
import sample.modal.Surat;
import sample.utils.MyListener;
import sample.utils.NewMyListener;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TextField search;

    @FXML
    private GridPane grid;

    @FXML
    void chooser(ActionEvent event) throws IOException {
            Stage stage = new Stage();
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(stage);

            System.out.println(selectedDirectory.getAbsolutePath());

            FileWriter myWriter = new FileWriter("excel.txt");

            myWriter.write(selectedDirectory.getAbsolutePath());
            myWriter.close();
            System.out.println(selectedDirectory.getAbsolutePath());
    }

    @FXML
    void pageLaporan(ActionEvent event) {
        isSurat = false;
        grid.getChildren().clear();

        int col = 0;
        int row = 1;

        try {
            for (int i=0; i< excelDatas.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../view/Excel.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                ExcelController excelController = fxmlLoader.getController();
                excelController.setExcel(excelDatas.get(i), newMyListener);

                if(col == 3){
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


                GridPane.setMargin(anchorPane, new Insets(30));

            }
        }catch (IOException e){
            e.printStackTrace();
        }
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
                fxmlLoader.setLocation(getClass().getResource("../view/Data_item.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                DataItemController dataItemController = fxmlLoader.getController();
                dataItemController.setData(surats.get(i), myListener);

                if(col == 3){
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


                GridPane.setMargin(anchorPane, new Insets(30));

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
            "Surat Keterangan Cerai Lingkungan",
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

    private String [] nama_data_surat = new String[]{
            "Data Surat Keterangan Usaha",
            "Data Surat Keterangan Kematian",
            "Data Surat Keterangan Beda Nama",
            "Data Surat Keterangan Beda Tanggal Lahir",
            "Data Surat Keterangan Belum Memiliki Rumah",
            "Data Surat Keterangan Identitas Orang Tua",
            "Data Surat Keterangan Duda",
            "Data Surat Keterangan Janda",
            "Data Surat Keterangan Menikah",
            "Data Surat Keterangan Numpang Nikah",
            "Data Surat Keterangan Belum Menikah",
            "Data Surat Keterangan Penghasilan",
            "Data Surat Keterangan Tanah Tidak Dalam Sengketa",
            "Data Surat Keterangan Tidak Mampu",
            "Data Surat Keterangan Cerai Lingkungan",
            "Data Surat Keterangan Catatan Kepolisian",
            "Data Surat Keterangan Izin Berkunjung",
            "Data Surat Keterangan Kehilangan",
            "Data Surat Keterangan Bepergian",
            "Data Surat Keterangan Kepemilikan Sepeda Motor",
            "Data Surat Keterangan Telah Melakukan Penelitian",
            "Data Surat Keterangan Perwalian/Pengampu",
            "Data Surat Keterangan Terdaftar",
            "Data Surat Keterangan Domisili Usaha",
            "Data Surat Keterangan Kelahiran"
    };


    private  List<Surat> getData() {
        List<Surat> surats = new ArrayList<>();
        Surat surat;

        for (int i=0; i<nama_surat.length; i++){
            surat = new Surat();
            surat.setNamaSurat(nama_surat[i]);
            surat.setImgSrc("../images/img_form.png");
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
            excelData.setImgDataSuratKeterangan("../images/excel_logo.png");
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
                    surat.setImgSrc("../images/img_form.png");
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
                    excelData.setImgDataSuratKeterangan("../images/excel_logo.png");
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

        newMyListener = new NewMyListener() {
            @Override
            public void onClickListener(MouseEvent mouseEvent) {
                System.out.println("Clicked");
            }
        };
        myListener = new MyListener() {
            @Override
            public void onClickListener(Surat surat, MouseEvent mouseEvent) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Form.fxml"));
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
                fxmlLoader.setLocation(getClass().getResource("../view/Data_item.fxml"));

                AnchorPane anchorPane = fxmlLoader.load();

                DataItemController dataItemController = fxmlLoader.getController();
                dataItemController.setData(surats.get(i), myListener);

                if(col == 3){
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


                GridPane.setMargin(anchorPane, new Insets(30));

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

                        int col = 0;
                        int row = 1;

                        try {
                            for (int i = 0; i < surats.size(); i++) {
                                FXMLLoader fxmlLoader = new FXMLLoader();
                                fxmlLoader.setLocation(getClass().getResource("../view/Data_item.fxml"));

                                AnchorPane anchorPane = fxmlLoader.load();

                                DataItemController dataItemController = fxmlLoader.getController();
                                dataItemController.setData(surats.get(i), myListener);

                                if (col == 3) {
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


                                GridPane.setMargin(anchorPane, new Insets(30));

                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if(isSurat == false){
                        excelDatas.clear();

                        excelDatas.addAll(getSpecificDataExcel(search.getText()));

                        int col = 0;
                        int row = 1;

                        try {
                            for (int i = 0; i < excelDatas.size(); i++) {
                                FXMLLoader fxmlLoader = new FXMLLoader();
                                fxmlLoader.setLocation(getClass().getResource("../view/Excel.fxml"));

                                AnchorPane anchorPane = fxmlLoader.load();

                                ExcelController excelController = fxmlLoader.getController();
                                excelController.setExcel(excelDatas.get(i), newMyListener);

                                if (col == 3) {
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


                                GridPane.setMargin(anchorPane, new Insets(30));

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
