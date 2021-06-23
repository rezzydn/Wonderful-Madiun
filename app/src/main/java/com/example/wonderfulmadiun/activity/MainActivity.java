package com.example.wonderfulmadiun.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.wonderfulmadiun.R;
import com.example.wonderfulmadiun.adapter.MainAdapter;
import com.example.wonderfulmadiun.adjustment.MarginAdjustment;
import com.example.wonderfulmadiun.model.KulinerModel;
import com.example.wonderfulmadiun.model.MainModel;
import com.example.wonderfulmadiun.utility.Tools;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainAdapter.onSelectData {

    RecyclerView rvMainMenu;
    MarginAdjustment gridMargin;
    MainModel mdlMainMenu;
    List<MainModel> lsMainMenu = new ArrayList<>();
    TextView tvToday;
    String hariIni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility
                    (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        tvToday = findViewById(R.id.tvDate);
        rvMainMenu = findViewById(R.id.rv_mainmenu);
        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2,
                RecyclerView.VERTICAL, false);
        rvMainMenu.setLayoutManager(mLayoutManager);
        gridMargin = new MarginAdjustment(2, Tools.dp2px(this, 4));
        rvMainMenu.addItemDecoration(gridMargin);
        rvMainMenu.setHasFixedSize(true);

        //get Time Now
        Date dateNow = Calendar.getInstance().getTime();
        hariIni = (String) DateFormat.format("EEEE", dateNow);
        getToday();
        setMenu();
    }

    private void getToday() {
        Date date = Calendar.getInstance().getTime();
        String tanggal = (String) DateFormat.format("d MMMM yyyy", date);
        String formatFix = hariIni + ", " + tanggal;
        tvToday.setText(formatFix);
    }

    private void setMenu() {
        mdlMainMenu = new MainModel("Destinasi Wisata", R.drawable.ic_destination);
        lsMainMenu.add(mdlMainMenu);
        mdlMainMenu = new MainModel("Kuliner", R.drawable.ic_cafe);
        lsMainMenu.add(mdlMainMenu);
        mdlMainMenu = new MainModel("Penginapan", R.drawable.ic_hotel);
        lsMainMenu.add(mdlMainMenu);
        mdlMainMenu = new MainModel("Tempat Ibadah", R.drawable.ic_pray_place);
        lsMainMenu.add(mdlMainMenu);
        mdlMainMenu = new MainModel("Perbelanjaan", R.drawable.ic_komunitas);
        lsMainMenu.add(mdlMainMenu);
        mdlMainMenu = new MainModel("Coffee Shop", R.drawable.ic_cafe);
        lsMainMenu.add(mdlMainMenu);

        MainAdapter myAdapter = new MainAdapter(lsMainMenu, this);
        rvMainMenu.setAdapter(myAdapter);

    }

    @Override
    public void onSelected(MainModel mdlMain) {
        switch (mdlMain.getTxtName()) {
            case "Penginapan":
                startActivityForResult(new Intent(MainActivity.this, PenginapanActivity.class), 1);
                break;
            case "Kuliner":
                startActivityForResult(new Intent(MainActivity.this, KulinerActivity.class), 1);
                break;
            case "Tempat Ibadah":
                startActivityForResult(new Intent(MainActivity.this, PrayplaceActivity.class), 1);
                break;
            case "Destinasi Wisata":
                startActivityForResult(new Intent(MainActivity.this, WisataActivity.class), 1);
                break;
            case "Perbelanjaan":
                startActivityForResult(new Intent(MainActivity.this, PerbelanjaanActivity.class), 1);
                break;
            case "Coffee Shop":
                startActivityForResult(new Intent(MainActivity.this, CoffeeActivity.class), 1);
                break;
        }
    }

    //set Transparent Status bar
    public static void setWindowFlag(Activity activity, final int bits, boolean on) {

        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
