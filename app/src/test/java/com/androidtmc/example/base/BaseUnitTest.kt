package com.androidtmc.example.base

import com.androidtmc.android_kotlin_base.base.IAPIManager
import com.androidtmc.android_kotlin_base.di.component.AppComponent
import org.junit.Before
import org.robolectric.RuntimeEnvironment
import retrofit2.Retrofit



/**
 * Created by cuong on 7/4/17.
 */
open class BaseUnitTest {
     lateinit var mRetrofit: Retrofit
     lateinit var apiManage: IAPIManager
     lateinit var mAppComponent: AppComponent


    @Before
    open fun setUp() {
        mAppComponent = (RuntimeEnvironment.application as MockApplication).getAppComponent()
        mRetrofit = (RuntimeEnvironment.application as MockApplication).getAppComponent().retrofit()
        apiManage = (RuntimeEnvironment.application as MockApplication).getAppComponent().getIAPIManger()

    }
}