package com.cxp.eyepetizer.ui

import android.Manifest
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.cxp.eyepetizer.R
import com.cxp.eyepetizer.databinding.ActivitySplashBinding
import com.cxp.eyepetizer.extension.sharedPreferences
import com.cxp.eyepetizer.ui.common.ui.BaseActivity
import com.cxp.eyepetizer.util.GlobalUtil
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.ktx.immersionBar
import com.permissionx.guolindev.PermissionX
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * <pre>
 *     author : CXP
 *     e-mail : cxpnobug@gmail.com
 *     time   : 2021/01/02
 *     desc   : 闪屏页面，应用程序首次启动入口。
 *     version: 1.0
 * </pre>
 */
class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding

    private val job by lazy { Job() }

    private val splashDuration = 3 * 1000L

    private val alphaAnimation by lazy {
        AlphaAnimation(0.5f, 1.0f).apply {
            duration = splashDuration
            fillAfter = true
        }
    }

    private val scaleAnimation by lazy {
        ScaleAnimation(
            1f,
            1.05f,
            1f,
            1.05f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        ).apply {
            duration = splashDuration
            fillAfter = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val decorView = window.decorView
        decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        requestWriteExternalStoragePermission()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivSlogan.startAnimation(alphaAnimation)
        binding.ivSplashPicture.startAnimation(scaleAnimation)
        CoroutineScope(job).launch {
            delay(splashDuration)
            MainActivity.start(this@SplashActivity)
            finish()
        }
        isFirstEntryApp = false
    }


    private fun requestWriteExternalStoragePermission() {
        PermissionX.init(this@SplashActivity)
            .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .onExplainRequestReason { scope, deniedList ->
                val message = GlobalUtil.getString(R.string.request_permission_picture_processing)
                scope.showRequestReasonDialog(
                    deniedList,
                    message,
                    GlobalUtil.getString(R.string.ok),
                    GlobalUtil.getString(R.string.cancel)
                )
            }
            .onForwardToSettings { scope, deniedList ->
                val message = GlobalUtil.getString(R.string.request_permission_picture_processing)
                scope.showForwardToSettingsDialog(
                    deniedList,
                    message,
                    GlobalUtil.getString(R.string.settings),
                    GlobalUtil.getString(R.string.cancel)
                )
            }
            .request { _, _, _ ->
                requestReadPhoneStatePermission()
            }
    }

    private fun requestReadPhoneStatePermission() {
        PermissionX.init(this@SplashActivity).permissions(Manifest.permission.READ_PHONE_STATE)
            .onExplainRequestReason { scope, deniedList ->
                val message = GlobalUtil.getString(R.string.request_permission_access_phone_info)
                scope.showRequestReasonDialog(
                    deniedList,
                    message,
                    GlobalUtil.getString(R.string.ok),
                    GlobalUtil.getString(R.string.cancel)
                )
            }
            .onForwardToSettings { scope, deniedList ->
                val message = GlobalUtil.getString(R.string.request_permission_access_phone_info)
                scope.showForwardToSettingsDialog(
                    deniedList,
                    message,
                    GlobalUtil.getString(R.string.settings),
                    GlobalUtil.getString(R.string.cancel)
                )
            }
            .request { _, _, _ ->
                setContentView(R.layout.activity_splash)
            }
    }

    companion object {

        /**
         * 是否首次进入APP应用
         */
        var isFirstEntryApp: Boolean
            get() = sharedPreferences.getBoolean("is_first_entry_app", true)
            set(value) = sharedPreferences.edit { putBoolean("is_first_entry_app", value) }
    }
}