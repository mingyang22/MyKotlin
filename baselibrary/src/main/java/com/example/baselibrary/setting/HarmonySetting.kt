package com.example.baselibrary.setting

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings

/**
 * @author ym on 2024/1/3
 * 鸿蒙2.0，3.0系统设置项
 */
class HarmonySetting : ISystemSetting {
    override fun getNotificationSettingIntent(context: Context): Intent {
        return getNormalNotificationIntent(context)
    }

    override fun getAudibleModeIntent(context: Context): Intent {
        return Intent(Settings.ACTION_SOUND_SETTINGS)
    }

    override fun getPowerSaveModeIntent(context: Context): Intent {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Intent(Intent.ACTION_POWER_USAGE_SUMMARY)
        } else {
            val intent = Intent()
            intent.component = ComponentName(
                "com.huawei.systemmanager",
                "com.huawei.systemmanager.power.ui.HwPowerManagerActivity"
            )
            intent
        }
    }

    override fun getZenModeIntent(context: Context): Intent {
        val intent = Intent()
        val componentName = ComponentName(
            "com.android.settings",
            "com.android.settings.Settings\$ZenModeSettingsActivity"
        )
        intent.component = componentName
        return intent
    }

    override fun getAutoStartIntent(context: Context): Intent {
        val intent = Intent()
        intent.component =
            ComponentName.unflattenFromString("com.android,settings/.Settings\$AppAndNotificationDashboardActivity")
        return intent
    }

    override fun getBackgroundRunIntent(context: Context): Intent {
        return getAppInfoIntent(context)
    }

    override fun getBackgroundPopIntent(context: Context): Intent {
        val intent = Intent()
        intent.component = ComponentName(
            "com.huawei.systemmanager",
            "com.huawei.permissionmanager.ui.MainActivity"
        )
        return intent
    }
}