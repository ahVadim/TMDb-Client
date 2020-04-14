package com.example.core.presentation

import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.core.presentation.events.NavEvent
import com.example.core.presentation.events.ParentNavEvent
import com.example.core.presentation.events.ToastString
import com.example.core.presentation.events.ToastStringRes

open class BaseFragment : Fragment() {

    @CallSuper
    protected open fun onEvent(event: Event) {
        when (event) {
            is NavEvent -> findNavController().navigate(event.direction)
            is ParentNavEvent -> parentFragment?.parentFragment?.findNavController()?.navigate(event.direction)
            is ToastString -> Toast.makeText(context, event.text, Toast.LENGTH_LONG).show()
            is ToastStringRes -> Toast.makeText(context, event.text, Toast.LENGTH_LONG).show()
        }
    }
}
