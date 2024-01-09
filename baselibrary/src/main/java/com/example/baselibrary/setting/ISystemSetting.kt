package com.example.baselibrary.setting

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings

/**
 * @author ym on 2024/1/3
 * 系统设置基类
 */
interface ISystemSetting {

    /**
     * 跳转通知
     */
    fun getNotificationSettingIntent(context: Context): Intent

    /**
     * 跳转响铃
     */
    fun getAudibleModeIntent(context: Context): Intent

    /**
     * 跳转省电模式
     */
    fun getPowerSaveModeIntent(context: Context): Intent

    /**
     * 跳转勿扰模式
     */
    fun getZenModeIntent(context: Context): Intent

    /**
     * 跳转自启动
     */
    fun getAutoStartIntent(context: Context): Intent

    /**
     * 跳转跳转后台允许
     */
    fun getBackgroundRunIntent(context: Context): Intent

    /**
     * 忽略电池优化页面
     */
    fun getIgnoreBatteryOptimizationIntent(context: Context): Intent {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS)
        } else {
            return getNormalNotificationIntent(context)
        }
    }

    /**
     * 悬浮窗页面
     */
    fun getOverlayIntent(context: Context): Intent {
        val intent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
        } else {
            Intent(Settings.ACTION_SETTINGS)
        }
        val uri = Uri.fromParts("package", context.packageName, null)
        intent.data = uri
        return intent
    }

    /**
     * 后台弹出页面权限
     */
    fun getBackgroundPopIntent(context: Context): Intent {
        return getAppInfoIntent(context)
    }

    /**
     * 铃声与提醒
     */
    fun getAppRingingIntent(context: Context): Intent {
        return getNormalNotificationIntent(context)
    }

    /**
     * 系统设置页面
     */
    fun getNormalSettingIntent(context: Context): Intent {
        return Intent(Settings.ACTION_SETTINGS)
    }

    /**
     * 应用信息页面
     */
    fun getAppInfoIntent(context: Context): Intent {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri: Uri = Uri.fromParts("package", context.packageName, null)
        intent.data = uri
        return intent
    }

    /**
     * 回到桌面
     */
    fun getHomeMainIntent(context: Context): Intent {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        return intent
    }

//    fun intentSystemSetting(context: Context, key: String){
//        val intent =when(key){
//                SystemSettingUtiLS.SET KEY ALLOW NOTIFICATION,
//        SystemSettingUtiLS.SET KEY AI NOTIFICATION CONTROL
//        > getNotificationSettingIntent(context)
//        SystemSettingUtiLs.SET KEY_APP_RINGING -> getAppRingingIntent(context)SystemSettingUtils.SET KEY SOUND SET -> getNotificationsettingIntent(context)SystemSettingUtiLs.SET KEY NOTIFICATION_VOICE -> getAudibleModeIntent(context)SystemSettingUtiLS.SET KEY NO DISTURB-> getZenModeIntent(context)SystemSettingUtils.SET KEY POWER SAVE -> getPowerSaveModeIntent(context)SystemSettingUtiLS.SET_KEY_ALLOW_TRAFFIC_ACCESS -> getNetworkconnectedIntent(context)SystemSettingUtils,SET_KEY_CLOSE BATTERY -> getIgnoreBatteryoptimizationIntent(context)SystemSettingUtils.SET_KEY_AUTO START -> getAutostartIntent(context)SystemSettingUtils,SET_KEY_BACKGROUND RUN -> getBackgroundRunIntent(context)SystemSettingUtils,SET KEY_BACKGROUND_POP -> getBackgroundPopIntent(context)SystemSettingUtiLS,SET KEY_LOCK BACK_RUNNING -> getHomeMainIntent(context)SystemSettingUtiLs,SET_KEY_OVERLAYS _WINDow -> getoverlayIntent(context)else -> getNormalSettingIntent(context)
//        }
//        intentSetting(context, intent)
//    }

    private fun intentSetting(context: Context, intent: Intent) {
        try {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (e: Exception) {
            val bak = Intent(Settings.ACTION_SETTINGS)
            bak.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(bak)
        }
    }

    /**
     * 获取默认跳转系统通知
     */
     fun getNormalNotificationIntent(context: Context): Intent {
        val intent = Intent()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
        } else {
            intent.action = "android.settings.APP_NOTIFICATION_SETTINGS"
            intent.putExtra("app_package", context.packageName)
            intent.putExtra("app_uid", context.applicationInfo.uid)
        }
        return intent
    }


}