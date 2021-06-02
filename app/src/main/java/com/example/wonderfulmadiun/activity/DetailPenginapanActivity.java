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
import com.example.wonderfulmadiun.model.PenginapanModel;
import com.example.wonderfulmadiun.rest.RestApi;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailPenginapanActivity extends AppCompatActivity implements OnMapReadyCallback {

    Toolbar tbDetailHotel;
    GoogleMap googleMaps;
    TextView txtNameHotel, txtAddressHotel, txtPhoneHotel;
    String idHotel, NameHotel, AddressHotel, PhoneHotel, LinkHotel;
    PenginapanModel modelHotel;
    ImageView imgHotel;
    Button btnHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_penginapan);

        tbDetailHotel = findViewById(R.id.tbDetailHotel);
        tbDetailHotel.setTitle("Detail Hotel");
        setSupportActionBar(tbDetailHotel);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //show maps
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        modelHotel = (PenginapanModel) getIntent().getSerializableExtra("detailHotel");
        if (modelHotel != null) {

            //get String
            NameHotel = modelHotel.getTxtNamaHotel();
            AddressHotel = modelHotel.getTxtAlamatHotel();
            PhoneHotel = modelHotel.getTxtNoTelp();
            LinkHotel = modelHotel.getLinkHotel();


            //set Id
            txtNameHotel = findViewById(R.id.tvNamaHotel);
            imgHotel = findViewById(R.id.imgHotel);
            txtAddressHotel = findViewById(R.id.tvAddressHotel);
            txtPhoneHotel = findViewById(R.id.tvPhoneHotel);

            btnHotel = findViewById(R.id.button_link);


            //show String to Text
            txtNameHotel.setText(NameHotel);
            txtAddressHotel.setText(AddressHotel);
            txtPhoneHotel.setText(PhoneHotel);

            Glide.with(this)
                    .load(modelHotel.getGambarHotel())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgHotel);

            getDetailHotel();
        }
    }
    private void getDetailHotel() {
        AndroidNetworking.get(RestApi.DetailWisata)
                .addPathParameter("id", idHotel)
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
        String[] latlong =  modelHotel.getKoordinat().split(",");
        double latitude = Double.parseDouble(latlong[0]);
        double longitude = Double.parseDouble(latlong[1]);

        googleMaps = googleMap;
        LatLng latLng = new LatLng(latitude, longitude);
        googleMaps.addMarker(new MarkerOptions().position(latLng).title(NameHotel));
        googleMaps.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMaps.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
        googleMaps.getUiSettings().setAllGesturesEnabled(true);
        googleMaps.getUiSettings().setZoomGesturesEnabled(true);
        googleMaps.setTrafficEnabled(true);
    }

    public void booking( View view) {
        String url = "https://www.agoda.com/en-gb/aston-madiun-hotel-and-conference-center/hotel/madiun-id.html";
        Intent openlink  = new Intent(Intent.ACTION_VIEW);
        openlink.setData(Uri.parse(url));
        startActivity(openlink);
    }
}
