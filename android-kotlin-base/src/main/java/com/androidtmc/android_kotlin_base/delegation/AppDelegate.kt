package com.androidtmc.android_kotlin_base.delegation

import android.app.Application
import com.androidtmc.android_kotlin_base.apphandle.ActivityLifecycle
import com.androidtmc.android_kotlin_base.base.ConfigModule
import com.androidtmc.android_kotlin_base.di.component.AppComponent
import com.androidtmc.android_kotlin_base.di.component.DaggerAppComponent
import com.androidtmc.android_kotlin_base.di.module.AppModule
import com.androidtmc.android_kotlin_base.di.module.ClientModule
import com.androidtmc.android_kotlin_base.di.module.GlobalModule
import com.androidtmc.android_kotlin_base.utils.ManifestParser
import javax.inject.Inject

/**
 * Created by cuong on 7/1/17.
 */
class AppDelegate : App {

   @Inject
    internal lateinit var mActivityLifeCycle: ActivityLifecycle

    private var mConfigModules: List<ConfigModule>? = null
    private var mAppLifeCycles: List<LifeCycle>? = ArrayList()
    private var mActivityLifeCycles: ArrayList<Application.ActivityLifecycleCallbacks>? = ArrayList()
     var mApplication: Application?
    var mAppComponent: AppComponent? = null

    @Inject
    constructor(application: Application) {
        this.mApplication = application
        this.mConfigModules = ManifestParser(application).parse()
        mConfigModules?.forEach {
            it.injectActivityLifeCycles(application, mActivityLifeCycles!!)
        }

    }

    fun onCreate() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(AppModule(mApplication!!))
                .clientModule(ClientModule())
                .globalModule(getGlobalModule(mApplication!!, mConfigModules!!))
                .build()

        mAppComponent?.inject(this)
                 (mAppComponent?.extras()!!.extract).put(ConfigModule::class.java.simpleName, mConfigModules!!)
        mConfigModules?.forEach {
            it.registerComponents(mApplication!!, mAppComponent!!.getIAPIManger())
        }
        mApplication?.registerActivityLifecycleCallbacks(mActivityLifeCycle)
        mActivityLifeCycles?.forEach {
            mApplication?.registerActivityLifecycleCallbacks(it)
        }
        mAppLifeCycles?.forEach {
            it.onCreate(mApplication!!)
        }


    }

    fun onTerminal() {
        mAppLifeCycles?.forEach {
            it.onTerminate(mApplication!!)
        }
        mApplication?.unregisterActivityLifecycleCallbacks(mActivityLifeCycle)
        mActivityLifeCycles?.forEach {
            mApplication?.unregisterActivityLifecycleCallbacks(it)
        }
        this.mAppLifeCycles = null
        // this.mActivityLifeCycle = null
        this.mActivityLifeCycles = null
        this.mAppComponent = null
        this.mApplication = null
    }


    override fun getAppComponent(): AppComponent {
        return mAppComponent!!
    }

    interface LifeCycle {
        fun onCreate(application: Application)

        fun onTerminate(application: Application)
    }

    private fun getGlobalModule(context: Application, modules: List<ConfigModule>): GlobalModule {
        val builder = GlobalModule.builder()

        for (module in modules) {
            module.applyOptions(context, builder)
        }
        return builder.build()
    }


}