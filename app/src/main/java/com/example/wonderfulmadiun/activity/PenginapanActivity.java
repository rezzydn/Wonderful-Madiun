package com.example.wonderfulmadiun.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.wonderfulmadiun.R;
import com.example.wonderfulmadiun.adapter.PenginapanAdapter;
import com.example.wonderfulmadiun.rest.RestApi;
import com.example.wonderfulmadiun.model.PenginapanModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PenginapanActivity extends AppCompatActivity implements PenginapanAdapter.onSelectData {

    RecyclerView rvHotel;
    PenginapanAdapter hotelAdapter;
    ProgressDialog progressDialog;
    List<PenginapanModel> modelHotel = new ArrayList<>();
    Toolbar tbHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penginapan);

        tbHotel = findViewById(R.id.toolbar_hotel);
        tbHotel.setTitle("Daftar Hotel Kota Madiun & Sekitar");
        setSupportActionBar(tbHotel);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Mohon Tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data...");

        rvHotel = findViewById(R.id.rvHotel);
        rvHotel.setHasFixedSize(true);
        rvHotel.setLayoutManager(new LinearLayoutManager(this));

        getHotel();
    }

    private void getHotel() {
        progressDialog.show();
        AndroidNetworking.get(RestApi.Penginapan)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressDialog.dismiss();
                            JSONArray playerArray = response.getJSONArray("penginapan");
                            for (int i = 0; i < playerArray.length(); i++) {
                                JSONObject temp = playerArray.getJSONObject(i);
                                PenginapanModel dataApi = new PenginapanModel();
                                dataApi.setTxtNamaHotel(temp.getString("nama"));
                                dataApi.setTxtAlamatHotel(temp.getString("alamat"));
                                dataApi.setTxtNoTelp(temp.getString("no_telp"));
                                dataApi.setKoordinat(temp.getString("koordinat"));
                                dataApi.setGambarHotel(temp.getString("url_gambar"));
                                dataApi.setLinkHotel(temp.getString("book_link"));
                                modelHotel.add(dataApi);
                                showHotel();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(PenginapanActivity.this,
                                    "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Toast.makeText(PenginapanActivity.this,
                                "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showHotel() {
        hotelAdapter = new PenginapanAdapter(PenginapanActivity.this, modelHotel, this);
        rvHotel.setAdapter(hotelAdapter);
    }

    @Override
    public void onSelected(PenginapanModel modelHotel) {
        Intent intent = new Intent(PenginapanActivity.this, DetailPenginapanActivity.class);
        intent.putExtra("detailHotel", modelHotel);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}