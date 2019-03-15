package com.hyu.basiccamera

import android.app.Application
import com.hyu.basiccamera.modules.SupportModules
import org.koin.android.ext.android.startKoin

class CameraApplication : Application() {
    override fun onCreate(){
        super.onCreate()
        startKoin(applicationContext,SupportModules.modules)
    }
}