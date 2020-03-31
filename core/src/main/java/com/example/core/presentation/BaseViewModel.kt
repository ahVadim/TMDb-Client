package com.example.core.presentation

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    protected fun addDisposable(disposable: Disposable) = compositeDisposable::add

    override fun onCleared() {
        compositeDisposable.dispose()
    }
}
