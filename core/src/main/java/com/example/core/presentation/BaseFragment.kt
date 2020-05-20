package com.example.core.presentation

import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.core.presentation.events.Exit
import com.example.core.presentation.events.HideKeyboard
import com.example.core.presentation.events.NavEvent
import com.example.core.presentation.events.ParentNavEvent
import com.example.core.presentation.events.ShowSnackbar
import com.example.core.presentation.events.ShowSnackbarResId
import com.example.core.presentation.events.ToastString
import com.example.core.presentation.events.ToastStringRes
import com.google.android.material.snackbar.Snackbar
import com.example.core.util.hideKeyboard

open class BaseFragment : Fragment() {

    @CallSuper
    protected open fun onEvent(event: Event) {
        when (event) {
            is NavEvent -> findNavController().navigate(event.direction)
            is ParentNavEvent -> parentFragment?.parentFragment?.findNavController()?.navigate(event.direction)
            is ToastString -> Toast.makeText(context, event.text, Toast.LENGTH_LONG).show()
            is ToastStringRes -> Toast.makeText(context, event.text, Toast.LENGTH_LONG).show()
            is HideKeyboard -> hideKeyboard()
            is ShowSnackbar -> view?.let {
                Snackbar.make(it, event.text, Snackbar.LENGTH_SHORT).show()
            }
            is ShowSnackbarResId -> view?.let {
                Snackbar.make(it, event.textResId, Snackbar.LENGTH_SHORT).show()
            }
            is Exit -> activity?.finish()
        }
    }
}
