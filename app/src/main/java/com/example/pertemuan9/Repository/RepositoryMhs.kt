package com.example.pertemuan9.Repository

import com.example.pertemuan9.Data.entity.Mahasiswa
import kotlinx.coroutines.flow.Flow

interface RepositoryMhs {
    suspend fun insertMhs(mahasiswa: Mahasiswa)
    //getAllMhs
    fun getAllMhs() : Flow<List<Mahasiswa>>
    //getMhs
    fun getMhs(nim : String): Flow<Mahasiswa>
    //deleteMhs
    suspend fun deleteMhs(mahasiswa: Mahasiswa)
    //updateMhs
    suspend fun updateMhs(mahasiswa: Mahasiswa)
}