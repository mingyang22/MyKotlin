package com.example.baselibrary.setting

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings

/**
 * @author ym on 2024/1/3
 * 荣耀系统设置项
 */
class HonorSetting : ISystemSetting {
    override fun getNotificationSettingIntent(context: Context): Intent {
        return getNormalNotificationIntent(context)
    }

    override fun getAudibleModeIntent(context: Context): Intent {
        return Intent(Settings.ACTION_SOUND_SETTINGS)
    }

    override fun getPowerSaveModeIntent(context: Context): Intent {
        return Intent(Intent.ACTION_POWER_USAGE_SUMMARY)
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
            ComponentName.unflattenFromString("com.android.settings/.settings\$AppAndNotificationDashboardActivity")
        return intent
    }

    override fun getBackgroundRunIntent(context: Context): Intent {
        return getAppInfoIntent(context)
    }
}