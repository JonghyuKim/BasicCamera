package com.hyu.basiccamera.modules.camera

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.hardware.camera2.*
import android.os.Build
import org.koin.standalone.KoinComponent
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import com.hyu.basiccamera.modules.preview.PreviewData


@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class Camera2Api(context: Context): ICameraModule, KoinComponent{

    private val cameraManager: CameraManager by lazy { context.getSystemService(Context.CAMERA_SERVICE) as CameraManager }

    private var cameraDevice: CameraDevice? = null
    private var cameraSession: CameraCaptureSession? = null
    private var previewRequestBuilder : CaptureRequest.Builder? = null

    private var cameraThread: HandlerThread? = null
    private var cameraHandler: Handler? = null

    override var previewData : PreviewData? = null

    @SuppressLint("MissingPermission")
    override fun openCamera() {
        startCameraThread()
        cameraHandler?.post{
            try {
                cameraManager.openCamera(getCameraId(CameraCharacteristics.LENS_FACING_BACK), cameraListener, cameraHandler)
            } catch (e: CameraAccessException) {
            }
        }
    }

    private fun getCameraId(facing: Int): String? {
        return cameraManager.cameraIdList.find { id ->
            cameraManager.getCameraCharacteristics(id).get(CameraCharacteristics.LENS_FACING) == facing
        }
    }

    override fun closeCamera() {
        cameraSession?.close()
        cameraDevice?.close()
        cameraDevice = null
        cameraSession = null
        stopCameraThread()
    }

    private val cameraListener = object : CameraDevice.StateCallback() {

        override fun onOpened(camera: CameraDevice) {

            cameraDevice = camera
            try {
                previewRequestBuilder = cameraDevice!!.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
                previewRequestBuilder?.addTarget(previewData?.previewSurface)
                previewRequestBuilder?.set(CaptureRequest.CONTROL_AF_MODE,CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE)
                cameraDevice!!.createCaptureSession(listOf(previewData?.previewSurface), cameraSessionCallback, cameraHandler)
            } catch (e: CameraAccessException) {
                camera.close()
                cameraDevice = null
            }
        }

        override fun onClosed(camera: CameraDevice) {
            camera.close()
            cameraDevice = null
        }

        override fun onDisconnected(camera: CameraDevice) {
            camera.close()
            cameraDevice = null
        }

        override fun onError(camera: CameraDevice, error: Int) {
            camera.close()
            cameraDevice = null
        }

    }

    private val cameraSessionCallback = object : CameraCaptureSession.StateCallback() {
        override fun onConfigureFailed(session: CameraCaptureSession) {
            closeCamera()
        }

        override fun onConfigured(session: CameraCaptureSession) {
            cameraDevice ?: return
            previewRequestBuilder ?: return

            cameraSession = session
            try {
                session.setRepeatingRequest(previewRequestBuilder?.build(), null, null)
            } catch (e: CameraAccessException) {
                Log.e("hyuhyu", "Failed to start camera preview because it couldn't access camera", e)
            } catch (e: IllegalStateException) {
                Log.e("hyuhyu", "Failed to start camera preview.", e)
            }
        }
    }

    private fun startCameraThread(){
        stopCameraThread()
        cameraThread = HandlerThread("BasicCameraThread")
        cameraThread!!.start()
        cameraHandler = Handler(cameraThread!!.looper)
    }

    private fun stopCameraThread() {
        if (cameraThread == null)
            return
        cameraThread?.quitSafely()
        try {
            cameraThread?.join()
            cameraThread = null
            cameraHandler = null
        } catch (e: InterruptedException) {
        }

    }

}