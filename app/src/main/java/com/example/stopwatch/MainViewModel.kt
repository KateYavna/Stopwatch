package com.example.stopwatch

import android.util.Log
import androidx.databinding.ObservableField


class MainViewModel {
    val timer = Timer()
    var text = ObservableField("time")

    fun refreshTime(){
        timer.startTimer(object :TimerCallback{
            override fun onNewSecond(time: String) {
                text.set(time)
            }

        })
    }

}