package com.example.wonderfulmadiun.model;

import android.widget.LinearLayout;

import java.io.Serializable;



public class PerbelanjaanModel implements Serializable {

    private String txtNamaShop, txtAlamatShop, txtNoTelp, Koordinat, GambarShop;

    public String getTxtNamaShop() {
        return txtNamaShop;
    }

    public void setTxtNamaShop(String txtNamaShop) {
        this.txtNamaShop = txtNamaShop;
    }

    public String getTxtAlamatShop() {
        return txtAlamatShop;
    }

    public void setTxtAlamatShop(String txtAlamatShop) {
        this.txtAlamatShop = txtAlamatShop;
    }

    public String getTxtNoTelp() {
        return txtNoTelp;
    }

    public void setTxtNoTelp(String txtNoTelp) {
        this.txtNoTelp = txtNoTelp;
    }

    public String getKoordinat() {
        return Koordinat;
    }

    public void setKoordinat(String koordinat) {
        this.Koordinat = koordinat;
    }

    public String getGambarShop() {
        return GambarShop;
    }

    public void setGambarShop(String gambarShop) {
        this.GambarShop = gambarShop;
    }

}

