package com.hyu.basiccamera.modules

import android.os.Build
import android.view.SurfaceHolder
import com.hyu.basiccamera.modules.camera.Camera2Api
import com.hyu.basiccamera.modules.camera.CameraApi
import com.hyu.basiccamera.modules.camera.ICameraModule
import com.hyu.basiccamera.modules.decorator.DecoratorCommon
import com.hyu.basiccamera.modules.decorator.DecoratorHdr
import com.hyu.basiccamera.modules.decorator.IDecoratorModule
import com.hyu.basiccamera.modules.preview.PreviewData
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

class SupportModules{

    companion object {

        val modules : List<Module> by lazy {
            val cameraModules = module {
                single<ICameraModule> {
                    if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
                        CameraApi(get())
                    }
                    else{
                        Camera2Api(get())
                    }
                }
            }

            val decoratorModules = module {
                factory<IDecoratorModule>(name = "Hdr") { DecoratorHdr()}
                factory<IDecoratorModule>(name = "Common") { DecoratorCommon()}
            }

            val previewModules = module {
                factory<SurfaceHolder.Callback> { CameraViewConnector() }
                factory<PreviewData> {PreviewData(get("Common"))}
            }
            listOf(cameraModules, decoratorModules, previewModules)
        }

    }
}