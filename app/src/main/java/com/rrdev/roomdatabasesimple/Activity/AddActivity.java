package com.rrdev.roomdatabasesimple.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.rrdev.roomdatabasesimple.R;
import com.rrdev.roomdatabasesimple.RoomDb.Mahasiswa;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;

import static com.rrdev.roomdatabasesimple.RoomDb.MyApp.db;

public class AddActivity extends AppCompatActivity {

    @BindView(R.id.etNama)
    EditText etNama;
    @BindView(R.id.etNim)
    EditText etNim;
    @BindView(R.id.etKejuruan)
    EditText etKejuruan;
    @BindView(R.id.etAlamat)
    EditText etAlamat;
    Mahasiswa mahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);

        Button btInsert = findViewById(R.id.btInsert);

        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etAlamat.getText().toString().isEmpty() && !etKejuruan.getText().toString().isEmpty() &&
                        !etNama.getText().toString().isEmpty() && !etNim.getText().toString().isEmpty()) {

                    mahasiswa = new Mahasiswa();
                    mahasiswa.setAlamat(etAlamat.getText().toString());
                    mahasiswa.setKejuruan(etKejuruan.getText().toString());
                    mahasiswa.setNama(etNama.getText().toString());
                    mahasiswa.setNim(etNim.getText().toString());
                    //Insert data in database
                    db.userDao().insertAll(mahasiswa);
                    startActivity(new Intent(AddActivity.this, ShowActivity.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Mohon masukkan dengan benar", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
