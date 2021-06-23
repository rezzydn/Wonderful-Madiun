package com.example.wonderfulmadiun.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.wonderfulmadiun.R;
import com.example.wonderfulmadiun.adapter.PrayplaceAdapter;
import com.example.wonderfulmadiun.rest.RestApi;
import com.example.wonderfulmadiun.adjustment.MarginAdjustment;
import com.example.wonderfulmadiun.model.PrayplaceModel;
import com.example.wonderfulmadiun.utility.Tools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PrayplaceActivity extends AppCompatActivity implements PrayplaceAdapter.onSelectData {

    RecyclerView rvPrayplace;
    MarginAdjustment gridMargin;
    PrayplaceAdapter prayplaceAdapter;
    ProgressDialog progressDialog;
    List<PrayplaceModel> modelPrayplace = new ArrayList<>();
    Toolbar tbPrayplace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayplace);

        tbPrayplace = findViewById(R.id.toolbar_prayplace);
        tbPrayplace.setTitle("Daftar Tempat Ibadah Kota Madiun & Sekitar");
        setSupportActionBar(tbPrayplace);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Mohon Tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang menampilkan data...");

        rvPrayplace = findViewById(R.id.rvPrayplace);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this,
                2, RecyclerView.VERTICAL, false);
        rvPrayplace.setLayoutManager(mLayoutManager);
        gridMargin = new MarginAdjustment(2, Tools.dp2px(this, 4));
        rvPrayplace.addItemDecoration(gridMargin);
        rvPrayplace.setHasFixedSize(true);

        getPrayplace();
    }

    private void getPrayplace() {
        progressDialog.show();
        AndroidNetworking.get(RestApi.Prayplace)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            progressDialog.dismiss();
                            JSONArray playerArray = response.getJSONArray("tempatibadah");
                            for (int i = 0; i < playerArray.length(); i++) {
                                JSONObject temp = playerArray.getJSONObject(i);
                                PrayplaceModel dataApi = new PrayplaceModel();
                                dataApi.setIdPrayplace(temp.getString("id"));
                                dataApi.setTxtNamaPrayplace(temp.getString("nama"));
                                dataApi.setKategoriPrayplace(temp.getString("kategori"));
                                dataApi.setTxtAlamatPrayplace(temp.getString("alamat"));
                                dataApi.setTxtDaerah(temp.getString("daerah"));
                                dataApi.setKoordinat(temp.getString("koordinat"));
                                dataApi.setGambarPrayplace(temp.getString("url_gambar"));
                                modelPrayplace.add(dataApi);
                                showPrayplace();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(PrayplaceActivity.this,
                                    "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Toast.makeText(PrayplaceActivity.this,
                                "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void showPrayplace() {
        prayplaceAdapter = new PrayplaceAdapter(PrayplaceActivity.this, modelPrayplace, this);
        rvPrayplace.setAdapter(prayplaceAdapter);
    }

    @Override
    public void onSelected(PrayplaceModel prayplaceModel) {
        Intent intent = new Intent(PrayplaceActivity.this, DetailPrayplaceActivity.class);
        intent.putExtra("detailPrayplace", prayplaceModel);
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
