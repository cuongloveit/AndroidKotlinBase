package com.androidtmc.example.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidtmc.android_kotlin_base.view.fragment.BaseMainFragment
import com.androidtmc.android_kotlin_base.view.toolbar.ToolbarHelper
import com.androidtmc.example.R
import com.androidtmc.example.presenter.UserPresenter
import com.androidtmc.example.view.activity.MainActivity

/**
 * Created by cuong on 7/2/17.
 */
class MainFragment : BaseMainFragment<UserPresenter>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitleToolbar("Kotlin Demo")
        getMyToolbarHelper()?.showLeftButton(R.mipmap.ic_menu_white_24dp, View.OnClickListener {
            //click left button
        })
    }


    override fun setupFragmentComponent() {
    }

    override fun setTitleToolbar(msg: String) {
        getMainActivity().setTitleToolbar(msg)

    }

    override fun setTitleToolbar(msg: String, font: String) {
        getMainActivity().setTitleToolbar(msg, font)
    }

    override fun setTitleMainColor(color: Int) {
        getMainActivity().setToolbarColor(color)
    }

    override fun getMyToolbarHelper(): ToolbarHelper? {

        return getMainActivity().getMyToolbarHelper()
    }


    fun getMainActivity(): MainActivity {
        return activity as MainActivity
    }
    override fun getLayoutResource(): Int {
        return R.layout.fragemetn_main
    }
}