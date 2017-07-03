package com.androidtmc.example.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidtmc.android_kotlin_base.di.component.AppComponent
import com.androidtmc.android_kotlin_base.view.fragment.BaseMainFragment
import com.androidtmc.android_kotlin_base.view.toolbar.ToolbarHelper
import com.androidtmc.example.R
import com.androidtmc.example.contract.UserContract
import com.androidtmc.example.model.entity.User
import com.androidtmc.example.presenter.UserPresenter
import com.androidtmc.example.view.activity.MainActivity
import kotlinx.android.synthetic.main.fragement_main.*
import kotlinx.android.synthetic.main.fragement_main.view.*
import vn.eazy.base.mvp.example.mvp.di.component.DaggerUserComponent
import vn.eazy.base.mvp.example.mvp.di.module.UserModule

/**
 * Created by cuong on 7/2/17.
 */
class MainFragment : BaseMainFragment<UserPresenter>(),UserContract.View {



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
        rootView.btnCreate.setOnClickListener({
            val user = User(edtName.text.toString(),edtEmail.text.toString())
            presenter.createUser(user)

        })
    }


    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerUserComponent.builder().appComponent(appComponent)
                .userModule( UserModule(this))
                .build().inject(this)
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
        return R.layout.fragement_main
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onCreateUserSuccess(user: User) {
        tvName.text = "Name: ${user.name}"
        tvEmail.text = "Email: ${user.email}"
    }
}