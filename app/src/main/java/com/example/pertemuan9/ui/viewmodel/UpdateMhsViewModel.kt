package com.example.pertemuan9.ui.viewmodel

import com.example.pertemuan9.Data.entity.Mahasiswa

fun Mahasiswa.toUIStateMhs(): MhsUIState = MhsUIState(
    mahasiswaEvent = this.toDetailUiEvent(),
)