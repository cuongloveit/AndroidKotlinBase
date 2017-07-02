package vn.eazy.base.mvp.example.mvp.di.module

import android.app.Application
import android.content.Context
import android.support.v4.app.FragmentManager
import com.androidtmc.android_kotlin_base.base.ConfigModule
import com.androidtmc.android_kotlin_base.base.IAPIManager
import com.androidtmc.android_kotlin_base.di.module.GlobalModule



class AppConfig : ConfigModule {



    override fun applyOptions(context: Context, builder: GlobalModule.Builder) {
        builder.baseUrl("http://echo.jsontest.com/key/value/")
    }

    override fun registerComponents(context: Context,iapiManager: IAPIManager ) {
       // repositoryManager.injectRetrofitService(UserService::class.java)
    }

    override fun injectActivityLifeCycles(context: Context, activityLifeCycles: List<Application.ActivityLifecycleCallbacks>) {
    }

    override fun injectFragmentLifeCycles(context: Context, fragmentLifecycleCallbacks: List<FragmentManager.FragmentLifecycleCallbacks>) {
    }



}
