package com.hyu.basiccamera.modules.decorator

import android.view.Surface


interface IDecoratorModule{
    var inputSurface : Surface?
    var outputSurface : Surface?
}