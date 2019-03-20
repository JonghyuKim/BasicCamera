package com.hyu.basiccamera.modules.camera

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.hardware.Camera
import android.os.Build
import org.koin.standalone.KoinComponent
import android.util.Log
import com.hyu.basiccamera.modules.preview.PreviewData
import java.io.IOException


@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class CameraApi(context: Context): ICameraModule, KoinComponent{

    private var camera: Camera? = null

    override var previewData : PreviewData? = null


    @SuppressLint("MissingPermission")
    override fun openCamera() {
        try {
            camera = Camera.open(getCameraId())

            camera?.apply {
                initCameraParams(this)
                //            camera!!.setPreviewCallback(null);
                setPreviewDisplay(previewData!!.previewSurfaceHolder)
            }?.startPreview()

        } catch (ioe: IOException) {
            Log.e("hyuhyu", "setPreviewTexture() failed: " + ioe.message)
        } catch (e: RuntimeException) {
            Log.e("hyuhyu", "camera open() failed: " + e.message)
        }
    }


    override fun closeCamera() {
        camera?.run{
            setPreviewCallback(null);
            stopPreview()
            release()
        }

    }

    private fun getCameraId(): Int {
        var cameraIndex = -1

        val cameraInfo = Camera.CameraInfo()

        for (camIdx in 0 until Camera.getNumberOfCameras()) {
            Camera.getCameraInfo(camIdx, cameraInfo)
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                cameraIndex = camIdx
                break
            }
        }
        return cameraIndex
    }

    private fun initCameraParams(camera : Camera){
        val cameraParams = camera.parameters
        val FocusModes = cameraParams.getSupportedFocusModes()

        calculatorPreviewSize(cameraParams, previewData!!.surfaceWidth, previewData!!.surfaceHeight)

        if (FocusModes != null && FocusModes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO)) {
            cameraParams.focusMode = Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO
        }
        cameraParams.set("orientation", "landscape")
    }

    private fun calculatorPreviewSize(cameraParam : Camera.Parameters, width: Int, height: Int){
        val psize = cameraParam.supportedPreviewSizes
        var bestWidth = 0
        var bestHeight = 0
        if (psize.size > 0) {
            val aspect = width.toFloat() / height
            for (size in psize) {
                val w = size.width
                val h = size.height
                Log.d("hyuhyu", "checking camera preview size: " + w + "x" + h)
                if (w <= width && h <= height &&
                    w >= bestWidth && h >= bestHeight &&
                    Math.abs(aspect - w.toFloat() / h) < 0.2
                ) {
                    bestWidth = w
                    bestHeight = h
                }
            }
            if (bestWidth <= 0 || bestHeight <= 0) {
                bestWidth = psize.get(0).width
                bestHeight = psize.get(0).height
                Log.e("hyuhyu", "Error: best size was not selected, using $bestWidth x $bestHeight")
            } else {
                Log.i("hyuhyu", "Selected best size: $bestWidth x $bestHeight")
            }

            cameraParam.setPreviewSize(bestWidth, bestHeight)
        }
    }
}