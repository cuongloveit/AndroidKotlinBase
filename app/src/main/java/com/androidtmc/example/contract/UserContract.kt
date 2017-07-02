package com.androidtmc.example.contract

import com.androidtmc.android_kotlin_base.base.IModel
import com.androidtmc.android_kotlin_base.base.IView
import com.androidtmc.example.model.entity.User
import io.reactivex.Flowable

/**
 * Created by cuong on 7/2/17.
 */
interface UserContract {
    interface View : IView {


    }

    interface Model : IModel {
        fun createUser(user: User): Flowable<User>

    }
}