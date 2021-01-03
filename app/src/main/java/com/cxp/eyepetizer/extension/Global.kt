package com.cxp.eyepetizer.extension

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import com.cxp.eyepetizer.EyepetizerApplication
import com.cxp.eyepetizer.util.GlobalUtil


/**
 * 获取SharedPreferences实例。
 */
val sharedPreferences: SharedPreferences = EyepetizerApplication.context.getSharedPreferences(
    GlobalUtil.appPackage + "_preferences", Context.MODE_PRIVATE)

/**
 * 批量设置控件点击事件。
 *
 * @param v 点击的控件
 * @param block 处理点击事件回调代码块
 */
fun setOnClickListener(vararg v: View?, block: View.() -> Unit) {
    val listener = View.OnClickListener { it.block() }
    v.forEach { it?.setOnClickListener(listener) }
}