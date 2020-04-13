package com.example.core.presentation.events

import androidx.annotation.StringRes
import com.example.core.presentation.Event

class ShowSnackbar(val text: String) : Event

class ShowSnackbarResId(@StringRes val textResId: Int) : Event
