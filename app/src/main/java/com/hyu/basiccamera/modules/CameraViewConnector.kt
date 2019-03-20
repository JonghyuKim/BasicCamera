package com.hyu.basiccamera.modules

import android.util.Log
import android.view.SurfaceHolder
import com.hyu.basiccamera.modules.camera.ICameraModule
import com.hyu.basiccamera.modules.preview.PreviewData
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class CameraViewConnector : SurfaceHolder.Callback , KoinComponent{

    private val camera : ICameraModule by inject()
    private val previewData : PreviewData by inject()

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {

        Log.d("hyuhyu", "surfaceChange !")

        camera.previewData = previewData.apply {
                                    surfaceWidth = width
                                    surfaceHeight = height
                                    previewSurfaceHolder = if(decorator != null){
                                                                    decorator!!.outputSurface = holder?.surface
                                                                    DummyHolder(decorator!!.inputSurface!!)
                                                               } else holder
                                }
        camera.openCamera()
    }

    override fun surfaceDestroyed(p0: SurfaceHolder?) {
        camera.closeCamera()
    }

    override fun surfaceCreated(p0: SurfaceHolder?) {}
}