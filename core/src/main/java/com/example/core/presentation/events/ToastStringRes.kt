package com.example.core.presentation.events

import androidx.annotation.StringRes
import com.example.core.presentation.Event

class ToastStringRes(@StringRes val text: Int) : Event
