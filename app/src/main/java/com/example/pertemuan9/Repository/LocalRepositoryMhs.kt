package com.example.pertemuan9.Repository

import com.example.pertemuan9.Data.dao.MahasiswaDao
import com.example.pertemuan9.Data.entity.Mahasiswa

class LocalRepositoryMhs (
    private val mahasiswaDao: MahasiswaDao
): RepositoryMhs{
    override suspend fun insertMhs(mahasiswa: Mahasiswa) {
        mahasiswaDao.insertMahasiswa(mahasiswa)
    }
}
