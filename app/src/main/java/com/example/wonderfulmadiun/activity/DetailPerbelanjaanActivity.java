package com.example.wonderfulmadiun.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.wonderfulmadiun.R;
import com.example.wonderfulmadiun.model.PerbelanjaanModel;
import com.example.wonderfulmadiun.rest.RestApi;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailPerbelanjaanActivity extends AppCompatActivity implements OnMapReadyCallback {

    Toolbar tbDetailShop;
    GoogleMap googleMaps;
    TextView txtNameShop, txtAddressShop;
    String idShop, NameShop, AddressShop;
    PerbelanjaanModel modelShop;
    ImageView imgShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_perbelanjaan);

        tbDetailShop = findViewById(R.id.tbDetailShop);
        tbDetailShop.setTitle("Detail Shop");
        setSupportActionBar(tbDetailShop);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //show maps
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        modelShop = (PerbelanjaanModel) getIntent().getSerializableExtra("detailShop");
        if (modelShop != null) {

            //get String
            NameShop = modelShop.getTxtNamaShop();
            AddressShop = modelShop.getTxtAlamatShop();


            //set Id
            txtNameShop = findViewById(R.id.tvNamaShop);
            imgShop = findViewById(R.id.imgShop);
            txtAddressShop = findViewById(R.id.tvAddressShop);


            //show String to Text
            txtNameShop.setText(NameShop);
            txtAddressShop.setText(AddressShop);

            Glide.with(this)
                    .load(modelShop.getGambarShop())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgShop);

            getDetailShop();
        }
    }
    private void getDetailShop() {
        AndroidNetworking.get(RestApi.DetailPerbelanjaan)
                .addPathParameter("id", idShop)
                .setPriority(Priority.HIGH)
                .build();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        //get LatLong
        String[] latlong =  modelShop.getKoordinat().split(",");
        double latitude = Double.parseDouble(latlong[0]);
        double longitude = Double.parseDouble(latlong[1]);

        googleMaps = googleMap;
        LatLng latLng = new LatLng(latitude, longitude);
        googleMaps.addMarker(new MarkerOptions().position(latLng).title(NameShop));
        googleMaps.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMaps.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
        googleMaps.getUiSettings().setAllGesturesEnabled(true);
        googleMaps.getUiSettings().setZoomGesturesEnabled(true);
        googleMaps.setTrafficEnabled(true);
    }
}

