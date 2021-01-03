package com.cxp.eyepetizer.event

/**
 * <pre>
 *     author : CXP
 *     e-mail : cxpnobug@gmail.com
 *     time   : 2021/01/02
 *     desc   : EventBus通知刷新界面消息。
 *     version: 1.0
 * </pre>
 */
open class RefreshEvent(var activityClass: Class<*>? = null) : MessageEvent()