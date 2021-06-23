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
import com.example.wonderfulmadiun.adapter.PerbelanjaanAdapter;
import com.example.wonderfulmadiun.rest.RestApi;
import com.example.wonderfulmadiun.model.PerbelanjaanModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PerbelanjaanActivity extends AppCompatActivity implements PerbelanjaanAdapter.onSelectData {

    RecyclerView rvShop;
    PerbelanjaanAdapter shopAdapter;
    ProgressDialog progressDialog;
    List<PerbelanjaanModel> modelShop = new ArrayList<>();
    Toolbar tbShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perbelanjaan);

        tbShop = findViewById(R.id.toolbar_shop);
        tbShop.setTitle("Daftar Tempat Belanja Kota Madiun & Sekitar");
        setSupportActionBar(tbShop);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Mohon Tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data...");

        rvShop = findViewById(R.id.rvShop);
        rvShop.setHasFixedSize(true);
        rvShop.setLayoutManager(new LinearLayoutManager(this));

        getHotel();
    }

    private void getHotel() {
        progressDialog.show();
        AndroidNetworking.get(RestApi.Perbelanjaan)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressDialog.dismiss();
                            JSONArray playerArray = response.getJSONArray("perbelanjaan");
                            for (int i = 0; i < playerArray.length(); i++) {
                                JSONObject temp = playerArray.getJSONObject(i);
                                PerbelanjaanModel dataApi = new PerbelanjaanModel();
                                dataApi.setTxtNamaShop(temp.getString("nama"));
                                dataApi.setTxtAlamatShop(temp.getString("alamat"));
                                dataApi.setTxtNoTelp(temp.getString("daerah"));
                                dataApi.setKoordinat(temp.getString("koordinat"));
                                dataApi.setGambarShop(temp.getString("url_gambar"));
                                modelShop.add(dataApi);
                                showShop();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(PerbelanjaanActivity.this,
                                    "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Toast.makeText(PerbelanjaanActivity.this,
                                "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showShop() {
        shopAdapter = new PerbelanjaanAdapter(PerbelanjaanActivity.this, modelShop, this);
        rvShop.setAdapter(shopAdapter);
    }

    @Override
    public void onSelected(PerbelanjaanModel modelShop) {
        Intent intent = new Intent(PerbelanjaanActivity.this, DetailPerbelanjaanActivity.class);
        intent.putExtra("detailShop", modelShop);
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
