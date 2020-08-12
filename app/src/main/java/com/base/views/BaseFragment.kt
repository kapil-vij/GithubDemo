/*
 * Copyright © 2017 Nedbank. All rights reserved.
 */

package com.base.views

import android.content.Context
import android.view.View
import android.view.WindowManager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mIsFragTransactionAllowed = true

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    val resActivity: BaseActivity?
        get() = activity as BaseActivity?

    fun refreshFragment() {
        // Do nothing here
    }

    protected fun replaceFragment(
        fragment: Fragment,
        frameId: Int
    ) {
        val supportFragmentManager = childFragmentManager
        replaceFragment(supportFragmentManager, fragment, frameId)
    }

    protected fun replaceFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        frameId: Int
    ) {
        if (isAdded) {
            val transaction = fragmentManager.beginTransaction()
            if (fragment.isAdded) {
                transaction.show(fragment)
            } else {
                transaction.replace(frameId, fragment)
            }
            transaction.commitAllowingStateLoss()
        }
    }

    fun setEnabledActivityTouch(isEnabled: Boolean) {
        if (activity != null) {
            if (isEnabled) {
                activity!!.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            } else {
                activity!!.window.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                )
            }
        }
    }

    fun showSnackbar(view: View, message: String){
        if(activity != null && activity is BaseActivity){
            (activity as BaseActivity).showSnackbar(view, message)
        }
    }
}
