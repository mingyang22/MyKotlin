package com.example.mykotlin

import android.app.Application
import android.content.Context

/**
 * @author yang on 2020/6/6
 */
class MyApplication : Application() {
    companion object {
        lateinit var context: Context
        const val TOKEN = ""
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

}