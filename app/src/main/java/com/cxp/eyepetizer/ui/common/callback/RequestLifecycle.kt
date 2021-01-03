package com.cxp.eyepetizer.ui.common.callback

/**
 * <pre>
 *     author : CXP
 *     e-mail : cxpnobug@gmail.com
 *     time   : 2021/01/03
 *     desc   : 在Activity或Fragment中进行网络请求所需要经历的生命周期函数。
 *     version: 1.0
 * </pre>
 */
interface RequestLifecycle {

    fun startLoading()

    fun loadFinished()

    fun loadFailed(msg: String?)

}