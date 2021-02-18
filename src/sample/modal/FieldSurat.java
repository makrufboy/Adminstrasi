package sample.modal;

import java.util.ArrayList;

public class FieldSurat {
    private String fieldFormSurat;

    public String getFieldFormSurat() {
        return fieldFormSurat;
    }

    public void setFieldFormSurat(String fieldFormSurat) {

        this.fieldFormSurat = fieldFormSurat;
    }

    public static String [] fieldSuratKeteranganUsaha = new String[]{
            "Nama",
            "Jenis Kelamin",
            "Tempat / Tgl Lahir",
            "Agama / Kebangsaan",
            "Pekerjaan",
            "NIK",
            "Alamat",
            "Nomer Lingkungan"
    };



    public static String [] fieldSuratKeteranganMenikah = new String[]{
            "Nama"
    };

    public String getstring(){ return this.fieldFormSurat;}

    public static String[] getFieldSuratKeteranganUsaha() {
        return fieldSuratKeteranganUsaha;
    }

    public static String[] getFieldSuratKeteranganMenikah() {
        return fieldSuratKeteranganMenikah;
    }
}
