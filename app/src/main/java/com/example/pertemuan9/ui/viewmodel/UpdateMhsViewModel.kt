package com.example.pertemuan9.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pertemuan9.Data.entity.Mahasiswa
import com.example.pertemuan9.Repository.RepositoryMhs
import com.example.pertemuan9.ui.navigation.DestinasiUpdate
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateMhsViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMhs: RepositoryMhs
) : ViewModel(){
    var updateUIState by mutableStateOf(MhsUIState())
        private set
    private val _nim: String = checkNotNull(savedStateHandle[DestinasiUpdate.NIM])

    init {
        viewModelScope.launch {
            updateUIState = repositoryMhs.getMhs(_nim)
                .filterNotNull()
                .first()
                .toUIStateMhs()
        }
    }
    fun updateState(mahasiswaEvent: MahasiswaEvent){
        updateUIState = updateUIState.copy(
            mahasiswaEvent = mahasiswaEvent,
        )
    }
    fun validateFields(): Boolean{
        val event = updateUIState.mahasiswaEvent
        val errorstate = FormErrorstate(
            nim = if (event.nim.isNotEmpty()) null else "NIM tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "nama tidak boleh kosong",
            jeniskelamin = if (event.jeniskelamin.isNotEmpty()) null else "jenis kelamintidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "alamat tidak boleh kosong",
            kelas = if (event.kelas.isNotEmpty()) null else "kelas tidak boleh kosong",
            angkatan = if (event.angkatan.isNotEmpty()) null else "angkatan tidak boleh kosong",
        )
        updateUIState = updateUIState.copy(isEntryValid = errorstate)
        return errorstate.isValid()
    }
    fun updateData() {
        val currentEvent = updateUIState.mahasiswaEvent
        if (validateFields()){
            viewModelScope.launch {
                try {
                    repositoryMhs.updateMhs(currentEvent.toMahasiswaEntity())
                    updateUIState = updateUIState.copy(
                        snackbarMessage = "Data berhasil diupdate",
                        mahasiswaEvent = MahasiswaEvent(),
                        isEntryValid = FormErrorstate()
                    )
                    println("snackBarMessage diatur: ${updateUIState
                        .snackbarMessage}")
                } catch (e: Exception){
                    updateUIState = updateUIState.copy(
                        snackbarMessage = "Data gagal diupdate"
                    )
                }
            }
        } else {
            updateUIState = updateUIState.copy(
                snackbarMessage = "Data gagal diupdate"
            )
        }
    }
    fun resetSnackBarMessage() {
        updateUIState = updateUIState.copy(snackbarMessage = null)
    }
}

fun Mahasiswa.toUIStateMhs(): MhsUIState = MhsUIState(
    mahasiswaEvent = this.toDetailUiEvent(),
)