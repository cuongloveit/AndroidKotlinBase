package com.androidtmc.android_kotlin_base.base


open class BaseModel(iapiManager: IAPIManager) : IModel {

    protected var mRepositoryManager: IAPIManager? = null

    init {
        this.mRepositoryManager = iapiManager
    }

    override fun onDestroy() {
        mRepositoryManager = null
    }
}
