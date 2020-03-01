package com.rrdev.roomdatabasesimple.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Entity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.rrdev.roomdatabasesimple.R;
import com.rrdev.roomdatabasesimple.RoomDb.Mahasiswa;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.detNim)
    TextView detNim;
    @BindView(R.id.det_nama)
    TextView detNama;
    @BindView(R.id.det_jurusan)
    TextView detJur;
    @BindView(R.id.det_alamat)
    TextView detAlam;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String nim = intent.getExtras().getString("nim");
        String nama = intent.getExtras().getString("nama");
        String jur = intent.getExtras().getString("kej");
        String alam = intent.getExtras().getString("alam");

        detNim.setText(nim);
        detNama.setText(nama);
        detJur.setText(jur);
        detAlam.setText(alam);

        //menampilkan tombol kembali
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    //action tombol kembali
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
