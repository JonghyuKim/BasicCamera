package com.hyu.basiccamera.modules.preview

import android.view.Surface
import android.view.SurfaceHolder
import com.hyu.basiccamera.modules.decorator.IDecoratorModule
import org.koin.standalone.KoinComponent

data class PreviewData(var decorator : IDecoratorModule?
                       , var surfaceWidth : Int = 0
                       , var surfaceHeigth : Int = 0
                       , var previewSurfaceHolder : SurfaceHolder? = null
                       , var previewSurface : Surface? = null
    ) : KoinComponent{



}