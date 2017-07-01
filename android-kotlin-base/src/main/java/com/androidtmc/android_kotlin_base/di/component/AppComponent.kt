package com.androidtmc.android_kotlin_base.di.component

import com.androidtmc.android_kotlin_base.base.IAPIManager
import com.androidtmc.android_kotlin_base.delegation.AppDelegate
import com.androidtmc.android_kotlin_base.di.module.AppModule
import com.androidtmc.android_kotlin_base.di.module.ClientModule
import com.androidtmc.android_kotlin_base.di.module.GlobalModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by cuong on 6/30/17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, ClientModule::class, GlobalModule::class))
interface AppComponent {
    fun inject(appDelegate: AppDelegate)
    fun extras(): Map<String, Object>
    fun getIAPIManger(): IAPIManager


}
