package com.rrdev.roomdatabasesimple.RoomDb;

import android.app.Application;

import androidx.room.Room;

import com.rrdev.roomdatabasesimple.RoomDb.AppDatabase;

public class MyApp extends Application {
    public static AppDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,"Mahasiswa").allowMainThreadQueries().build();
    }
}
