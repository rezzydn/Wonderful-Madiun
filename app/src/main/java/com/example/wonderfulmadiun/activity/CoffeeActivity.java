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
import com.example.wonderfulmadiun.adapter.CoffeeAdapter;
import com.example.wonderfulmadiun.model.CoffeeModel;
import com.example.wonderfulmadiun.rest.RestApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CoffeeActivity extends AppCompatActivity implements CoffeeAdapter.onSelectData {

    RecyclerView rvCoffee;
    CoffeeAdapter coffeeAdapter;
    ProgressDialog progressDialog;
    List<CoffeeModel> modelCoffee = new ArrayList<>();
    Toolbar tbCoffee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);

        tbCoffee = findViewById(R.id.toolbar_coffee);
        tbCoffee.setTitle("Daftar Coffee Kota Madiun & Sekitar");
        setSupportActionBar(tbCoffee);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Mohon Tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data...");

        rvCoffee = findViewById(R.id.rvCoffee);
        rvCoffee.setHasFixedSize(true);
        rvCoffee.setLayoutManager(new LinearLayoutManager(this));

        getCoffee();
    }

    private void getCoffee() {
        progressDialog.show();
        AndroidNetworking.get(RestApi.Coffee)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressDialog.dismiss();
                            JSONArray playerArray = response.getJSONArray("coffee_shop");
                            for (int i = 0; i < playerArray.length(); i++) {
                                JSONObject temp = playerArray.getJSONObject(i);
                                CoffeeModel dataApi = new CoffeeModel();
                                dataApi.setTxtNamaCoffee(temp.getString("nama"));
                                dataApi.setTxtAlamatCoffee(temp.getString("alamat"));
                                dataApi.setTxtJam(temp.getString("jam_buka_tutup"));
                                dataApi.setKoordinat(temp.getString("koordinat"));
                                dataApi.setGambarCoffee(temp.getString("url_gambar"));
                                modelCoffee.add(dataApi);
                                showCoffee();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(CoffeeActivity.this,
                                    "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Toast.makeText(CoffeeActivity.this,
                                "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showCoffee() {
        coffeeAdapter = new CoffeeAdapter(CoffeeActivity.this, modelCoffee, this);
        rvCoffee.setAdapter(coffeeAdapter);
    }

    @Override
    public void onSelected(CoffeeModel modelCoffee) {
        Intent intent = new Intent(CoffeeActivity.this, DetailCoffeeActivity.class);
        intent.putExtra("detailCoffee", modelCoffee);
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