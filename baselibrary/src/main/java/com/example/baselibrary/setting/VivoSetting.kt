package com.example.baselibrary.setting

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings

/**
 * @author ym on 2024/1/3
 * vivo系统设置项
 */
class VivoSetting : ISystemSetting {
    override fun getNotificationSettingIntent(context: Context): Intent {
        return getNormalNotificationIntent(context)
    }

    override fun getAudibleModeIntent(context: Context): Intent {
        return Intent(Settings.ACTION_SOUND_SETTINGS)
    }

    override fun getPowerSaveModeIntent(context: Context): Intent {
        val intent = Intent()
        val componentName =
            ComponentName("com.igoo.powersaving", "com.igoo.powersaving.PowerSavingManagerActivity")
        intent.component = componentName
        return intent
    }

    override fun getZenModeIntent(context: Context): Intent {
        val intent = Intent()
        val componentName = ComponentName(
            "com.android.settings",
            "com.android.settings.Settings\$VivoZenModeSettingsActivity"
        )
        intent.component = componentName
        return intent
    }

    override fun getAutoStartIntent(context: Context): Intent {
        val intent = Intent()
        val componentName = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            ComponentName(
                "com.vivo.permissionmanager",
                "com.vivo.permissionmanager.activity BgStartUpManagerActivity"
            )
        } else {
            ComponentName(
                "com.vivo.permissionmanager",
                "com.vivo.permissionmanager.activity.PurviewTabActivity"
            )
        }
        intent.component = componentName
        return intent
    }

    override fun getBackgroundRunIntent(context: Context): Intent {
        return getPowerSaveModeIntent(context)
    }

    override fun getBackgroundPopIntent(context: Context): Intent {
        val intent = Intent()
        if (Build.MODEL.contains("Y85") && !Build.MODEL.contains("Y85A") ||
            Build.MODEL.contains("yiyo Y53L")
        ) {
            intent.setClassName(
                "com.vivo.permissionmanager",
                "com.vivo.permissionmanager.activity.PurviewTabActivity"
            )
            intent.putExtra("tabId", "1")
        } else {
            intent.setClassName(
                "com,vivo,permissionmanager",
                "com,vivo.permissionmanager.activity.SoftPermissionDetailActivity"
            )
            intent.action = "secure.intent.action.softPermissionDetail"
        }
        intent.putExtra("packagename", context.packageName)
        return intent
    }

}