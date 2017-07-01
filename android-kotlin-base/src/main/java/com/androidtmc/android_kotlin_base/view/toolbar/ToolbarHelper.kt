package com.androidtmc.android_kotlin_base.view.toolbar

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.Toolbar
import android.view.View
import com.androidtmc.android_kotlin_base.R

/**
 * Created by cuong on 7/1/17.
 */
class ToolbarHelper : OnToolbarAction {


    private var toolbar: Toolbar

    private var context: Context?

    private var leftBtn: AppCompatTextView?

    private var rightBtn: AppCompatTextView?

    private var tvTitle: AppCompatTextView?

    @Throws(IllegalAccessException::class)
    constructor(toolbar: Toolbar) {
        this.toolbar = toolbar

        context = toolbar.context
        leftBtn = toolbar.findViewById(R.id.left_button) as AppCompatTextView
        rightBtn = toolbar.findViewById(R.id.right_button) as AppCompatTextView

        if (rightBtn == null) {
            throw IllegalAccessException("Can't find this right button.")
        }
        rightBtn?.isClickable = true

        if (leftBtn == null) {
            throw IllegalAccessException("Can't find this Left button")
        }
        leftBtn?.isClickable = true

        tvTitle = toolbar.findViewById(R.id.tvTitleToolbar) as AppCompatTextView
        if (tvTitle == null) {
            throw IllegalAccessException("Can't find this Title TextView")
        }
        tvTitle?.isClickable = true
    }

    override fun setTitle(title: String) {
    }

    override fun setTitle(title: String, font: String) {
    }

    override fun setTitleMainColor(color: Int) {
    }

    override fun showToolbar(isShow: Boolean) {
    }

    override fun showLeftButton(isShow: Boolean) {
    }

    override fun showLeftButton(isShow: Boolean, onClickListener: View.OnClickListener) {
    }

    override fun showLeftButton(iconRes: Int, onClickListener: View.OnClickListener) {
    }

    override fun showLeftButton(text: String, onClickListener: View.OnClickListener) {
    }

    override fun showRightButton(isShow: Boolean) {
    }

    override fun showRightButton(text: String, onClickListener: View.OnClickListener) {
    }

    override fun showRightButton(iconRes: Int, onClickListener: View.OnClickListener) {


    }

    override fun showLeftButton(onImageForLeftButtonToolbar: Int, nothing: Nothing?) {
    }

}
