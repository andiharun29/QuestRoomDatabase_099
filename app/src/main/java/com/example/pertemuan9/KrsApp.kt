package com.example.pertemuan9

import android.app.Application
import com.example.pertemuan9.dependenciesinjection.ContainerApp

class KrsApp : Application(){
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this)
    }
}