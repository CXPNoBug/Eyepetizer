package com.cxp.eyepetizer

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

/**
 * <pre>
 *     author : CXP
 *     e-mail : cxpnobug@gmail.com
 *     time   : 2021/01/02
 *     desc   : Eyepetizer自定义Application，在这里进行全局的初始化操作。
 *     version: 1.0
 * </pre>
 */
class EyepetizerApplication : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        lateinit var context: Context
    }
}