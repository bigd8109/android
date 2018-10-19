package com.example.hieplp.livedatademo

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    var name = MutableLiveData<String>()

    fun doSomething(s: String){
        Thread().run { Thread.sleep(3000)
           }
        name.value = "Co len : $s"
    }
}