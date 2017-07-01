package com.androidtmc.android_kotlin_base.di.module

import android.app.Application
import android.content.Context
import android.os.Handler
import com.androidtmc.android_kotlin_base.base.IAPIManager
import com.androidtmc.android_kotlin_base.manager.APIManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton

/**
 * Created by cuong on 6/30/17.
 */
@Module
class AppModule(mApplication: Application?) {
    private lateinit var mApplication: Application

    fun AppModule(application: Application) {
        this.mApplication = application
    }

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @Singleton
    fun provideGson(application: Application, configuration: GsonConfiguration?): Gson {
        val builder = GsonBuilder()
        configuration?.configGson(application, builder)
        return builder.create()
    }

    @Provides
    @Singleton
    fun provideHandler(): Handler {
        return Handler()
    }

    @Provides
    @Singleton
    fun provideAPIManager(apiManager: APIManager): IAPIManager {
        return apiManager
    }


    @Provides
    @Singleton
    fun provideExtras(): Map<String, Any> {
        return HashMap()
    }

    interface GsonConfiguration {
        fun configGson(context: Context, builder: GsonBuilder)
    }
}