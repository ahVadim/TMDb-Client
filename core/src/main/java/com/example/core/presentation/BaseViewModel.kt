package com.example.core.presentation

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.example.core.presentation.events.NavEvent
import com.example.core.presentation.events.ParentNavEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    val eventsQueue = EventsQueue()

    private val compositeDisposable = CompositeDisposable()

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    protected fun navigateTo(destination: NavDirections) {
        eventsQueue.offer(NavEvent(destination))
    }

    protected fun parentNavigateTo(destination: NavDirections) {
        eventsQueue.offer(ParentNavEvent(destination))
    }

    override fun onCleared() {
        compositeDisposable.dispose()
    }
}
