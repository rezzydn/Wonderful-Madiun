package com.example.wonderfulmadiun.model;

import java.io.Serializable;


public class PrayplaceModel implements Serializable {

    private String idPrayplace, txtNamaPrayplace, txtAlamatPrayplace, DaerahPrayplace, Koordinat, GambarPrayplace, KategoriPrayplace;

    public String getIdPrayplace() {
        return idPrayplace;
    }

    public void setIdPrayplace(String idPrayplace) {
        this.idPrayplace = idPrayplace;
    }

    public String getTxtNamaPrayplace() {
        return txtNamaPrayplace;
    }

    public void setTxtNamaPrayplace(String txtNamaPrayplace) {
        this.txtNamaPrayplace = txtNamaPrayplace;
    }

    public String getTxtAlamatPrayplace() {
        return txtAlamatPrayplace;
    }

    public void setTxtAlamatPrayplace(String txtAlamatPrayplace) {
        this.txtAlamatPrayplace = txtAlamatPrayplace;
    }

    public String getTxtDaerah() {
        return DaerahPrayplace;
    }

    public void setTxtDaerah(String DaerahPrayplace) {
        this.DaerahPrayplace = DaerahPrayplace;
    }

    public String getKoordinat() {
        return Koordinat;
    }

    public void setKoordinat(String koordinat) {
        this.Koordinat = koordinat;
    }

    public String getGambarPrayplace() {
        return GambarPrayplace;
    }

    public void setGambarPrayplace(String gambarPrayplace) {
        this.GambarPrayplace = gambarPrayplace;
    }

    public String getKategoriPrayplace() {
        return KategoriPrayplace;
    }

    public void setKategoriPrayplace(String kategoriPrayplace) {
        this.KategoriPrayplace = kategoriPrayplace;
    }
}
