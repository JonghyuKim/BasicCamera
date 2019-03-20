package com.hyu.basiccamera.modules

import android.graphics.Canvas
import android.graphics.Rect
import android.view.Surface
import android.view.SurfaceHolder

class DummyHolder(val dummySurface : Surface): SurfaceHolder{

    override fun getSurface(): Surface {
        return dummySurface
    }

    /**
     * Not Used Functions
     */
    override fun setType(p0: Int) {}
    override fun setSizeFromLayout() {}
    override fun lockCanvas(): Canvas? {return null}
    override fun lockCanvas(p0: Rect?): Canvas? {return null}
    override fun getSurfaceFrame(): Rect? {return null}
    override fun setFixedSize(p0: Int, p1: Int) {}
    override fun removeCallback(p0: SurfaceHolder.Callback?) {}
    override fun isCreating(): Boolean {return false}
    override fun addCallback(p0: SurfaceHolder.Callback?) {}
    override fun setFormat(p0: Int) {}
    override fun setKeepScreenOn(p0: Boolean) {}
    override fun unlockCanvasAndPost(p0: Canvas?) {}

}