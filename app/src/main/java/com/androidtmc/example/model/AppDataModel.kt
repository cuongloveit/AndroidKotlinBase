package com.androidtmc.example.model

import com.androidtmc.android_kotlin_base.base.BaseModel
import com.androidtmc.android_kotlin_base.base.IAPIManager
import com.androidtmc.android_kotlin_base.di.scope.ActivityScope
import com.androidtmc.example.contract.UserContract
import com.androidtmc.example.model.api.UserService
import com.androidtmc.example.model.entity.User
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by cuong on 7/2/17.
 */
@ActivityScope
class AppDataModel @Inject constructor(var apiManager: IAPIManager) : BaseModel(apiManager), UserContract.Model {


    override fun createUser(user: User): Flowable<User> {
        return apiManager.obtainRetrofitServices(UserService::class.java).createUser(user.name, user.email)
    }


}