package com.hyu.basiccamera.modules.decorator

import android.view.Surface

class DecoratorHdr: IDecoratorModule{
    override var inputSurface: Surface? = null
        get() = outputSurface
        set(value) { field = value}
//    override var inputSurface: Surface? = null
    override var outputSurface: Surface? = null
}