package com.example.mykotlin.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

/**
 * @author ym on 2022/8/5
 * 日期工具类
 */
@RequiresApi(Build.VERSION_CODES.O)
object DateUtils {
    const val YMDHMS = "yyyy-MM-dd HH:mm:ss"
    const val YMD = "yyyy-MM-dd"
    const val MDHM = "MM-dd HH:mm"
    const val HM = "HH:mm"
    const val YMDHMS_CHINESE = "yyyy年MM月dd日 HH:mm:ss"
    const val YMD_CHINESE = "yyyy年MM月dd日"


    /**
     * 获取当前时间戳
     * @return Long 精确到毫秒
     */
    fun getCurrentMillisecond(): Long {
        return Instant.now().toEpochMilli()
    }

    /**
     * 获取当前时间戳
     * @return Long 精确到秒
     */
    fun getCurrentSecond(): Long {
        return Instant.now().epochSecond
    }

    /**
     * 将Long类型的时间戳转换成String
     * @param time Long 毫秒
     * @param format String 格式化格式
     * @return String
     */
    fun timeToString(time: Long, format: String = YMDHMS_CHINESE): String {
        if (time == 0L) return ""
        val ftf = DateTimeFormatter.ofPattern(format)
        return ftf.format(
            LocalDateTime.ofInstant(
                Instant.ofEpochMilli(time),
                ZoneId.systemDefault()
            )
        )
    }

}