package com.androidtmc.android_kotlin_base.view.fragment

import android.support.v4.app.Fragment
import android.view.View
import com.androidtmc.android_kotlin_base.base.IFragment
import com.androidtmc.android_kotlin_base.base.IPresenter
import com.androidtmc.android_kotlin_base.view.activity.BaseActivity
import javax.inject.Inject

/**
 * Created by cuong on 6/30/17.
 */
abstract class BaseFragment<P : IPresenter> : Fragment(), IFragment {
    @Inject
    lateinit var presenter: P
    protected lateinit var rooView: View

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    fun getBaseActivity(): BaseActivity {
        if (activity is BaseActivity) {
            return activity as BaseActivity
        } else {
            throw NullPointerException("Can't cast this activity to BaseActivity")
        }
    }

    fun showMessage(message: String){
        getBaseActivity().showMessage(message)
    }


}