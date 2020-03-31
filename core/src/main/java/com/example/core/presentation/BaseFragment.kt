package com.example.core.presentation

import androidx.annotation.CallSuper
import moxy.MvpAppCompatFragment

open class BaseFragment : MvpAppCompatFragment() {

    @CallSuper
    protected open fun onEvent(event: Event) {
    }
}
