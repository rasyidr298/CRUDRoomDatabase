package com.rrdev.roomdatabasesimple.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.rrdev.roomdatabasesimple.R;
import com.rrdev.roomdatabasesimple.RoomDb.Mahasiswa;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;

import static com.rrdev.roomdatabasesimple.RoomDb.MyApp.db;

public class UpdateActivity extends AppCompatActivity {

    @BindView(R.id.etNamaDialog)
    EditText etNamaDial;
    @BindView(R.id.etNimDialog)
    EditText etNimDial;
    @BindView(R.id.etKejuruanDialog)
    EditText etKejuruanDial;
    @BindView(R.id.etAlamatDialog)
    EditText etAlamatDial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        ButterKnife.bind(this);

        Button btUpdate = findViewById(R.id.btUpdateDialog);

        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        final Mahasiswa mahasiswa = (Mahasiswa) getIntent().getSerializableExtra("data");

        if (mahasiswa != null){
            etNamaDial.setText(mahasiswa.getNama());
            etNimDial.setText(mahasiswa.getNim());
            etKejuruanDial.setText(mahasiswa.getKejuruan());
            etAlamatDial.setText(mahasiswa.getAlamat());

            btUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mahasiswa.setNama(etNamaDial.getText().toString());
                    mahasiswa.setNim(etNimDial.getText().toString());
                    mahasiswa.setKejuruan(etKejuruanDial.getText().toString());
                    mahasiswa.setAlamat(etAlamatDial.getText().toString());
                    updateMahasiswa(mahasiswa);
                }
            });
        }

        //menampilkan tombol kembali
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void updateMahasiswa (final Mahasiswa mahasiswa){
        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.userDao().update(mahasiswa);
                return status;
            }

            @Override
            protected void onPostExecute(Long aLong) {
                startActivity(new Intent(UpdateActivity.this, ShowActivity.class));
            }
        }.execute();
    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, UpdateActivity.class);
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
