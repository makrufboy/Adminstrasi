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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private String txt;

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

        FileInputStream file = new FileInputStream(new File("src/sample/template/"+txt+".xlsx"));

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
//            File theDir = new File("D:\\netbean\\tes\\coba");
//            String dir= "D:\\netbean\\tes\\coba\\Keluaran ";
//            if (!theDir.exists()){
//                theDir.mkdirs();
//            }
            FileInputStream fis=new FileInputStream("excel.txt");
            Scanner sc=new Scanner(fis);    //file to be scanned
            String dir=sc.nextLine();
            sc.close();

            FileOutputStream out = new FileOutputStream(new File("src/sample/template/"+txt+".xlsx"));
            workbook.write(out);
            out.close();
            FileOutputStream out2 = new FileOutputStream(new File(dir+"\\"+txt+".xlsx"));
            workbook.write(out2);
            out2.close();
//            System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void replace(Map<String, String> peta) throws FileNotFoundException {
        String input = "src\\sample\\template\\"+txt+".docx";

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
                case "No Lingkungan":
                    document.replace("nlx", (String) value, false, true);
                    break;
                case "Nomor Surat":
                    document.replace("nsx", (String) value, false, true);
                    break;
                case "Tgl Meninggal":
                    document.replace("tgmx", (String) value, false, true);
                    break;
                case "Bukti":
                    document.replace("bux", (String) value, false, true);
                    break;
                case "Nama 2":
                    document.replace("namx2", (String) value, false, true);
                    break;
                case "Bukti 2":
                    document.replace("bux2", (String) value, false, true);
                    break;
                case "Tempat / Tgl Lahir 2":
                    document.replace("ttlx2", (String) value, false, true);
                    break;
                case "Nama Perusahaan/Usaha":
                    document.replace("npux", (String) value, false, true);
                    break;
                case "Jenis Usaha":
                    document.replace("juxx", (String) value, false, true);
                    break;
                case "Alamat Usaha":
                    document.replace("aluxx", (String) value, false, true);
                    break;
                case "Status Bangunan":
                    document.replace("sbx", (String) value, false, true);
                    break;
                case "No. Akte Pendirian":
                    document.replace("napx", (String) value, false, true);
                    break;
                case "Tanggal /Tahun Berdiri":
                    document.replace("ttbx", (String) value, false, true);
                    break;
                case "Penanggung Jawab":
                    document.replace("penjx", (String) value, false, true);
                    break;
                case "Status dari":
                    document.replace("sdx", (String) value, false, true);
                    break;
                case "Jenis Kelamin 2":
                    document.replace("jkx2", (String) value, false, true);
                    break;
                case "Agama / Kebangsaan 2":
                    document.replace("agx2", (String) value, false, true);
                    break;
                case "Pekerjaan 2":
                    document.replace("pjx2", (String) value, false, true);
                    break;
                case "NIK 2":
                    document.replace("nikx2", (String) value, false, true);
                    break;
                case "Alamat 2":
                    document.replace("alx2", (String) value, false, true);
                    break;
                case "Nama Istri":
                    document.replace("nmxi", (String) value, false, true);
                    break;
                case "Nama Suami":
                    document.replace("nmxi", (String) value, false, true);
                    break;
                case "Status":
                    document.replace("staxxx", (String) value, false, true);
                    break;
                case "Alamat Numpang":
                    document.replace("alanumx", (String) value, false, true);
                    break;
                case "Jumlah Penghasilan":
                    document.replace("penghaxxx", (String) value, false, true);
                    break;
                case "Luas Tanah":
                    document.replace("luasxx", (String) value, false, true);
                    break;
                case "SHM":
                    document.replace("shmxx", (String) value, false, true);
                    break;
                case "Alamat Tanah":
                    document.replace("alatanx", (String) value, false, true);
                    break;
                case "Keperluan":
                    document.replace("keperxxx", (String) value, false, true);
                    break;
                case "Nama Istri/Suami":
                    document.replace("issumx", (String) value, false, true);
                    break;
                case "Terdakwa":
                    document.replace("terdakx", (String) value, false, true);
                    break;
                case "Hubungan dengan Terdakwa":
                    document.replace("hubterdakx", (String) value, false, true);
                    break;
                case "Nomor Perkara":
                    document.replace("noperx", (String) value, false, true);
                    break;
                case "Barang yang Hilang":
                    document.replace("barhilx", (String) value, false, true);
                    break;
                case "Atas Nama":
                    document.replace("atasxxx", (String) value, false, true);
                    break;
                case "Terakhir Barang Ditemukan":
                    document.replace("terbarx", (String) value, false, true);
                    break;
                case "lokasi Meninggal":
                    document.replace("lomenxx", (String) value, false, true);
                    break;
                case "Tanggal Bepergian":
                    document.replace("tglxxx", (String) value, false, true);
                    break;
                case "Judul Penelitian":
                    document.replace("judxx", (String) value, false, true);
                    break;
                case "TNKB":
                    document.replace("tnkbx", (String) value, false, true);
                    break;
                case "Bepergian ke":
                    document.replace("pergixx", (String) value, false, true);
                    break;
                case "Kecamatan":
                    document.replace("matanx", (String) value, false, true);
                    break;
                case "Kabupaten/Kota":
                    document.replace("kotaxxx", (String) value, false, true);
                    break;
                case "Provinsi":
                    document.replace("provinx", (String) value, false, true);
                    break;
                case "Pengikut":
                    document.replace("pengekxx", (String) value, false, true);
                    break;
                case "Merk":
                    document.replace("merkxx", (String) value, false, true);
                    break;
                case "Jenis/Model":
                    document.replace("jenmodlx", (String) value, false, true);
                    break;
                case "Tahun Pembuatan":
                    document.replace("thnpemx", (String) value, false, true);
                    break;
                case "Isi Silinder":
                    document.replace("silinx", (String) value, false, true);
                    break;
                case "nomor rangka":
                    document.replace("rangx", (String) value, false, true);
                    break;
                case "Nomor Mesin":
                    document.replace("mesxxx", (String) value, false, true);
                    break;
                case "Warna":
                    document.replace("warnx", (String) value, false, true);
                    break;
                case "Bahan Bakar":
                    document.replace("bakarx", (String) value, false, true);
                    break;
                case "BPKB No:":
                    document.replace("bpkxx", (String) value, false, true);
                    break;
                case "Nomer HP":
                    document.replace("hpxx", (String) value, false, true);
                    break;
                case "Jurusan":
                    document.replace("jursxx", (String) value, false, true);
                    break;
                case "Nama Sekolah":
                    document.replace("seklxx", (String) value, false, true);
                    break;
                case "Tanggal Mulai":
                    document.replace("mulxx", (String) value, false, true);
                    break;
                case "Tanggal Selesai":
                    document.replace("selexx", (String) value, false, true);
                    break;
                case "Hubungan":
                    document.replace("hubxx", (String) value, false, true);
                    break;
                case "Nama Kelompok/Lembaga":
                    document.replace("klpxx", (String) value, false, true);
                    break;
                case "Tahun Berdiri":
                    document.replace("berdirixxx", (String) value, false, true);
                    break;
                case "Bidang Kegiatan":
                    document.replace("bidangxxx", (String) value, false, true);
                    break;
                case "Tanggal Terakhir":
                    document.replace("tglterxx", (String) value, false, true);
                    break;
                case "Anak yang Ke":
                    document.replace("anakxx", (String) value, false, true);
                    break;
                case "Nama Ayah":
                    document.replace("ayahxx", (String) value, false, true);
                    break;
                case "Nama Ibu":
                    document.replace("ibuxx", (String) value, false, true);
                    break;
                case "Tgl Menikah":
                    document.replace("menikahxxx", (String) value, false, true);
                    break;
                case "Lingkungan":
                    document.replace("lingxxx", (String) value, false, true);
                    break;
                case "Nama Orang Tua":
                    document.replace("namx2", (String) value, false, true);
                    break;
                case "Jenis Kelamin Orang Tua":
                    document.replace("jkx2", (String) value, false, true);
                    break;
                case "Tempat / Tgl Lahir Orang Tua":
                    document.replace("ttlx2", (String) value, false, true);
                    break;
                case "Agama / Kebangsaan Orang Tua":
                    document.replace("agx2", (String) value, false, true);
                    break;
                case "Pekerjaan Orang Tua":
                    document.replace("pjx2", (String) value, false, true);
                    break;
                case "NIK Orang Tua":
                    document.replace("nikx2", (String) value, false, true);
                    break;
                case "Alamat Orang Tua":
                    document.replace("alx2", (String) value, false, true);
                    break;
                case "Nama Wali":
                    document.replace("namx2", (String) value, false, true);
                    break;
                case "Jenis Kelamin Wali":
                    document.replace("jkx2", (String) value, false, true);
                    break;
                case "Tempat / Tgl Lahir Wali":
                    document.replace("ttlx2", (String) value, false, true);
                    break;
                case "Agama / Kebangsaan Wali":
                    document.replace("agx2", (String) value, false, true);
                    break;
                case "Pekerjaan Wali":
                    document.replace("pjx2", (String) value, false, true);
                    break;
                case "NIK Wali":
                    document.replace("nikx2", (String) value, false, true);
                    break;
                case "Alamat Wali":
                    document.replace("alx2", (String) value, false, true);
                    break;
                case "Universitas":
                    document.replace("univx", (String) value, false, true);
                    break;
                case "Nama Pasangan":
                    document.replace("namx2", (String) value, false, true);
                    break;
                case "Jenis Kelamin Pasangan":
                    document.replace("jkx2", (String) value, false, true);
                    break;
                case "Tempat / Tgl Lahir Pasangan":
                    document.replace("ttlx2", (String) value, false, true);
                    break;
                case "Agama / Kebangsaan Pasangan":
                    document.replace("agx2", (String) value, false, true);
                    break;
                case "Pekerjaan Pasangan":
                    document.replace("pjx2", (String) value, false, true);
                    break;
                case "NIK Pasangan":
                    document.replace("nikx2", (String) value, false, true);
                    break;
                case "Alamat Pasangan":
                    document.replace("alx2", (String) value, false, true);
                    break;
                default:

                    break;
            }
        }
        LocalDate myDateObj = LocalDate.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = myDateObj.format(myFormatObj);
        int tahun = myDateObj.getYear();
        int bulan = myDateObj.getMonthValue();
        int hari = myDateObj.getDayOfMonth();
//        document.replace("tanggaxx", formattedDate, false, true);
        document.replace("TAHUNXX", String.valueOf(tahun), false, true);
        switch (bulan){
            case 1:
                document.replace("BULANXX","I", false, true);
                document.replace("tanggaxx", hari+" "+"Januari "+tahun, false, true);
                break;
            case 2:
                document.replace("BULANXX","II", false, true);
                document.replace("tanggaxx", hari+" "+"Februari "+tahun, false, true);
                break;
            case 3:
                document.replace("BULANXX","III", false, true);
                document.replace("tanggaxx", hari+" "+"Maret "+tahun, false, true);
                break;
            case 4:
                document.replace("BULANXX","IV", false, true);
                document.replace("tanggaxx", hari+" "+"April "+tahun, false, true);
                break;
            case 5:
                document.replace("BULANXX","V", false, true);
                document.replace("tanggaxx", hari+" "+"Mei "+tahun, false, true);
                break;
            case 6:
                document.replace("BULANXX","VI", false, true);
                document.replace("tanggaxx", hari+" "+"Juni "+tahun, false, true);
                break;
            case 7:
                document.replace("BULANXX","VII", false, true);
                document.replace("tanggaxx", hari+" "+"Juli  "+tahun, false, true);
                break;
            case 8:
                document.replace("BULANXX","VIII", false, true);
                document.replace("tanggaxx", hari+" "+"Agustus  "+tahun, false, true);
                break;
            case 9:
                document.replace("BULANXX","IX", false, true);
                document.replace("tanggaxx", hari+" "+"September  "+tahun, false, true);
                break;
            case 10:
                document.replace("BULANXX","X", false, true);
                document.replace("tanggaxx", hari+" "+"Oktober  "+tahun, false, true);
                break;
            case 11:
                document.replace("BULANXX","XI", false, true);
                document.replace("tanggaxx", hari+" "+"November  "+tahun, false, true);
                break;
            case 12:
                document.replace("BULANXX","XII", false, true);
                document.replace("tanggaxx", hari+" "+"Desember  "+tahun, false, true);
                break;
            default:
                break;
        }


        //save the document
//        File theDir = new File("D:\\netbean\\tes\\coba");
//        String dir= "D:\\netbean\\tes\\coba\\Keluaran ";
//        if (!theDir.exists()){
//            theDir.mkdirs();
//        }
        FileInputStream fis=new FileInputStream("excel.txt");
        Scanner sc=new Scanner(fis);    //file to be scanned
        String dir=sc.nextLine();
        sc.close();
        String output = dir+"\\"+txt+".docx";
        document.saveToFile(output,FileFormat.Docx);
        open(output);
    }

    public void setColumnName(String testText) {
//        System.out.println(testText);

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
        }else if(testText.equals("Surat Keterangan Perwalian")){
            this.fieldKolomSurat = FieldSurat.getFieldSuratKeteranganPerwalian();
        }else if(testText.equals("Surat Keterangan Terdaftar")){
            this.fieldKolomSurat = FieldSurat.getFieldSuratKeteranganTerdaftar();
        }else if(testText.equals("Surat Keterangan Domisili Usaha")){
            this.fieldKolomSurat = FieldSurat.getFieldSuratKeteranganDomisiliUsaha();
        }else if(testText.equals("Surat Keterangan Kelahiran")){
            this.fieldKolomSurat = FieldSurat.getFieldSuratKeteranganKelahiran();
        }else if(testText.equals("Surat Keterangan Janda")){
            this.fieldKolomSurat = FieldSurat.getFieldSuratKeteranganJanda();
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
        txt=text;
        fieldSurats.addAll(getFieldSurats());


        int col = 0;
        int row = 1;

        try {
            for (int i=0; i<fieldSurats.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/sample/view/Field_item.fxml"));

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
