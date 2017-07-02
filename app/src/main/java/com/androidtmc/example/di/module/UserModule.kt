package vn.eazy.base.mvp.example.mvp.di.module

import com.androidtmc.android_kotlin_base.di.scope.ActivityScope
import com.androidtmc.example.contract.UserContract
import com.androidtmc.example.model.AppDataModel
import dagger.Module
import dagger.Provides


@Module
class UserModule(private val mView: UserContract.View) {

    @ActivityScope
    @Provides
    internal fun provideAppDataView(): UserContract.View {
        return this.mView
    }

    @ActivityScope
    @Provides
    internal fun provideUserModel(model: AppDataModel): UserContract.Model {
        return model
    }
}
