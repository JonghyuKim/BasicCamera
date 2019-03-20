package com.hyu.basiccamera.modules.preview

import android.view.Surface
import android.view.SurfaceHolder
import com.hyu.basiccamera.modules.decorator.IDecoratorModule

data class PreviewData(var decorator : IDecoratorModule?
                       , var surfaceWidth : Int = 0
                       , var surfaceHeight : Int = 0
                       , var previewSurfaceHolder : SurfaceHolder? = null
    ) {

    var previewSurface : Surface? = null
        get() = previewSurfaceHolder!!.surface

}