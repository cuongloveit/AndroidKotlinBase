package com.androidtmc.android_kotlin_base.di.module

import android.app.Application
import android.content.Context
import com.androidtmc.android_kotlin_base.BuildConfig
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by cuong on 7/1/17.
 */

@Module
class ClientModule {
    private val CACHE_SIZE = 10 * 1024 * 1024 //10MiB
    private val CONNECT_TIMEOUT = 10
    private val READ_TIMEOUT = 30
    private val WRITE_TIMEOUT = 60
    //Todo Application's injected from AppModule

    @Provides
    @Singleton
    fun provideCache(cacheFile: File): Cache {
        val cache = Cache(cacheFile, CACHE_SIZE.toLong())
        return cache
    }

    @Provides
    @Singleton
    fun provideOkHttpBuilder(): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
        return builder
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(application: Application, builder: OkHttpClient.Builder, cache: Cache?, httpConfiguration: OkHttpConfiguration?): OkHttpClient {
        builder.cache(cache)
                .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .addInterceptor(LoggingInterceptor.Builder()
                        .loggable(BuildConfig.DEBUG)
                        .setLevel(Level.BODY)
                        .request("Request")
                        .response("Response")
                        .build())

//        if (interceptors != null && !interceptors.isEmpty()) {
//            for (interceptor in interceptors) {
//                builder.addInterceptor(interceptor)
//            }
//        }

        httpConfiguration?.configOkHttp(application, builder)

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
    }

    @Provides
    @Singleton
    fun provideRetrofit(application: Application, okHttpClient: OkHttpClient, builder: Retrofit.Builder, httpUrl: HttpUrl, configuration: RetrofitConfiguration?): Retrofit {
        builder.baseUrl(httpUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)

        configuration?.configRetrofit(application, builder)
        return builder.build()
    }


    interface OkHttpConfiguration {
        fun configOkHttp(context: Context, builder: OkHttpClient.Builder)
    }

    interface RetrofitConfiguration {
        fun configRetrofit(context: Context, builder: Retrofit.Builder)
    }
}