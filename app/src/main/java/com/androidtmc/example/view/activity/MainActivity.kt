package com.androidtmc.example.view.activity

import android.os.Bundle
import com.androidtmc.android_kotlin_base.view.activity.BaseMainActivity
import com.androidtmc.example.R
import com.androidtmc.example.presenter.UserPresenter
import com.androidtmc.example.view.fragment.MainFragment
import com.androidtmc.example.view.toolbar.MyToolbarHelper

class MainActivity : BaseMainActivity<UserPresenter, MyToolbarHelper>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changeFragment(MainFragment(), false)
    }


    override fun setToolbarColor(color: Int) {
    }

    override fun showToolbar(isShow: Boolean) {
    }

    override fun onColorOfToolbar() = R.color.colorPrimary

    override fun onImageForLeftButtonToolbar() = R.mipmap.ic_menu_white_24dp


    override fun getLayoutResource() = R.layout.activity_main

    fun testing() = "testing"

}
