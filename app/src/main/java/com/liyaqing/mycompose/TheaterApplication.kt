package com.liyaqing.mycompose

import android.app.Application

/**
 * @Author: liyaqing
 * @Date: Create in 4:17 下午 2022/9/22
 * @Description:
 */
class TheaterApplication : Application() {
    // AppContainer instance used by the rest of classes to obtain dependencies
//    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
//        container = AppContainerImpl(this)
    }
}