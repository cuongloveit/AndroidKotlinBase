package com.androidtmc.android_kotlin_base.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View


/**
 * Created by Harry on 12/24/16.
 */

interface OnFragmentAction {
    fun replaceFragment(id: Int, baseFragment: BaseFragment<*>)

    fun replaceFragment(id: Int, baseFragment: BaseFragment<*>, bundle: Bundle)

    fun replaceFragment(baseFragment: BaseFragment<*>)
    fun replaceFragment(baseFragment: BaseFragment<*>, isBackStack: Boolean, InAnim: Int, OutAnim: Int)

    fun replaceFragment(baseFragment: BaseFragment<*>, bundle: Bundle)

    fun replaceFragment(id: Int, fragment: Fragment)
    fun replaceFragment(id: Int, fragment: Fragment, isBackStack: Boolean)

    fun replaceFragmentWithSharedElement(baseFragment: BaseFragment<*>, views: List<View>)

    fun addFragment(baseFragment: BaseFragment<*>)

    fun addFragment(baseFragment: BaseFragment<*>, isBackStack: Boolean)

    fun addFragmentWithSharedElement(baseFragment: BaseFragment<*>, views: List<View>)

    fun popBackStack()

    fun removeFragment(fragment: Fragment)

    val sizeFragmentManager: Int

    fun clearAllFragments()

    val lastFragment: BaseFragment<*>
}
