package com.androidtmc.android_kotlin_base.utils

import android.support.v4.app.Fragment
import com.androidtmc.android_kotlin_base.base.IView
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.components.support.RxFragment
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RxUtils {
    fun bindToLifeCycle(view: IView): LifecycleProvider<*> {
        if (view is RxAppCompatActivity) {
            return view
        } else if (view is RxFragment) {
            return view as LifecycleProvider<Fragment>
        } else {
            throw IllegalArgumentException("Unable find fragment or activity properly")
        }
    }

    fun <T> applySchedules(view: IView): FlowableTransformer<T, T> {
        return FlowableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                    .doOnSubscribe { view.showLoading() }
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnTerminate { view.hideLoading() }
        }
    }

}
