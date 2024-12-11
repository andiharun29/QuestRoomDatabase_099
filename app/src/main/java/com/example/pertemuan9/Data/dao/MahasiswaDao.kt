package com.example.pertemuan9.Data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.pertemuan9.Data.entity.Mahasiswa

@Dao
interface MahasiswaDao {
    @Insert
    suspend fun insertMahasiswa(
        mahasiswa: Mahasiswa
    )
}