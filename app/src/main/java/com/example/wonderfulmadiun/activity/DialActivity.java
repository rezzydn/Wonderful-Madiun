package com.example.wonderfulmadiun.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.wonderfulmadiun.R;

public class DialActivity extends AppCompatActivity {

    Toolbar tbDial;
    LinearLayout btnAmbulan,btnPolisi,btnPemadam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dial);

        tbDial = findViewById(R.id.tbDial);
        tbDial.setTitle("Kontak Penting");
        setSupportActionBar(tbDial);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnAmbulan = findViewById(R.id.btnPanggilAmbulance);
        btnPolisi = findViewById(R.id.btnPanggilPolisi);
        btnPemadam = findViewById(R.id.btnPanggilPemadam);

        btnAmbulan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:119"));
                startActivity(intent);
            }
        });

        btnPemadam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:112"));
                startActivity(intent);
            }
        });

        btnPolisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:112"));
                startActivity(intent);
            }
        });

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