package com.example.baselibrary.setting

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings

/**
 * @author ym on 2024/1/3
 * 默认系统设置项
 */
class NormalSetting : ISystemSetting {
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
        return getNormalSettingIntent(context)
    }

    override fun getBackgroundRunIntent(context: Context): Intent {
        return getAppInfoIntent(context)
    }

}