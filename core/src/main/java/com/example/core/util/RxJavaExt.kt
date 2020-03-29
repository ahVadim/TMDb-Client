package com.example.core.util

import com.example.core.rxjava.SchedulersProvider
import io.reactivex.Completable

fun Completable.ioToMain(provider: SchedulersProvider): Completable {
    return subscribeOn(provider.io()).observeOn(provider.ui())
}
