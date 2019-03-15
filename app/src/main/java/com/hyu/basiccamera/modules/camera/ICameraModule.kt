package com.hyu.basiccamera.modules.camera

import android.view.Surface

interface ICameraModule{
    fun setSurface(surface: Surface)
    fun openCamera()
    fun closeCamera()
}