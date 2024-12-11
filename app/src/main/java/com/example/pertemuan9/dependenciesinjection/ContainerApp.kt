package com.example.pertemuan9.dependenciesinjection

import android.content.Context
import com.example.pertemuan9.Data.Database.KrsDatabase
import com.example.pertemuan9.Repository.LocalRepositoryMhs
import com.example.pertemuan9.Repository.RepositoryMhs

interface InterfaceContainerApp{
    val repositoryMhs: RepositoryMhs
}

class ContainerApp(private val context: Context) : InterfaceContainerApp{
    override val repositoryMhs: RepositoryMhs by lazy {
        LocalRepositoryMhs(KrsDatabase.getDatabase(context).mahasiswaDao())
    }
}