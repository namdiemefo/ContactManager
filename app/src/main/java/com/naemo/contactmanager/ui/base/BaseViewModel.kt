package com.naemo.contactmanager.ui.base

import androidx.lifecycle.ViewModel

open class BaseViewModel<N> : ViewModel() {
    private var navigator: N? = null

    fun getNavigator(): N?{
        return navigator
    }

    fun setNavigator(navigator: N) {
        this.navigator = navigator
    }
}