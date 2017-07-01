package com.androidtmc.android_kotlin_base.view.fragment

import com.androidtmc.android_kotlin_base.base.IBaseMainFragment
import com.androidtmc.android_kotlin_base.base.IPresenter
import com.androidtmc.android_kotlin_base.view.activity.BaseMainActivity
import com.androidtmc.android_kotlin_base.view.toolbar.ToolbarHelper

/**
 * Created by cuong on 7/1/17.
 * Handling toolbar, menu
 */
abstract class BaseMainFragment<P : IPresenter> : BaseFragment<P>(),IBaseMainFragment {

    override fun setToolbarTitle(title: String) {
        getBaseActivity().setTitleToolbar(title)

    }

    override fun setToolbarColor(color: Int) {
        getBaseActivity().setToolbarColor(color)
    }

    override fun showToolbar(isShow: Boolean) {
        getBaseActivity().showToolbar(isShow)
    }

    override fun getToolbarHelper(): ToolbarHelper? {
        if (getBaseActivity() is BaseMainActivity<P>) {
            return getBaseActivity().toolbarHelper
        }
        return null
    }
}