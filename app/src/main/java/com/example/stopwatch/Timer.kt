package com.example.stopwatch

import android.util.Log
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import java.util.concurrent.TimeUnit

class Timer {
    lateinit var timer  : DisposableSubscriber<Long?>

    fun startTimer(callback: TimerCallback){

        timer = object : DisposableSubscriber<Long?>() {
            override fun onNext(t: Long?) {
                Log.d("MyLog", t.toString())
                callback.onNewSecond(t.toString())

            }
            override fun onError(t: Throwable) {}
            override fun onComplete() {}
        }
        Flowable.interval(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(timer)
    }
    fun stopTimer() {
       // timer.dispose()
    }

    }

interface TimerCallback{
    fun onNewSecond(time:String)
}