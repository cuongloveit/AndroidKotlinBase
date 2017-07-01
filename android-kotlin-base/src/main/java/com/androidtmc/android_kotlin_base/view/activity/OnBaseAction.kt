package com.androidtmc.android_kotlin_base.view.activity

import android.support.annotation.IdRes
import android.view.View
import com.androidtmc.android_kotlin_base.view.toolbar.ToolbarHelper

/**
 * Created by cuong on 7/1/17.
 */
interface OnBaseAction {
    fun setTitleToolbar(msg: String)

    fun setTitleToolbar(msg: String, font: String)

    fun setTitleMainColor(@IdRes color: Int)

    fun setToolbarColor(@IdRes color: Int)

    fun showToolbar(isShow: Boolean)

    @Throws(IllegalAccessException::class)
    fun getMyToolbarHelper(): ToolbarHelper?

    fun showMessage(message: String){
    }

    fun showDialog(message: String){

    }
    fun showDialog(message: String,textButton : String, onClickListener: View.OnClickListener){

    }

    fun showLoader(isShow : Boolean){

    }
}