package com.androidtmc.android_kotlin_base.view.toolbar

import android.view.View

/**
 * Created by cuong on 7/1/17.
 */
interface OnToolbarAction {

     fun setTitle(title: String)

     fun setTitle(title: String, font: String)

     fun setTitleMainColor(color: Int)

     fun showToolbar(isShow: Boolean)

     fun showLeftButton(isShow: Boolean)

     fun showLeftButton(isShow: Boolean, onClickListener: View.OnClickListener)

     fun showLeftButton(iconRes: Int, onClickListener: View.OnClickListener)

     fun showLeftButton(text: String, onClickListener: View.OnClickListener)

     fun showRightButton(isShow: Boolean)

     fun showRightButton(text: String, onClickListener: View.OnClickListener)

     fun showRightButton(iconRes: Int, onClickListener: View.OnClickListener)
     fun showLeftButton(onImageForLeftButtonToolbar: Int, nothing: Nothing?)
}