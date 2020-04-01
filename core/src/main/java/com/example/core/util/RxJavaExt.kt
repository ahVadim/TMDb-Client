package com.example.core.util

import com.example.core.rxjava.SchedulersProvider
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

fun Completable.ioToMain(provider: SchedulersProvider): Completable {
    return subscribeOn(provider.io()).observeOn(provider.ui())
}

fun <T> Single<T>.ioToMain(provider: SchedulersProvider): Single<T> {
    return subscribeOn(provider.io()).observeOn(provider.ui())
}

fun <T> Observable<T>.ioToMain(provider: SchedulersProvider): Observable<T> {
    return subscribeOn(provider.io()).observeOn(provider.ui())
}
