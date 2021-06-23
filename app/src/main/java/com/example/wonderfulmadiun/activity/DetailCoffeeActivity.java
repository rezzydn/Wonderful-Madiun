package com.example.wonderfulmadiun.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.wonderfulmadiun.R;
import com.example.wonderfulmadiun.model.CoffeeModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailCoffeeActivity extends AppCompatActivity implements OnMapReadyCallback {

    Toolbar tbDetailCoffee;
    GoogleMap googleMaps;
    TextView txtNameCoffee, txtAddressCoffee, txtTimeCoffee;
    String NameCoffee, AddressCoffee, TimeCoffee;
    CoffeeModel modelCoffee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_coffee);

        tbDetailCoffee = findViewById(R.id.tbDetailCoffee);
        tbDetailCoffee.setTitle("Detail Coffee");
        setSupportActionBar(tbDetailCoffee);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //show maps
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        modelCoffee = (CoffeeModel) getIntent().getSerializableExtra("detailCoffee");
        if (modelCoffee != null) {

            //get String
            NameCoffee = modelCoffee.getTxtNamaCoffee();
            AddressCoffee = modelCoffee.getTxtAlamatCoffee();
            TimeCoffee = modelCoffee.getTxtJam();

            //set Id
            txtNameCoffee = findViewById(R.id.tvNamaCoffee);
            txtAddressCoffee = findViewById(R.id.tvAddressCoffee);
            txtTimeCoffee = findViewById(R.id.tvTimeCoffee);

            //show String to Text
            txtNameCoffee.setText(NameCoffee);
            txtAddressCoffee.setText(AddressCoffee);
            txtTimeCoffee.setText(TimeCoffee);
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
        String[] latlong =  modelCoffee.getKoordinat().split(",");
        double latitude = Double.parseDouble(latlong[0]);
        double longitude = Double.parseDouble(latlong[1]);

        googleMaps = googleMap;
        LatLng latLng = new LatLng(latitude, longitude);
        googleMaps.addMarker(new MarkerOptions().position(latLng).title(NameCoffee));
        googleMaps.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMaps.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
        googleMaps.getUiSettings().setAllGesturesEnabled(true);
        googleMaps.getUiSettings().setZoomGesturesEnabled(true);
        googleMaps.setTrafficEnabled(true);
    }
}
