package com.androidtmc.android_kotlin_base.base

import android.support.annotation.IdRes

/**
 * Created by cuong on 7/1/17.
 */
interface IBaseMainFragment {
    fun setToolbarTitle(title :String)
    fun setToolbarColor(@IdRes color :Int)
    fun showToolbar(isShow : Boolean)

}