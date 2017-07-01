package com.androidtmc.android_kotlin_base.delegation

import com.androidtmc.android_kotlin_base.di.component.AppComponent

/**
 * Created by cuong on 7/1/17.
 */
interface App {
    fun getAppComponent(): AppComponent
}