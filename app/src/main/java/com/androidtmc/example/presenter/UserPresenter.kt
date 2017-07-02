package com.androidtmc.example.presenter

import android.util.Log
import com.androidtmc.android_kotlin_base.base.BasePresenter
import com.androidtmc.android_kotlin_base.di.scope.ActivityScope
import com.androidtmc.android_kotlin_base.utils.RxUtils
import com.androidtmc.example.contract.UserContract
import com.androidtmc.example.model.entity.User
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.components.support.RxFragment
import com.trello.rxlifecycle2.kotlin.bindUntilEvent
import javax.inject.Inject

/**
 * Created by cuong on 7/2/17.
 */
@ActivityScope
class UserPresenter @Inject constructor(model : UserContract.Model,view : UserContract.View)
    :BasePresenter<UserContract.Model,UserContract.View>(model,view) {

    fun createUser(user : User){
        mModel?.createUser(user)
                .compose(RxUtils.applySchedules(mView))
                .bindUntilEvent(mView as RxFragment, FragmentEvent.PAUSE)
                .subscribe({ Log.d("TAG", it.toString()) })
    }


}