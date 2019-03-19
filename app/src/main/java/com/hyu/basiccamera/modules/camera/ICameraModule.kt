package com.hyu.basiccamera.modules.camera

import android.view.Surface
import com.hyu.basiccamera.modules.preview.PreviewData

interface ICameraModule{
    var previewData: PreviewData?

    fun openCamera()
    fun closeCamera()
}