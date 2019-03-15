package com.hyu.basiccamera.modules.preview

import android.content.Context
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class PreviewSurfaceView: SurfaceView, KoinComponent{

    private val previewCallback : SurfaceHolder.Callback by inject()

    constructor(context: Context) : super(context){ initSurface() }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){ initSurface() }

    private fun initSurface(){
        holder.addCallback(previewCallback)
    }
}