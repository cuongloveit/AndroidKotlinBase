package com.androidtmc.android_kotlin_base.base

import com.androidtmc.android_kotlin_base.di.component.AppComponent
import com.androidtmc.android_kotlin_base.view.fragment.BaseFragment

/**
 * Created by cuong on 7/1/17.
 */
interface IActivity{
    fun setupActivityComponent(appComponent: AppComponent) {

    }

    fun changeFragment(fragment : BaseFragment<*>, isBackStack: Boolean)


}