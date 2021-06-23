package com.example.wonderfulmadiun.model;

import java.io.Serializable;



public class CoffeeModel implements Serializable {

    private String txtNamaCoffee, txtAlamatCoffee, txtJam, Koordinat, GambarCoffee;

    public String getTxtNamaCoffee() {
        return txtNamaCoffee;
    }

    public void setTxtNamaCoffee(String txtNamaCoffee) {
        this.txtNamaCoffee = txtNamaCoffee;
    }

    public String getTxtAlamatCoffee() {
        return txtAlamatCoffee;
    }

    public void setTxtAlamatCoffee(String txtAlamatCoffee) {
        this.txtAlamatCoffee = txtAlamatCoffee;
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

    public String getGambarCoffee() {
        return GambarCoffee;
    }

    public void setGambarCoffee(String gambarCoffee) {
        this.GambarCoffee = gambarCoffee;
    }
}

