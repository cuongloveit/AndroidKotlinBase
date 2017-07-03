package com.androidtmc.android_kotlin_base.delegation


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import com.androidtmc.android_kotlin_base.base.IFragment
import java.io.Serializable


class FragmentDelegate() : Fragment(), Serializable {
    private var iFragment: IFragment? = null
    lateinit var mFragment: Fragment


    @SuppressLint("ValidFragment")
    constructor(fragment: Fragment) : this() {
        mFragment = fragment
        this.iFragment = mFragment as IFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    companion object {
        val FRAGMENT_LIFECYCLE = "fragment_lifecycle"
    }
}
