@file:Suppress("DEPRECATION")

package com.whc.asmrMusic.ui.base

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.transition.ChangeBounds
import com.whc.asmrMusic.databinding.FragmentBaseBinding
import com.whc.asmrMusic.ui.MvpView


abstract class BaseFragment : Fragment(), MvpView {

    private lateinit var baseBinding: FragmentBaseBinding


    open fun getLoadView(): LinearLayout = baseBinding.layoutProgress.root

    open fun getLoadTextView(): TextView = baseBinding.layoutProgress.textLoading

    open fun getErrorView(): LinearLayout = baseBinding.layoutError.root

    open fun getErrorTextView(): TextView = baseBinding.layoutError.textError

    open fun getErrorImageView(): View = baseBinding.layoutError.imageError

    open fun getContentView(): View = baseBinding.layoutContent

    var progressDialog: ProgressDialog? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedElementEnterTransition = ChangeBounds().apply {
            duration = 500
        }
        sharedElementReturnTransition = ChangeBounds().apply {
            duration = 500
        }
        baseBinding = FragmentBaseBinding.inflate(layoutInflater, container, false)
        baseBinding.layoutContent.addView(createContentView(layoutInflater, container))
        return baseBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        AndroidSupportInjection.inject(this)


//        appbar_base.visibility = View.GONE

    }

    override fun showLoading() {
        showLoading(null)
    }


    override fun showLoading(message: String?) {
        getLoadView().visibility = View.VISIBLE
        getLoadTextView().visibility = if (message != null) View.VISIBLE else View.GONE
        message?.let { getLoadTextView().text = it }
        getLoadView().bringToFront()
    }

    override fun showContent() {
        getContentView().visibility = View.VISIBLE
        getContentView().bringToFront()
    }

    override fun showError() {
        showError(null)
    }

    @SuppressLint("SetTextI18n")
    override fun showError(message: String?) {
        getErrorView().visibility = View.VISIBLE
        getErrorImageView().visibility = View.VISIBLE
        message?.let { getErrorTextView().text = "$it\n重试" }
        getErrorView().bringToFront()
    }

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showProgressDialog(message: String) {
        context?.let {
            if (progressDialog?.isShowing == true)
                progressDialog?.dismiss()
            progressDialog = ProgressDialog.show(it, null, message, true, false)
        }
    }

    override fun hideProgressDialog() {
        progressDialog?.dismiss()
    }

    abstract fun createContentView(inflater: LayoutInflater, container: ViewGroup?): View
}