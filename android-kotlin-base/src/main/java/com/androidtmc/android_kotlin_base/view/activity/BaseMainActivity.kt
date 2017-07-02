package com.androidtmc.android_kotlin_base.view.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.androidtmc.android_kotlin_base.R
import com.androidtmc.android_kotlin_base.base.IPresenter
import com.androidtmc.android_kotlin_base.view.toolbar.OnCallBackToolbarAction
import com.androidtmc.android_kotlin_base.view.toolbar.ToolbarHelper

/**
 * Created by cuong on 7/1/17.
 */
abstract class BaseMainActivity<P : IPresenter, T : ToolbarHelper> : BaseActivity<P>(), OnCallBackToolbarAction {


    private val NULL_TOOLBAR_EX = "Can't find toolbar of this activity. Please checking it. Note: With raw id : R.id.toolbar"
    var toolbarHelper: ToolbarHelper? = null
    protected var toolbar: Toolbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())
        toolbar = findViewById(R.id.toolbar) as Toolbar
        try {
            setupToolbar()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

    }

    abstract fun getLayoutResource(): Int

    override fun onCallBackToolbar() {
        fragmentHelper.popBackStack()
    }

    override fun setTitleToolbar(msg: String) {
        toolbarHelper?.setTitle(msg)
    }

    override fun setTitleToolbar(msg: String, font: String) {
        toolbarHelper?.setTitle(msg, font)
    }

    override fun setTitleMainColor(color: Int) {
        toolbarHelper?.setTitleMainColor(color)
    }

    @Throws(IllegalAccessException::class)
    private fun setupToolbar() {
        if (toolbar == null) {
            throw NullPointerException(NULL_TOOLBAR_EX)
        } else {
            setSupportActionBar(toolbar)
            toolbar?.setBackgroundResource(onColorOfToolbar())
            toolbarHelper = getMyToolbarHelper()
            if (toolbarHelper == null) {
                toolbarHelper = ToolbarHelper(toolbar!!)
            }
            toolbarHelper?.showLeftButton(onImageForLeftButtonToolbar(), null)
        }
    }

    override fun getMyToolbarHelper(): T? {
        toolbarHelper?.let {
            return toolbarHelper as T
        }
        return null
    }




    abstract fun onColorOfToolbar(): Int

    abstract fun onImageForLeftButtonToolbar(): Int


}