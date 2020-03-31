package com.example.test_core.rxjava

import com.example.core.rxjava.SchedulersProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

open class TestSchedulersProvider(
    private val scheduler: Scheduler = Schedulers.trampoline()
) : SchedulersProvider() {

    override fun ui(): Scheduler = scheduler
    override fun io(): Scheduler = scheduler
    override fun computation(): Scheduler = scheduler
}
