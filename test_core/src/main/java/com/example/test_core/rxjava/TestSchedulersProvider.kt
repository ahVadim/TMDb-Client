package com.example.test_core.rxjava

import com.example.core.rxjava.SchedulersProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

open class TestSchedulersProvider(
    scheduler: Scheduler = Schedulers.trampoline()
) : SchedulersProvider() {

    override fun ui(): Scheduler = Schedulers.trampoline()
    override fun io(): Scheduler = Schedulers.trampoline()
    override fun computation(): Scheduler = Schedulers.trampoline()
}
