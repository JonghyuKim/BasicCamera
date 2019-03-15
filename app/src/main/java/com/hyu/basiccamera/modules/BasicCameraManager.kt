package com.hyu.basiccamera.modules

import android.util.Log
import com.hyu.basiccamera.modules.camera.ICameraModule
import com.hyu.basiccamera.modules.decorator.IDecoratorModule
import com.hyu.basiccamera.modules.preview.IPreviewModule
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class BasicCameraManager : KoinComponent{

    val camera : ICameraModule by inject()
    val preview : IPreviewModule by inject()
    val decoration : IDecoratorModule by inject()

//    var camera : ICameraModule? = null
//    var preview : IPreviewModule? = null
//    var decoration : IDecoratorModule? = null

    fun log(){
        Log.d("hyuhyu", camera.toString() + " , " + preview + " , " + decoration)
    }
}