package com.hyu.basiccamera.modules.decorator

import android.view.Surface

class DecoratorCommon: IDecoratorModule{
    override var inputSurface: Surface? = null
        get() = outputSurface
//    override var inputSurface: Surface? = null
    override var outputSurface: Surface? = null
}