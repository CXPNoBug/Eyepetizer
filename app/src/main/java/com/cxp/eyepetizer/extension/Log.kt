package com.cxp.eyepetizer.extension

import android.util.Log
import com.cxp.eyepetizer.BuildConfig

/**
 * <pre>
 *     author : CXP
 *     e-mail : cxpnobug@gmail.com
 *     time   : 2021/01/02
 *     desc   : 日志调试工具类。
 *     version: 1.0
 * </pre>
 */

private const val TAG = "CXP_LOG"

private const val VERBOSE = 1
private const val DEBUG = 2
private const val INFO = 3
private const val WARN = 4
private const val ERROR = 5

private val level = if (BuildConfig.DEBUG) VERBOSE else WARN

fun logV(msg: String?) {
    if (level <= VERBOSE) {
        Log.v(TAG, msg.toString())
    }
}

fun logD(msg: String?) {
    if (level <= DEBUG) {
        Log.d(TAG, msg.toString())
    }
}

fun logI(msg: String?) {
    if (level <= INFO) {
        Log.i(TAG, msg.toString())
    }
}

fun logW(msg: String?, tr: Throwable? = null) {
    if (level <= WARN) {
        if (tr == null) {
            Log.w(TAG, msg.toString())
        } else {
            Log.w(TAG, msg.toString(), tr)
        }
    }
}

fun logE(msg: String?, tr: Throwable) {
    if (level <= ERROR) {
        Log.e(TAG, msg.toString(), tr)
    }
}


fun logV(tag: String, msg: String?) {
    if (level <= VERBOSE) {
        Log.v(tag, msg.toString())
    }
}

fun logD(tag: String, msg: String?) {
    if (level <= DEBUG) {
        Log.d(tag, msg.toString())
    }
}

fun logI(tag: String, msg: String?) {
    if (level <= INFO) {
        Log.i(tag, msg.toString())
    }
}

fun logW(tag: String, msg: String?, tr: Throwable? = null) {
    if (level <= WARN) {
        if (tr == null) {
            Log.w(tag, msg.toString())
        } else {
            Log.w(tag, msg.toString(), tr)
        }
    }
}

fun logE(tag: String, msg: String?, tr: Throwable) {
    if (level <= ERROR) {
        Log.e(tag, msg.toString(), tr)
    }
}

