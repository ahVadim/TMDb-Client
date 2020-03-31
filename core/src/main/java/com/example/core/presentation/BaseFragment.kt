package com.example.core.presentation

import androidx.annotation.CallSuper
import androidx.navigation.fragment.findNavController
import com.example.core.navigation.NavEvent
import moxy.MvpAppCompatFragment

open class BaseFragment : MvpAppCompatFragment() {

    @CallSuper
    protected open fun onEvent(event: Event) {
        if (event is NavEvent) {
            findNavController().navigate(event.direction)
        }
    }
}
