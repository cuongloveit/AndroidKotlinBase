package com.androidtmc.android_kotlin_base.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidtmc.android_kotlin_base.base.IFragment
import com.androidtmc.android_kotlin_base.base.IPresenter
import com.androidtmc.android_kotlin_base.view.activity.BaseActivity
import com.trello.rxlifecycle2.components.support.RxFragment
import javax.inject.Inject

/**
 * Created by cuong on 6/30/17.
 * Handling base function, show dialog, message,..
 */
abstract class BaseFragment<P : IPresenter> : RxFragment(), IFragment {
    @Inject
    lateinit var presenter: P
    protected lateinit var rootView: View


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(getLayoutResource(),container,false)
        return rootView
    }

    abstract fun getLayoutResource(): Int


    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    fun getBaseActivity(): BaseActivity<*> {
        if (activity is BaseActivity<*>) {
            return activity as BaseActivity<*>
        } else {
            throw NullPointerException("Can't cast this activity to BaseActivity")
        }
    }

    override fun showMessage(message: String){
        getBaseActivity().showMessage(message)
    }

    override fun showDialog(message: String){
        getBaseActivity().showDialog(message)

    }
    override fun showDialog(message: String,textButton : String, onClickListener: View.OnClickListener){
        getBaseActivity().showDialog(message,textButton,onClickListener)

    }

    override fun showLoader(isShow : Boolean){
        getBaseActivity().showLoader(isShow)

    }


}