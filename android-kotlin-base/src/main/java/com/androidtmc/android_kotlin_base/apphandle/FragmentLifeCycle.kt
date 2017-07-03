package com.androidtmc.android_kotlin_base.apphandle

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import com.androidtmc.android_kotlin_base.base.IFragment
import com.androidtmc.android_kotlin_base.delegation.FragmentDelegate


class FragmentLifeCycle : FragmentManager.FragmentLifecycleCallbacks() {
    override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        super.onFragmentCreated(fm, f, savedInstanceState)
        val delegate = getFragmentDelegate(f)
        delegate?.onCreate(savedInstanceState)
        Log.d("TAG", "onFragmentCreated life cycle")
    }

    override fun onFragmentActivityCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
        super.onFragmentActivityCreated(fm, f, savedInstanceState)
        val delegate = getFragmentDelegate(f)
        delegate?.onActivityCreated(savedInstanceState)
    }

    override fun onFragmentAttached(fm: FragmentManager?, f: Fragment?, context: Context?) {
        super.onFragmentAttached(fm, f, context)
        if (f is IFragment && f.arguments != null) {
            var delegate = getFragmentDelegate(f)
            if (delegate == null) {
                delegate = FragmentDelegate(f)
                f.arguments.putSerializable(FragmentDelegate.FRAGMENT_LIFECYCLE, delegate)
            }
            delegate.onAttach(context)
        }
    }

    override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
        super.onFragmentResumed(fm, f)
        val delegate = getFragmentDelegate(f)
        delegate?.onResume()
    }

    override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
        super.onFragmentStarted(fm, f)
        val delegate = getFragmentDelegate(f)
        delegate?.onStart()
    }

    override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
        super.onFragmentPaused(fm, f)
        val delegate = getFragmentDelegate(f)
        delegate?.onPause()
    }

    override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
        super.onFragmentDetached(fm, f)
        val delegate = getFragmentDelegate(f)
        delegate?.onDetach()
    }

    override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
        super.onFragmentDestroyed(fm, f)
        val delegate = getFragmentDelegate(f)
        delegate?.onDestroy()
    }

    override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
        super.onFragmentViewDestroyed(fm, f)
        val delegate = getFragmentDelegate(f)
        delegate?.onDestroyView()
    }

    private fun getFragmentDelegate(fragment: Fragment): FragmentDelegate? {
        if (fragment is IFragment) {
            return if (fragment.arguments == null) null else fragment.arguments.getSerializable(FragmentDelegate.FRAGMENT_LIFECYCLE) as FragmentDelegate
        }
        return null
    }
}
