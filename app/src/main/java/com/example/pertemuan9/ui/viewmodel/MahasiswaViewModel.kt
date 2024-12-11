package com.example.pertemuan9.ui.viewmodel

import androidx.compose.material3.Snackbar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pertemuan9.Data.entity.Mahasiswa
import com.example.pertemuan9.Repository.RepositoryMhs
import kotlinx.coroutines.launch

class MahasiswaViewModel(private  val repositoryMhs: RepositoryMhs): ViewModel(){

    var uiState by mutableStateOf(MhsUIState())

    //memperbaiki state berdasarkan input
    fun updateState(mahasiswaEvent: MahasiswaEvent){
        uiState = uiState.copy(
            mahasiswaEvent = mahasiswaEvent,
        )
    }
    //validasi data input pengguna
    fun validateFields(): Boolean{
        val event = uiState.mahasiswaEvent
        val errorstate = FormErrorstate(
            nim = if (event.nim.isNotEmpty()) null else "NIM tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "nama tidak boleh kosong",
            jeniskelamin = if (event.jeniskelamin.isNotEmpty()) null else "jenis kelamin tidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "alamat tidak boleh kosong",
            kelas =  if (event.kelas.isNotEmpty()) null else "kelas tidak boleh kosong",
            angkatan = if (event.angkatan.isNotEmpty()) null else "angkatan tidak boleh kosong",
        )

        uiState = uiState.copy(isEntryValid = errorstate)
        return errorstate.isValid()
    }
    fun saveData() {
        val currentEvent = uiState.mahasiswaEvent

        if (validateFields()){
            viewModelScope.launch{
                try {
                    repositoryMhs.insertMhs(currentEvent.toMahasiswaEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        mahasiswaEvent = MahasiswaEvent(),
                        isEntryValid = FormErrorstate()
                    )
                }catch (e:Exception){
                    uiState = uiState.copy(
                        snackBarMessage = "data gagal disimpan"
                    )
                }
            }
        }else{
            uiState = uiState.copy(
                snackBarMessage = "input tidak valid periksa kembali data anda"
            )
        }

        //reset pesan setelah di tampilkan
        fun ResetSnackBarMessage() {
            uiState = uiState.copy(snackBarMessage = null)
        }

    }



    data class FormErrorstate(
        val nim: String? = null,
        val nama: String? = null,
        val jeniskelamin: String? = null,
        val alamat: String? = null,
        val kelas: String? = null,
        val angkatan: String? = null,
    ){
        fun isValid(): Boolean{
            return nim== null && nama == null && jeniskelamin == null &&
                    alamat == null && kelas == null && angkatan == null
        }
    }
    data class MhsUIState(
        val mahasiswaEvent: MahasiswaEvent = MahasiswaEvent(),
        val isEntryValid: FormErrorstate = FormErrorstate(),
        val snackBarMessage: String? = null,
    )
    //data class variabel yang menyimpan data input form
    data class MahasiswaEvent(
        val nim: String = "",
        val nama: String = "",
        val jeniskelamin: String = "",
        val alamat: String = "",
        val kelas: String = "",
        val angkatan: String = "",
    )

    //menyimpann input form ke dalam entity
    fun MahasiswaEvent.toMahasiswaEntity(): Mahasiswa = Mahasiswa(
        nim = nim,
        nama = nama,
        jeniskelamin = jeniskelamin,
        alamat = alamat,
        kelas = kelas,
        angkatan = angkatan
    )
}


