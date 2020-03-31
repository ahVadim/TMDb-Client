package com.example.core.presentation

import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.navigation.fragment.findNavController
import com.example.core.presentation.events.NavEvent
import com.example.core.presentation.events.ToastString
import com.example.core.presentation.events.ToastStringRes
import moxy.MvpAppCompatFragment

open class BaseFragment : MvpAppCompatFragment() {

    @CallSuper
    protected open fun onEvent(event: Event) {
        when (event) {
            is NavEvent -> findNavController().navigate(event.direction)
            is ToastString -> Toast.makeText(context, event.text, Toast.LENGTH_LONG).show()
            is ToastStringRes -> Toast.makeText(context, event.text, Toast.LENGTH_LONG).show()
        }
    }
}
