package com.hyu.basiccamera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hyu.basiccamera.modules.BasicCameraManager
import com.hyu.basiccamera.modules.camera.Camera2Api
import com.hyu.basiccamera.modules.decorator.DecoratorHdr
import com.hyu.basiccamera.modules.preview.PreviewTexture

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val manager = BasicCameraManager()

//        CameraSetting(manager).settingType1()
        manager.log()
    }

//    class CameraSetting(val manager: BasicCameraManager){
//        fun settingType1(){
//
//            manager.camera = Camera2Api()
//            manager.decoration = DecoratorHdr()
//            manager.preview = PreviewTexture()
//        }
//    }
}
