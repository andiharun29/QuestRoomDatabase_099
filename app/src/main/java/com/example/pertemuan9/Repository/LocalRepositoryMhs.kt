package com.example.pertemuan9.Repository

import com.example.pertemuan9.Data.dao.MahasiswaDao
import com.example.pertemuan9.Data.entity.Mahasiswa
import kotlinx.coroutines.flow.Flow
import java.lang.invoke.MethodHandle

class LocalRepositoryMhs(
    private val mahasiswaDao: MahasiswaDao
) : RepositoryMhs{
    override suspend fun insertMhs(mahasiswa: Mahasiswa) {
        mahasiswaDao.insertMahasiswa(mahasiswa)
    }
    //getAllMhs
    override fun getAllMhs(): Flow<List<Mahasiswa>> {
        return mahasiswaDao.getAllMahasiswa()
    }
    //getMhs
    override fun getMhs(nim: String): Flow<Mahasiswa>{
       return mahasiswaDao.getMahasiswa(nim)
    }
    //deleteMhs
    override suspend fun deleteMhs(mahasiswa: Mahasiswa){
        mahasiswaDao.deleteMahasiswa(mahasiswa)
    }
    //updateMhs
    override suspend fun updateMhs(mahasiswa: Mahasiswa){
        mahasiswaDao.updateMahasiswa(mahasiswa)
    }
}