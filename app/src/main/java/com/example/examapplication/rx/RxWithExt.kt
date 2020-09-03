package com.example.examapplication.rx

import io.reactivex.Maybe


fun <T> Maybe<T>.with(schedulerProvider: SchedulerProvider): Maybe<T> =
        this.observeOn(schedulerProvider.ui()).subscribeOn(schedulerProvider.io())