package com.androidtmc.example.mainactivity

import com.androidtmc.example.base.AppTestRunner
import com.androidtmc.example.base.BaseUnitTest
import com.androidtmc.example.view.activity.MainActivity
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric

/**
 * Created by cuong on 7/4/17.
 */
@RunWith(AppTestRunner::class)
class MainActivityTest: BaseUnitTest() {


    @Test
    fun testing(){
        var mainActivity = Robolectric.buildActivity(MainActivity::class.java) as MainActivity

        Assertions.assertThat(mainActivity.testing()).isEqualTo("testing")

    }


}