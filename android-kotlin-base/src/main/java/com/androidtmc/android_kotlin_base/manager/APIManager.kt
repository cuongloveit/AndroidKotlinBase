package com.androidtmc.android_kotlin_base.manager

import com.androidtmc.android_kotlin_base.base.IAPIManager
import com.androidtmc.android_kotlin_base.utils.CheckException
import retrofit2.Retrofit
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by cuong on 6/30/17.
 */
@Singleton
class APIManager @Inject constructor() :IAPIManager {
    override fun injectRetrofitService(vararg services: Class<*>) {
        services
                .filterNot { mRetrofitServiceCache.containsKey(it.simpleName) }
                .forEach { mRetrofitServiceCache.put(it.simpleName, mRetrofit.create(it)) }
    }

    override fun <T> obtainRetrofitServices(service: Class<T>): T {
        CheckException.checkState(mRetrofitServiceCache.containsKey(service.simpleName),
                "Unable find " + service.simpleName + ",first call injectRetrofitServices it in AppConfig")
        return mRetrofitServiceCache[service.simpleName] as T
    }

    private val mRetrofitServiceCache = LinkedHashMap<String, Any>()
    private lateinit var mRetrofit: Retrofit




}