package com.androidtmc.android_kotlin_base.di.component

import com.androidtmc.android_kotlin_base.base.IAPIManager
import com.androidtmc.android_kotlin_base.delegation.AppDelegate
import com.androidtmc.android_kotlin_base.di.module.AppModule
import com.androidtmc.android_kotlin_base.di.module.ClientModule
import com.androidtmc.android_kotlin_base.di.module.GlobalModule
import com.androidtmc.android_kotlin_base.utils.ExtractUtils
import com.google.gson.Gson
import dagger.Component
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.io.File
import javax.inject.Singleton

/**
 * Created by cuong on 6/30/17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, ClientModule::class, GlobalModule::class))
interface AppComponent {
    fun inject(appDelegate: AppDelegate)
    fun extras(): ExtractUtils
    fun getIAPIManger(): IAPIManager
    fun getRetrofit(): Retrofit
    fun okHttpClient(): OkHttpClient
    fun cacheFile(): File
    fun getGson(): Gson
    fun httpUrl(): HttpUrl


}
