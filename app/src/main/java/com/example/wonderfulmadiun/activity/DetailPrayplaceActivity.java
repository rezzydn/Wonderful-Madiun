package com.example.wonderfulmadiun.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.wonderfulmadiun.R;
import com.example.wonderfulmadiun.rest.RestApi;
import com.example.wonderfulmadiun.model.PrayplaceModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailPrayplaceActivity extends AppCompatActivity implements OnMapReadyCallback {

    Toolbar tbDetailPrayplace;
    GoogleMap googleMaps;
    TextView tvNamaPrayplace, tvAddressPrayplace;
    ImageView imgPrayplace;
    String idPrayplace, NamaPrayplace, AddressPrayplace;
    PrayplaceModel modelPrayplace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_prayplace);

        tbDetailPrayplace = findViewById(R.id.tbDetailPrayplace);
        tbDetailPrayplace.setTitle("Detail Tempat Ibadah");
        setSupportActionBar(tbDetailPrayplace);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //show maps
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        modelPrayplace = (PrayplaceModel) getIntent().getSerializableExtra("detailPrayplace");
        if (modelPrayplace != null) {
            idPrayplace = modelPrayplace.getIdPrayplace();
            NamaPrayplace = modelPrayplace.getTxtNamaPrayplace();
            AddressPrayplace = modelPrayplace.getTxtAlamatPrayplace();

            //set Id
            imgPrayplace = findViewById(R.id.imgPrayplace);
            tvNamaPrayplace = findViewById(R.id.tvNamaPrayplace);
            tvAddressPrayplace = findViewById(R.id.tvAddressPrayplace);

            Glide.with(this)
                    .load(modelPrayplace.getGambarPrayplace())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgPrayplace);

            getDetailPrayplace();

        }
    }

    private void getDetailPrayplace() {
        AndroidNetworking.get(RestApi.DetailPrayplace)
                .addPathParameter("id", idPrayplace)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                NamaPrayplace = response.getString("nama");
                                AddressPrayplace = response.getString("alamat");

                                tvNamaPrayplace.setText(NamaPrayplace);
                                tvAddressPrayplace.setText(AddressPrayplace);

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(DetailPrayplaceActivity.this,
                                        "Gagal menampilkan data!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(DetailPrayplaceActivity.this,
                                "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        //get LatLong
        String[] latlong = modelPrayplace.getKoordinat().split(",");
        double latitude = Double.parseDouble(latlong[0]);
        double longitude = Double.parseDouble(latlong[1]);

        googleMaps = googleMap;
        LatLng latLng = new LatLng(latitude, longitude);
        googleMaps.addMarker(new MarkerOptions().position(latLng).title(NamaPrayplace));
        googleMaps.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMaps.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
        googleMaps.getUiSettings().setAllGesturesEnabled(true);
        googleMaps.getUiSettings().setZoomGesturesEnabled(true);
        googleMaps.setTrafficEnabled(true);
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