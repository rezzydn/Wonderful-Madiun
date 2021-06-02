package com.example.wonderfulmadiun.model;

import java.io.Serializable;



public class KulinerModel implements Serializable {

    private String txtNamaKuliner, txtAlamatKuliner, txtJam, Koordinat, GambarKuliner;

    public String getTxtNamaKuliner() {
        return txtNamaKuliner;
    }

    public void setTxtNamaKuliner(String txtNamaKuliner) {
        this.txtNamaKuliner = txtNamaKuliner;
    }

    public String getTxtAlamatKuliner() {
        return txtAlamatKuliner;
    }

    public void setTxtAlamatKuliner(String txtAlamatKuliner) {
        this.txtAlamatKuliner = txtAlamatKuliner;
    }

    public String getTxtJam() {
        return txtJam;
    }

    public void setTxtJam(String txtJam) {
        this.txtJam = txtJam;
    }

    public String getKoordinat() {
        return Koordinat;
    }

    public void setKoordinat(String koordinat) {
        this.Koordinat = koordinat;
    }

    public String getGambarKuliner() {
        return GambarKuliner;
    }

    public void setGambarKuliner(String gambarKuliner) {
        this.GambarKuliner = gambarKuliner;
    }
}

