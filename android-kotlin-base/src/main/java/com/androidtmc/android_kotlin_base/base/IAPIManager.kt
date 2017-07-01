package com.androidtmc.android_kotlin_base.base

/**
 * Created by cuong on 6/30/17.
 */
interface IAPIManager{
    /**
     * @param services
     */
     fun injectRetrofitService(vararg services: Class<*>)

    /**
     * @param service
     * *
     * @param <T>
     * *
     * @return
    </T> */
     fun <T> obtainRetrofitServices(service: Class<T>): T
}
