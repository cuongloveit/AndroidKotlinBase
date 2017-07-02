package com.androidtmc.android_kotlin_base.view.toolbar

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.Toolbar
import android.view.View
import com.androidtmc.android_kotlin_base.R

/**
 * Created by cuong on 7/1/17.
 */
open class ToolbarHelper : OnToolbarAction {


    private var toolbar: Toolbar

    private var context: Context?

    private var leftBtn: AppCompatTextView? = null

    private var rightBtn: AppCompatTextView? = null

    private var tvTitle: AppCompatTextView? = null

    @Throws(IllegalAccessException::class)
    constructor(toolbar: Toolbar) {
        this.toolbar = toolbar

        context = toolbar.context
        toolbar.findViewById(R.id.left_button)?.let {
            leftBtn = toolbar.findViewById(R.id.left_button) as AppCompatTextView
        }
        toolbar.findViewById(R.id.right_button)?.let {
            rightBtn = toolbar.findViewById(R.id.right_button) as AppCompatTextView
        }
        toolbar.findViewById(R.id.tvTitleToolbar)?.let {
            tvTitle = toolbar.findViewById(R.id.tvTitleToolbar) as AppCompatTextView
        }

        rightBtn?.isClickable = true
        leftBtn?.isClickable = true
        tvTitle?.isClickable = true
    }

    override fun setTitle(title: String) {
        tvTitle?.text = title
    }

    override fun setTitle(title: String, font: String) {
        tvTitle?.text = title
    }

    override fun setTitleMainColor(color: Int) {
    }

    override fun showToolbar(isShow: Boolean) {
    }

    override fun showLeftButton(isShow: Boolean) {
        leftBtn?.visibility = if (isShow) View.VISIBLE else View.INVISIBLE
    }

    override fun showLeftButton(isShow: Boolean, onClickListener: View.OnClickListener) {
    }

    override fun showLeftButton(iconRes: Int, onClickListener: View.OnClickListener) {
        leftBtn?.setCompoundDrawablesWithIntrinsicBounds(iconRes, 0, 0, 0)
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
