package com.androidtmc.android_kotlin_base.di.module

import android.app.Application
import com.androidtmc.android_kotlin_base.utils.DataHelper
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.Interceptor
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by cuong on 7/1/17.
 */
@Module
class GlobalModule {
    private var mCacheFile: File?
    private var  mGsonConfiguration: AppModule.GsonConfiguration?
    private  var mOkHttpConfiguration: ClientModule.OkHttpConfiguration?
    private  var mRetrofitConfiguration: ClientModule.RetrofitConfiguration?
    private  var mInterceptors: List<Interceptor>?
    private  var baseUrl: String?


    constructor(builder: Builder){
        this.mCacheFile = builder.cacheFile
        this.baseUrl = builder.baseUrl
        this.mGsonConfiguration = builder.gsonConfiguration
        this.mInterceptors = builder.interceptors
        this.mRetrofitConfiguration = builder.retrofitConfiguration
        this.mOkHttpConfiguration = builder.okHttpConfiguration
    }
    companion object {
        fun builder(): Builder {
            return Builder()
        }
    }



    @Provides
    @Singleton
    fun provideCacheFile(application: Application): File {
        return mCacheFile ?: DataHelper.getCacheFile(application)
    }

    @Provides
    @Singleton
    fun provideGsonConfiguartion(): AppModule.GsonConfiguration? {
        return this.mGsonConfiguration
    }

//    @Provides
//    @Singleton
//    fun provideInterceptors(): List<Interceptor>? {
//        return mInterceptors
//    }

    @Provides
    @Singleton
    @Named("baseUrl")
    fun provideBaseUrl(): String {
        return baseUrl!!
    }

    @Provides
    @Singleton
    fun provideRetrofitConfiguration(): ClientModule.RetrofitConfiguration? {
        return this.mRetrofitConfiguration
    }

    @Provides
    @Singleton
    fun provideOkHttpConfiguration(): ClientModule.OkHttpConfiguration? {
        return this.mOkHttpConfiguration
    }

    @Provides
    @Singleton
    fun provideHttpUrl(@Named("baseUrl") baseUrl: String): HttpUrl {
        return HttpUrl.parse(baseUrl)!!
    }


    class Builder {
        var cacheFile: File? = null
        var gsonConfiguration: AppModule.GsonConfiguration? = null
        var interceptors: List<Interceptor>? = null
        var baseUrl: String? = null
        var retrofitConfiguration: ClientModule.RetrofitConfiguration? = null
        var okHttpConfiguration: ClientModule.OkHttpConfiguration? = null

        fun cacheFile(cacheFile: File): Builder {
            this.cacheFile = cacheFile
            return this
        }

        fun gsonConfiguration(gsonConfiguration: AppModule.GsonConfiguration): Builder {
            this.gsonConfiguration = gsonConfiguration
            return this
        }

        fun interceptors(interceptors: List<Interceptor>): Builder {
            this.interceptors = interceptors
            return this
        }

        fun baseUrl(baseUrl: String): Builder {
            this.baseUrl = baseUrl
            return this
        }

        fun retrofitConfiguration(retrofitConfiguration: ClientModule.RetrofitConfiguration): Builder {
            this.retrofitConfiguration = retrofitConfiguration
            return this
        }

        fun okHttpConfiguration(okHttpConfiguration: ClientModule.OkHttpConfiguration): Builder {
            this.okHttpConfiguration = okHttpConfiguration
            return this
        }


        fun build(): GlobalModule {
            return GlobalModule(this)
        }
    }
}