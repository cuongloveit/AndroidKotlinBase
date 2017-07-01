package com.androidtmc.android_kotlin_base.delegation

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import com.androidtmc.android_kotlin_base.base.IActivity
import java.io.Serializable

/**
 * Created by cuong on 7/1/17.
 */
class ActivityDelegate(var activity: Activity ): Activity(),Serializable {
     var iActivity: IActivity = activity as IActivity

    companion object {
        var ACTIVITY_DELEGATE :String = "activity delegate"
    }


    @SuppressLint("MissingSuperCall") public
    override fun onCreate(savedInstanceState: Bundle?) {

        iActivity.setupActivityComponent((activity.application as App).getAppComponent())
    }

    @SuppressLint("MissingSuperCall")
    override fun onDestroy() {

    }

    @SuppressLint("MissingSuperCall")
    override fun onStart() {

    }


}