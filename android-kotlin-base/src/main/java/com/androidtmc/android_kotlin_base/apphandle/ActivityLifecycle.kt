package com.androidtmc.android_kotlin_base.apphandle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import com.androidtmc.android_kotlin_base.base.ConfigModule
import com.androidtmc.android_kotlin_base.base.IActivity
import com.androidtmc.android_kotlin_base.delegation.ActivityDelegate
import com.androidtmc.android_kotlin_base.utils.ExtractUtils
import java.util.*
import javax.inject.Inject

/**
 * Created by cuong on 7/1/17.
 */
class ActivityLifecycle : Application.ActivityLifecycleCallbacks {
    private var mApplication: Application? = null
    private var mExtras: Map<String, Any>? = null
    private var mFragmentLifeCycle: FragmentLifeCycle? = null
    private var mFragmentLifeCycles: List<FragmentManager.FragmentLifecycleCallbacks>? = null



    @Inject
    constructor(application: Application, extract: ExtractUtils){
        this.mApplication = application
        this.mExtras = extract.extract
    }
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

        if (activity is IActivity && activity.intent != null) {
            var activityDelegate = getActivityDelegate(activity)
            if (activityDelegate == null) {
                activityDelegate = ActivityDelegate(activity)
                activity.intent.putExtra(ActivityDelegate.ACTIVITY_DELEGATE, activityDelegate)
            }
            activityDelegate.onCreate(savedInstanceState)
        }

        if (activity is FragmentActivity) {
            if (mFragmentLifeCycle == null) {
                mFragmentLifeCycle = FragmentLifeCycle()
            }
            activity.supportFragmentManager.registerFragmentLifecycleCallbacks(mFragmentLifeCycle, true)
        }

        if (mFragmentLifeCycles == null) {
            mFragmentLifeCycles = ArrayList<FragmentManager.FragmentLifecycleCallbacks>()
            val configModules = mExtras!![ConfigModule::class.java.getSimpleName()] as List<ConfigModule>
            for (module in configModules) {
                module.injectFragmentLifeCycles(mApplication!!, mFragmentLifeCycles!!)
            }
        }

        for (lifecycleCallbacks in mFragmentLifeCycles!!) {
            (activity as FragmentActivity).supportFragmentManager.registerFragmentLifecycleCallbacks(lifecycleCallbacks, true)
        }

    }

    override fun onActivityStarted(activity: Activity) {
        val delegate = getActivityDelegate(activity)
        delegate?.onStart()
    }

    override fun onActivityResumed(activity: Activity) {
        val delegate = getActivityDelegate(activity)
        delegate?.onResume()
    }

    override fun onActivityPaused(activity: Activity) {
        val delegate = getActivityDelegate(activity)
        delegate?.onPause()
    }

    override fun onActivityStopped(activity: Activity) {
        val delegate = getActivityDelegate(activity)
        delegate?.onStop()
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        val delegate = getActivityDelegate(activity)
        delegate?.onSaveInstanceState(outState)
    }

    override fun onActivityDestroyed(activity: Activity) {
        val activityDelegate = getActivityDelegate(activity)
        if (activityDelegate != null) {
            activityDelegate.onDestroy()
            activity.intent.removeExtra(ActivityDelegate.ACTIVITY_DELEGATE)
        }
    }

    private fun getActivityDelegate(activity: Activity): ActivityDelegate? {
        var activityDelegate: ActivityDelegate? = null

        if (activity is IActivity && activity.intent != null) {

            activity.intent.getSerializableExtra(ActivityDelegate.ACTIVITY_DELEGATE)?.let {
                activityDelegate = activity.intent.getSerializableExtra(ActivityDelegate.ACTIVITY_DELEGATE) as ActivityDelegate

            }

        }
        return activityDelegate
    }

}