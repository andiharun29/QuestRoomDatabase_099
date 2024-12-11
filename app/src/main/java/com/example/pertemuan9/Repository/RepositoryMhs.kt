package com.example.pertemuan9.Repository

import com.example.pertemuan9.Data.entity.Mahasiswa

interface RepositoryMhs {
    suspend fun insertMhs(mahasiswa: Mahasiswa)
}