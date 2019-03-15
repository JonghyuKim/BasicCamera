package com.hyu.basiccamera.modules

import com.hyu.basiccamera.modules.camera.Camera2Api
import com.hyu.basiccamera.modules.camera.ICameraModule
import com.hyu.basiccamera.modules.decorator.DecoratorHdr
import com.hyu.basiccamera.modules.decorator.IDecoratorModule
import com.hyu.basiccamera.modules.preview.IPreviewModule
import com.hyu.basiccamera.modules.preview.PreviewTexture
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

class SupportModules{

    companion object {

        val modules : List<Module> by lazy {
            val cameraModules = module {
                factory<ICameraModule> { Camera2Api()}
            }

            val decoratorModules = module {
                factory<IDecoratorModule> { DecoratorHdr()}
            }

            val previewModules = module {
                factory<IPreviewModule> { PreviewTexture()}
            }
            listOf(cameraModules, decoratorModules, previewModules)
        }

    }
}