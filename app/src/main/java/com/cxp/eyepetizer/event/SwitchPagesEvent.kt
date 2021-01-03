package com.cxp.eyepetizer.event

/**
 * <pre>
 *     author : CXP
 *     e-mail : cxpnobug@gmail.com
 *     time   : 2021/01/02
 *     desc   : EventBus通知Tab页切换界面。
 *     version: 1.0
 * </pre>
 */
open class SwitchPagesEvent(var activityClass: Class<*>? = null) : MessageEvent()