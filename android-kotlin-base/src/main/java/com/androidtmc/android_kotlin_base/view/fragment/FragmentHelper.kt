package com.androidtmc.android_kotlin_base.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View
import com.androidtmc.android_kotlin_base.R


/**
 * Created by Harry on 12/24/16.
 */

class FragmentHelper(private val fragmentManager: FragmentManager, var idContent: Int) : OnFragmentAction {
    override val sizeFragmentManager: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.


    override fun replaceFragment(id: Int, baseFragment: BaseFragment<*>) {
        fragmentManager.beginTransaction().replace(id, baseFragment)
                .addToBackStack(baseFragment::class.java.name).commitAllowingStateLoss()
    }

    override fun replaceFragment(id: Int, baseFragment: BaseFragment<*>, bundle: Bundle) {
        baseFragment.arguments = bundle
        fragmentManager.beginTransaction().replace(id, baseFragment)
                .addToBackStack(baseFragment::class.java.name).commitAllowingStateLoss()
    }

    override fun replaceFragment(baseFragment: BaseFragment<*>) {
    }

    override fun replaceFragment(baseFragment: BaseFragment<*>, isBackStack: Boolean, inAnim: Int, outAnim: Int) {
        val fragmentTransaction = fragmentManager.beginTransaction()
                .setCustomAnimations(inAnim, outAnim)
                .replace(idContent, baseFragment)

        if (isBackStack) {
            fragmentTransaction.addToBackStack(baseFragment::class.java.name)
        }
        fragmentTransaction.commitAllowingStateLoss()
    }

    override fun replaceFragment(baseFragment: BaseFragment<*>, bundle: Bundle) {
    }

    override fun replaceFragment(id: Int, fragment: Fragment) {

        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(idContent, fragment)
                .addToBackStack(fragment::class.java.name).commitAllowingStateLoss()
    }

    override fun replaceFragment(id: Int, fragment: Fragment, isBackStack: Boolean) {
    }

    override fun replaceFragmentWithSharedElement(baseFragment: BaseFragment<*>, views: List<View>) {
    }

    override fun addFragment(baseFragment: BaseFragment<*>) {
    }

    override fun addFragment(baseFragment: BaseFragment<*>, isBackStack: Boolean) {
    }

    override fun addFragmentWithSharedElement(baseFragment: BaseFragment<*>, views: List<View>) {
    }

    override fun popBackStack() {
    }

    override fun removeFragment(fragment: Fragment) {
    }


    override val lastFragment: BaseFragment<*>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.



    override fun clearAllFragments() {
        for (i in 0..fragmentManager.getBackStackEntryCount() - 1) {
            fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }
}
