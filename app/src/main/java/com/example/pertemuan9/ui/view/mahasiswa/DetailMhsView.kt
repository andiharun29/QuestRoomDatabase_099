package com.example.pertemuan9.ui.view.mahasiswa

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier




@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCencel: () -> Unit,
    modifier: Modifier = Modifier

){
    AlertDialog(onDismissRequest = { /*TODO*/ },
        title = { Text( "Delete Data")},
        text = { Text("Apakah anda yakin ingin mengahpus data?" )},
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCencel) {
                Text(text = "Cencel")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Yes")
            }
        }
    )
}