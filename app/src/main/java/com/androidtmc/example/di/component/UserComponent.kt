package vn.eazy.base.mvp.example.mvp.di.component

import com.androidtmc.android_kotlin_base.di.component.AppComponent
import com.androidtmc.android_kotlin_base.di.scope.ActivityScope
import com.androidtmc.example.view.activity.MainActivity
import com.androidtmc.example.view.fragment.MainFragment
import dagger.Component
import vn.eazy.base.mvp.example.mvp.di.module.UserModule


@ActivityScope
@Component(modules = arrayOf(UserModule::class), dependencies = arrayOf(AppComponent::class))
interface UserComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainActivity: MainFragment)
}
