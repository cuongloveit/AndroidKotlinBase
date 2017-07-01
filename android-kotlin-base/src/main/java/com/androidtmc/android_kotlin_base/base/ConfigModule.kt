package com.androidtmc.android_kotlin_base.base

import android.app.Application
import android.content.Context
import android.support.v4.app.FragmentManager
import com.androidtmc.android_kotlin_base.di.module.GlobalModule

/**
 * Created by cuong on 7/1/17.
 */
interface ConfigModule {

    /**
     * @param context
     * *
     * @param builder
     */
     fun applyOptions(context: Context, builder: GlobalModule.Builder)

    /**
     * @param context
     * *
     * @param repositoryManager
     */
     fun registerComponents(context: Context, iApiManager: IAPIManager)


    /**
     * @param context
     * *
     * @param activityLifeCycles
     */
     fun injectActivityLifeCycles(context: Context, activityLifeCycles: List<Application.ActivityLifecycleCallbacks>)

    /**
     * @param context
     * *
     * @param fragmentLifecycleCallbacks
     */
    fun injectFragmentLifeCycles(context: Context, fragmentLifecycleCallbacks: List<FragmentManager.FragmentLifecycleCallbacks>)
}