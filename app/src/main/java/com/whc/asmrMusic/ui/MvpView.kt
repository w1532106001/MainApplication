package com.whc.asmrMusic.ui

import android.view.View

interface MvpView {

    fun showLoading()

    fun showLoading(message: String?)

    fun showContent()

    fun showError()

    fun showError(message: String?)

    fun showMessage(message: String)

    fun showProgressDialog(message: String)

    fun hideProgressDialog()
}