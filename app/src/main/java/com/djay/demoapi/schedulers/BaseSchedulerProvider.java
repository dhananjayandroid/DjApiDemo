package com.djay.demoapi.schedulers;

import android.support.annotation.NonNull;

import rx.Scheduler;


/**
 * Interface providing different types of web-api operations with RxJava
 *
 * @author Dhananjay Kumar
 */
public interface BaseSchedulerProvider {

    @NonNull
    Scheduler computation();

    @SuppressWarnings("unused")
    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();
}
