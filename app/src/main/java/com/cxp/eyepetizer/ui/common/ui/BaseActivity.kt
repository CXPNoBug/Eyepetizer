package com.cxp.eyepetizer.ui.common.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import com.cxp.eyepetizer.R
import com.cxp.eyepetizer.event.MessageEvent
import com.cxp.eyepetizer.extension.logD
import com.cxp.eyepetizer.ui.SplashActivity
import com.cxp.eyepetizer.util.ActivityCollector
import com.gyf.immersionbar.ktx.immersionBar
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.lang.ref.WeakReference

/**
 * <pre>
 *     author : CXP
 *     e-mail : cxpnobug@gmail.com
 *     time   : 2021/01/02
 *     desc   : 应用程序中所有Activity的基类。
 *     version: 1.0
 * </pre>
 */
open class BaseActivity : AppCompatActivity() {


    /**
     * 判断当前Activity是否在前台。
     */
    protected var isActive: Boolean = false

    /**
     * 当前Activity的实例。
     */
    protected var activity: Activity? = null

    /** 当前Activity的弱引用，防止内存泄露  */
    private var activityWR: WeakReference<Activity>? = null

    /**
     * 日志输出标志
     */
    protected val TAG: String = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logD(TAG, "BaseActivity-->onCreate()")

        activity = this
        activityWR = WeakReference(activity!!)
        ActivityCollector.pushTask(activityWR)

        EventBus.getDefault().register(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        logD(TAG, "BaseActivity-->onSaveInstanceState()")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        logD(TAG, "BaseActivity-->onRestoreInstanceState()")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        logD(TAG, "BaseActivity-->onNewIntent()")
    }

    override fun onRestart() {
        super.onRestart()
        logD(TAG, "BaseActivity-->onRestart()")
    }

    override fun onStart() {
        super.onStart()
        logD(TAG, "BaseActivity-->onStart()")
    }

    override fun onResume() {
        super.onResume()
        logD(TAG, "BaseActivity-->onResume()")
        isActive = true
    }

    override fun onPause() {
        super.onPause()
        logD(TAG, "BaseActivity-->onPause()")
        isActive = false
    }

    override fun onStop() {
        super.onStop()
        logD(TAG, "BaseActivity-->onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        logD(TAG, "BaseActivity-->onDestroy()")

        activity = null
        ActivityCollector.removeTask(activityWR)
        EventBus.getDefault().unregister(this)
    }


    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        if (this !is SplashActivity) {
            setStatusBarBackground(R.color.colorPrimary)
        }
        setupViews()
    }

    override fun setContentView(layoutView: View) {
        super.setContentView(layoutView)
        if (this !is SplashActivity) {
            setStatusBarBackground(R.color.colorPrimary)
        }
        setupViews()
    }

    protected open fun setupViews() {
        val navigateBefore = findViewById<ImageView>(R.id.ivNavigateBefore)
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        navigateBefore?.setOnClickListener { finish() }
        tvTitle?.isSelected = true  //获取焦点，实现跑马灯效果。
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onMessageEvent(messageEvent: MessageEvent) {

    }

    /**
     * 设置状态栏背景色
     */
    open fun setStatusBarBackground(@ColorRes statusBarColor: Int) {
        immersionBar {
            autoStatusBarDarkModeEnable(true, 0.2f)
            statusBarColor(statusBarColor)
            fitsSystemWindows(true)
        }
    }
}