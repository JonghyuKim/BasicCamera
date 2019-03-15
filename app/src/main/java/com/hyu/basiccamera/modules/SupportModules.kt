package com.hyu.basiccamera.modules

import android.view.SurfaceHolder
import com.hyu.basiccamera.modules.camera.Camera2Api
import com.hyu.basiccamera.modules.camera.ICameraModule
import com.hyu.basiccamera.modules.decorator.DecoratorHdr
import com.hyu.basiccamera.modules.decorator.IDecoratorModule
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

class SupportModules{

    companion object {

        val modules : List<Module> by lazy {
            val cameraModules = module {
                single<ICameraModule> { Camera2Api(get())}
            }

            val decoratorModules = module {
                factory<IDecoratorModule> { DecoratorHdr()}
            }

            val previewModules = module {
                factory<SurfaceHolder.Callback> { CameraViewConnector() }
            }
            listOf(cameraModules, decoratorModules, previewModules)
        }

    }
}