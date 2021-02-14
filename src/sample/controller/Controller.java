package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import sample.modal.Surat;
import sample.utils.MyListener;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private MyListener myListener;
    private List<Surat> surats = new ArrayList<>();
    private String [] nama_surat = new String[]{
            "Surat Keterangan Usaha",
            "Surat Keterangan Menikah",
            "Surat Keterangan Belum Menikah",
            "Surat Keterangan Rumah",
            "Surat Keterangan Badan Usaha",
            "Surat Keterangan Anak",
            "Surat Keterangan Cerai",
            "Surat Keterangan Orangtua",
            "Surat Keterangan Dinas"
    };


    private  List<Surat> getData() {
        List<Surat> surats = new ArrayList<>();
        Surat surat;

        for (int i=0; i<9; i++){
            surat = new Surat();
            surat.setNamaSurat(nama_surat[i]);
            surat.setImgSrc("../images/img_form.png");
            surats.add(surat);
        }

        return surats;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        surats.addAll(getData());

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


                GridPane.setMargin(anchorPane, new Insets(20));

            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
