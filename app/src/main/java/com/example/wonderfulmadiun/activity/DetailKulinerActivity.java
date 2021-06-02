package com.example.wonderfulmadiun.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.wonderfulmadiun.R;
import com.example.wonderfulmadiun.model.KulinerModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailKulinerActivity extends AppCompatActivity implements OnMapReadyCallback {

    Toolbar tbDetailKuliner;
    GoogleMap googleMaps;
    TextView txtNameKuliner, txtAddressKuliner, txtTimeKuliner;
    String NameKuliner, AddressKuliner, TimeKuliner;
    KulinerModel modelKuliner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kuliner);

        tbDetailKuliner = findViewById(R.id.tbDetailKuliner);
        tbDetailKuliner.setTitle("Detail Kuliner");
        setSupportActionBar(tbDetailKuliner);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //show maps
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        modelKuliner = (KulinerModel) getIntent().getSerializableExtra("detailKuliner");
        if (modelKuliner != null) {

            //get String
            NameKuliner = modelKuliner.getTxtNamaKuliner();
            AddressKuliner = modelKuliner.getTxtAlamatKuliner();
            TimeKuliner = modelKuliner.getTxtJam();

            //set Id
            txtNameKuliner = findViewById(R.id.tvNamaKuliner);
            txtAddressKuliner = findViewById(R.id.tvAddressKuliner);
            txtTimeKuliner = findViewById(R.id.tvTimeKuliner);

            //show String to Text
            txtNameKuliner.setText(NameKuliner);
            txtAddressKuliner.setText(AddressKuliner);
            txtTimeKuliner.setText(TimeKuliner);
        }
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
        String[] latlong =  modelKuliner.getKoordinat().split(",");
        double latitude = Double.parseDouble(latlong[0]);
        double longitude = Double.parseDouble(latlong[1]);

        googleMaps = googleMap;
        LatLng latLng = new LatLng(latitude, longitude);
        googleMaps.addMarker(new MarkerOptions().position(latLng).title(NameKuliner));
        googleMaps.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMaps.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
        googleMaps.getUiSettings().setAllGesturesEnabled(true);
        googleMaps.getUiSettings().setZoomGesturesEnabled(true);
        googleMaps.setTrafficEnabled(true);
    }
}
