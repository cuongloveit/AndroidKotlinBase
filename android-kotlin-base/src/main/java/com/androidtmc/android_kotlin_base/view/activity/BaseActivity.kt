package com.androidtmc.android_kotlin_base.view.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.androidtmc.android_kotlin_base.R
import com.androidtmc.android_kotlin_base.base.IActivity
import com.androidtmc.android_kotlin_base.base.IPresenter
import com.androidtmc.android_kotlin_base.view.fragment.BaseFragment
import com.androidtmc.android_kotlin_base.view.fragment.FragmentHelper
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import javax.inject.Inject

/**
 * Created by cuong on 7/1/17.
 */
abstract class BaseActivity<P : IPresenter> : RxAppCompatActivity(), OnBaseAction, IActivity {
    @Inject
    lateinit var presenter: P
    lateinit var fragmentHelper: FragmentHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentHelper = FragmentHelper(supportFragmentManager, R.id.fragment_content)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }


    override fun showDialog(message: String) {

    }

    override fun showDialog(message: String, textButton: String, onClickListener: View.OnClickListener) {

    }

    override fun showLoader(isShow: Boolean) {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun changeFragment(fragment: BaseFragment<*>, isBackStack: Boolean) {
        fragmentHelper.replaceFragment(fragment, isBackStack, R.anim.fade_in, R.anim.fade_out)
    }

}