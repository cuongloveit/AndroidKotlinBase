package com.androidtmc.example.user

import com.androidtmc.example.base.AppTestRunner
import com.androidtmc.example.base.BaseUnitTest
import com.androidtmc.example.model.AppDataModel
import com.androidtmc.example.model.entity.User
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by cuong on 7/4/17.
 */
@RunWith(AppTestRunner::class)
class CreateUserUnitTest : BaseUnitTest() {



    @Test
    fun createUser() {
        var user = User("Cuong","cuongloveit@gmail.com")
        var subscriber = AppDataModel(apiManage).createUser(user).test()
        subscriber.assertNoTimeout()
        Assertions.assertThat(subscriber.events[0][0]).isNotNull()


    }

}