package com.androidtmc.android_kotlin_base.base

import android.app.Application
import android.content.Context

import com.androidtmc.android_kotlin_base.delegation.App
import com.androidtmc.android_kotlin_base.delegation.AppDelegate
import com.androidtmc.android_kotlin_base.di.component.AppComponent


open class BaseApplication : Application(), App {
    private lateinit var mAppDelegate: AppDelegate

    override fun onCreate() {
        super.onCreate()
        this.mAppDelegate = AppDelegate(this)
        this.mAppDelegate.onCreate()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        //        MultiDex.install(this);
    }

    override fun onTerminate() {
        super.onTerminate()
        mAppDelegate.onTerminal()
    }

    override fun getAppComponent(): AppComponent {
        return mAppDelegate.getAppComponent()
    }
}
