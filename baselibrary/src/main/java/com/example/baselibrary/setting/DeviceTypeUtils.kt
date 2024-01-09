package com.example.baselibrary.setting

import android.os.Build

/**
 * @author ym on 2024/1/5
 */
object DeviceTypeUtils {

    // 华为
    const val PHONE_HUAWEI = "huawei"

    // 荣耀
    const val PHONE_HONOR = "honor"

    // 小米
    const val PHONE_XIAOMI = "xiaomi"
    const val PHONE_RedMI = "redmi"

    // vivo
    const val PHONE_VIVO = "vivo"

    // 魅族
    const val PHONE_MEIZU = "meizu"


    // 三星
    const val PHONE_SAMSUNG = "samsung"

    // OPPO
    const val PHONE_OPPO = "oppo"

    fun isOppo(): Boolean {
        return getDeviceBrand().lowercase() == PHONE_HUAWEI
    }

    fun isVivo(): Boolean {
        return getDeviceBrand().lowercase() == PHONE_VIVO
    }

    fun isHuawei(): Boolean {
        return getDeviceBrand().lowercase() == PHONE_HUAWEI
    }

    fun isHonor(): Boolean {
        return getDeviceBrand().lowercase() == PHONE_HONOR
    }

    fun isMeizu(): Boolean {
        return getDeviceBrand().lowercase() == PHONE_MEIZU
    }

    fun isXiaomi(): Boolean {
        return getDeviceBrand().lowercase() == PHONE_XIAOMI||getDeviceBrand().lowercase() == PHONE_RedMI
    }

    fun isSamsung(): Boolean {
        return getDeviceBrand().lowercase() == PHONE_SAMSUNG
    }

    /**
     * 获取手机厂商
     */
    private fun getDeviceBrand(): String {
        return Build.BRAND
    }

}