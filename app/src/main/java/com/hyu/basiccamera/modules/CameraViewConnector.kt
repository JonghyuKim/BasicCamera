package com.hyu.basiccamera.modules

import android.view.SurfaceHolder
import com.hyu.basiccamera.modules.camera.ICameraModule
import com.hyu.basiccamera.modules.decorator.IDecoratorModule
import org.koin.standalone.KoinComponent
import org.koin.standalone.get
import org.koin.standalone.inject

class CameraViewConnector : SurfaceHolder.Callback , KoinComponent{

    val camera : ICameraModule by inject()
    var decorator : IDecoratorModule? = get()

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        val surface = decorator?.run {
            outputSurface = holder!!.surface
            inputSurface
        } ?: holder!!.surface
        camera.setSurface(surface)
        camera.openCamera()
    }

    override fun surfaceDestroyed(p0: SurfaceHolder?) {
        camera.closeCamera()
    }

    override fun surfaceCreated(p0: SurfaceHolder?) {}
}