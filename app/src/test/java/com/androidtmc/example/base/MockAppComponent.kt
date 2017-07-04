package com.androidtmc.example.base

import com.androidtmc.android_kotlin_base.di.module.AppModule
import com.androidtmc.android_kotlin_base.di.module.ClientModule
import com.androidtmc.android_kotlin_base.di.module.GlobalModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by cuong on 7/4/17.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class, ClientModule::class, GlobalModule::class))
interface MockAppComponent {

}