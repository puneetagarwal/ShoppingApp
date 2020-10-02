package com.mobiquity.testapp.core.rx

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestSchedulerProvider : SchedulerProvider {

    override val mainThread: Scheduler
        get() = Schedulers.trampoline()

    override val io: Scheduler
        get() = Schedulers.trampoline()

    override val newThread: Scheduler
        get() = Schedulers.trampoline()

    override val computation: Scheduler
        get() = Schedulers.trampoline()
}