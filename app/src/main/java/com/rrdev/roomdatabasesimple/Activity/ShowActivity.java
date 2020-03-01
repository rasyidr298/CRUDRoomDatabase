package com.rrdev.roomdatabasesimple.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.rrdev.roomdatabasesimple.R;
import com.rrdev.roomdatabasesimple.Adapter.RecycleAdapter;
import com.rrdev.roomdatabasesimple.RoomDb.AppDatabase;
import com.rrdev.roomdatabasesimple.RoomDb.Mahasiswa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.rrdev.roomdatabasesimple.RoomDb.MyApp.db;

public class ShowActivity extends AppCompatActivity {

    @BindView(R.id.myRecyclerview)
    RecyclerView myRecyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private ArrayList<Mahasiswa> listMahasiswa = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);

        fetchDataFromRoom();
        initRecyclerView();
        setAdapter();

        //menampilkan tombol kembali
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    private void fetchDataFromRoom() {
        //add all data to arraylist
        listMahasiswa.addAll(Arrays.asList(db.userDao().selectAllMahasiswa()));

        //just checking data from db
        for (int i = 0; i < listMahasiswa.size(); i++) {
            Log.e("Aplikasi", listMahasiswa.get(i).getAlamat() + i);
            Log.e("Aplikasi", listMahasiswa.get(i).getKejuruan() + i);
            Log.e("Aplikasi", listMahasiswa.get(i).getNama() + i);
            Log.e("Aplikasi", listMahasiswa.get(i).getNim() + i);
        }
    }

    private void initRecyclerView() {
        //inissialisasi recycler dan layout manager
        myRecyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myRecyclerView.setLayoutManager(llm);
        recyclerAdapter = new RecycleAdapter(this, listMahasiswa);
    }

    private void setAdapter() {
        //set all data to adapter and show
        myRecyclerView.setAdapter(recyclerAdapter);
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