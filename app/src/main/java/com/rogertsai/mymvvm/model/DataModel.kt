package com.rogertsai.mymvvm.model

import android.os.Handler

class DataModel {
    private var count = 0

    // TODO : 了解 Kotlin 的 callback 方式
    fun retrieveData(callback: DataModelCallBack) {
        Handler().postDelayed({
            if (count%2 == 0) {
                callback.onSuccess("onSuccess New Data $count")
            } else {
                callback.onFail("onFail New Data $count")
            }
            count++
        }, 1500)
    }

    public interface DataModelCallBack {
        fun onSuccess(str: String)
        fun onFail(str: String)
    }
}
