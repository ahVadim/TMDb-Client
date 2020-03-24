package com.example.core.rxjava

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

open class TestSchedulersProvider : SchedulersProvider() {

    override fun ui(): Scheduler = Schedulers.trampoline()
    override fun io(): Scheduler = Schedulers.trampoline()
    override fun computation(): Scheduler = Schedulers.trampoline()
}
