package com.example.wonderfulmadiun.model;

import java.io.Serializable;


public class WisataModel implements Serializable {

    private String idWisata, txtNamaWisata, GambarWisata, KategoriWisata, DeskripsiWisata;

    public String getIdWisata() {
        return idWisata;
    }

    public void setIdWisata(String idWisata) {
        this.idWisata = idWisata;
    }

    public String getTxtNamaWisata() {
        return txtNamaWisata;
    }

    public void setTxtNamaWisata(String txtNamaWisata) {
        this.txtNamaWisata = txtNamaWisata;
    }

    public String getGambarWisata() {
        return GambarWisata;
    }

    public void setGambarWisata(String gambarWisata) {
        GambarWisata = gambarWisata;
    }

    public String getKategoriWisata() {
        return KategoriWisata;
    }

    public void setKategoriWisata(String kategoriWisata) {
        KategoriWisata = kategoriWisata;
    }

    public String getDeskripsiWisata() {
        return DeskripsiWisata;
    }

    public void setDeskripsiWisata(String deskripsiWisata) {
        DeskripsiWisata = deskripsiWisata;
    }
}
