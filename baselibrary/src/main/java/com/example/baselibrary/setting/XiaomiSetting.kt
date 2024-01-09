package com.example.baselibrary.setting

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings

/**
 * @author ym on 2024/1/3
 * 小米系统设置项
 */
class XiaomiSetting : ISystemSetting {
    override fun getNotificationSettingIntent(context: Context): Intent {
        val intent = Intent()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
        } else {
            val componentName =
                ComponentName.unflattenFromString("com.android.settings/.Settings\$NotificationFilterActivity")
            intent.component = componentName
            intent.putExtra("appName", context.applicationInfo.loadLabel(context.packageManager))
            intent.putExtra("packageName", context.applicationInfo.packageName)
        }
        return intent
    }

    override fun getAppRingingIntent(context: Context): Intent {
        return getNotificationSettingIntent(context)
    }

    override fun getAudibleModeIntent(context: Context): Intent {
        return Intent(Settings.ACTION_SOUND_SETTINGS)
    }

    override fun getPowerSaveModeIntent(context: Context): Intent {
        return Intent(Intent.ACTION_POWER_USAGE_SUMMARY)
    }

    override fun getZenModeIntent(context: Context): Intent {
        return Intent(Settings.ACTION_SOUND_SETTINGS)
    }

    override fun getAutoStartIntent(context: Context): Intent {
        val intent = Intent()
        val componentName = ComponentName(
            "com.miui.securitycenter",
            "com.miui.permcenter.autostart.AutoStartManagementActivity"
        )
        intent.component = componentName
        intent.putExtra("package_name", context.applicationInfo.packageName)
        return intent
    }

    override fun getBackgroundRunIntent(context: Context): Intent {
        val intent = Intent()
        val componentName = ComponentName(
            "com.miui.powerkeeper",
            "com.miui.powerkeeper.ui.HiddenAppsConfigActivity"
        )
        intent.component = componentName
        intent.putExtra("package_name", context.applicationInfo.packageName)
        return intent
    }

    override fun getBackgroundPopIntent(context: Context): Intent {
        val intent = Intent("miui.intent.actiOn.APP PERM EDITOR")
        intent.component = ComponentName(
            "com.miui.securitycenter",
            "com,miui.permcenter,permissions.PermissionsEditorActivity"
        )
        intent.putExtra("extra_pkgname", context.packageName)
        return intent
    }

}