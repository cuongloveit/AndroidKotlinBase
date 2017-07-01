package com.androidtmc.android_kotlin_base.apphandle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.androidtmc.android_kotlin_base.base.IActivity
import com.androidtmc.android_kotlin_base.delegation.ActivityDelegate
import javax.inject.Inject

/**
 * Created by cuong on 7/1/17.
 */
class ActivityLifecycle : Application.ActivityLifecycleCallbacks {


    private var mApplication: Application



    @Inject
    constructor(application: Application){
        mApplication = application
    }



    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {

        if (activity is IActivity && activity.intent != null) {
            var activityDelegate: ActivityDelegate? = getActivityDelegate(activity)
            if (activityDelegate == null) {
                activityDelegate = ActivityDelegate(activity)
                activity.intent.putExtra(ActivityDelegate.ACTIVITY_DELEGATE, activityDelegate)
            }
            activityDelegate.onCreate(savedInstanceState)
        }


    }

    override fun onActivityPaused(activity: Activity?) {
    }

    override fun onActivityResumed(activity: Activity?) {
    }

    override fun onActivityStarted(activity: Activity?) {

        val delegate :ActivityDelegate = getActivityDelegate(activity!!)


    }

    override fun onActivityDestroyed(activity: Activity?) {
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
    }

    override fun onActivityStopped(activity: Activity?) {
    }

    private fun getActivityDelegate(activity: Activity): ActivityDelegate {
        var activityDelegate: ActivityDelegate? = null
        if (activity is IActivity && activity.intent != null) {
            activityDelegate = activity.intent
                    .getSerializableExtra(ActivityDelegate.ACTIVITY_DELEGATE) as ActivityDelegate
        }
        return activityDelegate!!
    }

}