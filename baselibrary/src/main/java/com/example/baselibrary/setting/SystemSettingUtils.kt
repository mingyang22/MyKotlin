package com.example.baselibrary.setting

import android.app.AppOpsManager
import android.content.Context
import android.media.AudioManager
import android.net.Uri
import android.os.Binder
import android.os.Build
import android.os.PowerManager
import android.os.Process
import android.provider.Settings
import androidx.core.app.NotificationManagerCompat

/**
 * @author ym on 2024/1/4
 */
object SystemSettingUtils {
    private const val HW_OP_CODE_POPUP_BACKGROUND_WINDOW = 100000
    private const val XM_OP_CODE_POPUP_BACKGROUND_WINDOW = 10021

    /**
     * 是否开启通知
     */
    fun isNotificationEnabled(context: Context): Boolean {
        return try {
            NotificationManagerCompat.from(context).areNotificationsEnabled()
        } catch (e: Exception) {
            false
        }
    }

    /**
     * 否响铃模式并且非静音
     */
    fun isAudibleMode(context: Context): Boolean {
        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val volume = try {
            audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION)
        } catch (e: Exception) {
            1
        }
        return audioManager.ringerMode == AudioManager.RINGER_MODE_NORMAL && volume > 0
    }

    /**
     * 是否忽略电池优化
     */
    fun isIgnoringBatteryOptimizations(context: Context): Boolean {
        var ret = true
        //oppo手机9.0上显示异常，直接就过滤了
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.P && DeviceTypeUtils.isOppo()) {
            return ret
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ret = try {
                val packageName = context.packageName
                val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
                val isWhiteList = pm.isIgnoringBatteryOptimizations(packageName)
                isWhiteList
            } catch (e: Exception) {
                true
            }
        }
        return ret
    }

    /**
     * 是否省点优化
     */
    fun isPowerSaveMode(context: Context): Boolean {
        return if (DeviceTypeUtils.isHuawei() || DeviceTypeUtils.isHonor()) {
            isPowerSaveModeHuawei(context)
        } else if (DeviceTypeUtils.isXiaomi()) {
            isPowerSaveModeXiaomi(context)
        } else if (DeviceTypeUtils.isVivo()) {
            isPowerSaveModeVivo(context)
        } else {
            isPowerSaveModeNormal(context)
        }
    }

    private fun isPowerSaveModeHuawei(context: Context): Boolean {
        return try {
            val value = Settings.System.getInt(context.contentResolver, "SmartModeStatus")
            value == 4
        } catch (e: Exception) {
            isPowerSaveModeNormal(context)
        }
    }

    private fun isPowerSaveModeXiaomi(context: Context): Boolean {
        return try {
            val value = Settings.System.getInt(context.contentResolver, "POWER_SAVE_MODE_OPEN")
            value == 1
        } catch (e: Exception) {
            isPowerSaveModeNormal(context)
        }
    }

    private fun isPowerSaveModeVivo(context: Context): Boolean {
        return try {
            val value = Settings.System.getInt(context.contentResolver, "power_save_type")
            value == 2
        } catch (e: Exception) {
            isPowerSaveModeNormal(context)
        }
    }

    private fun isPowerSaveModeNormal(context: Context): Boolean {
        val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        return pm.isPowerSaveMode
    }

    /**
     * 是否允许后台弹窗
     */
    fun isBackgroundPop(context: Context): Boolean {
        return if (DeviceTypeUtils.isHuawei() || DeviceTypeUtils.isHonor()) {
            isBackgroundPopHuawei(context)
        } else if (DeviceTypeUtils.isXiaomi()) {
            isBackgroundPopXiaomi(context)
        } else if (DeviceTypeUtils.isVivo()) {
            isBackgroundPopVivo(context)
        } else {
            false
        }
    }

    private fun isBackgroundPopVivo(context: Context): Boolean {
        val uri =
            Uri.parse("content://com.vivo.permissionmanager.provider.permission/start_bg_activity")
        val selection = "pkgname = ?"
        val selectionArgs = arrayOf(context.packageName)
        var result = 1
        val contentResolver = context.contentResolver
        try {
            contentResolver.query(uri, null, selection, selectionArgs, null).use { cursor ->
                if (cursor!!.moveToFirst()) {
                    result = cursor.getInt(cursor.getColumnIndex("currentstate"))
                }
            }
        } catch (exception: Exception) {
            //ignore
        }
        return result == AppOpsManager.MODE_ALLOWED
    }

    private fun isBackgroundPopXiaomi(context: Context): Boolean {
        val ops = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        try {
            val method = ops.javaClass.getMethod(
                "checkOpNoThrow", *arrayOf<Class<*>?>(
                    Int::class.javaPrimitiveType, Int::class.javaPrimitiveType, String::class.java
                )
            )
            val result = method.invoke(
                ops,
                XM_OP_CODE_POPUP_BACKGROUND_WINDOW,
                Process.myUid(),
                context.packageName
            ) as Int
            return result == AppOpsManager.MODE_ALLOWED
        } catch (e: Exception) {
            //ignore
        }
        return false
    }

    private fun isBackgroundPopHuawei(context: Context): Boolean {
        try {
            val c = Class.forName("com.huawei.android.app.AppOpsManagerEx")
            val m = c.getDeclaredMethod(
                "checkHwOpNoThrow",
                AppOpsManager::class.java,
                Int::class.javaPrimitiveType,
                Int::class.javaPrimitiveType,
                String::class.java
            )
            val result = m.invoke(
                c.newInstance(),
                *arrayOf(
                    context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager,
                    HW_OP_CODE_POPUP_BACKGROUND_WINDOW,
                    Binder.getCallingUid(),
                    context.packageName
                )
            ) as Int
            return AppOpsManager.MODE_ALLOWED == result
        } catch (e: Exception) {
            //ignore
        }
        return false
    }

    /**
     * 是否开启勿扰模式
     */
    fun isZenMode(context: Context): Boolean {
        return try {
            val value = Settings.Global.getInt(context.contentResolver, "zen_mode")
            value != 0
        } catch (e: Exception) {
            false
        }
    }



}