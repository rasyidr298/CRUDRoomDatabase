package com.rrdev.roomdatabasesimple.RoomDb;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Dao;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MahasiswaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertAll (Mahasiswa mahasiswa);

    @Update
    int update(Mahasiswa mahasiswa);

    @Delete
    int deleteUsers(Mahasiswa mahasiswa);

    @Query("SELECT * FROM mahasiswa")
    Mahasiswa[]selectAllMahasiswa();

    @Query("SELECT * FROM mahasiswa WHERE id = :id LIMIT 1")
    Mahasiswa selectMahasiswaDetail (int id);
}
