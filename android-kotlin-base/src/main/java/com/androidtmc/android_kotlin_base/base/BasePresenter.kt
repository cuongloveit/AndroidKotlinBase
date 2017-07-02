package com.androidtmc.android_kotlin_base.base


import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


open class BasePresenter<M : IModel, V : IView> : IPresenter {
    val TAG = this.javaClass.simpleName
    var mCompositeDisposable: CompositeDisposable? = null

    lateinit var mModel: M
    lateinit var mView: V


    constructor(model: M, view: V) {
        this.mModel = model
        this.mView = view
        onStart()
    }

    constructor(view: V) {
        this.mView = view
        onStart()
    }

    constructor() {
        onStart()
    }

    override fun onStart() {

    }

    override fun onDestroy() {

        unDispose()
        mModel.onDestroy()
//        this.mModel = null
//        this.mView = null
        this.mCompositeDisposable = null
    }

    fun useEventBus(): Boolean {
        return true
    }

    fun addDispose(disposable: Disposable) {
        if (mCompositeDisposable != null) {
            mCompositeDisposable!!.add(disposable)
        }
    }

    fun unDispose() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable!!.clear()
        }
    }
}
