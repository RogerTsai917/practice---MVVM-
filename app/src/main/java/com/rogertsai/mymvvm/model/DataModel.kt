package com.rogertsai.mymvvm.model

import android.os.Handler

class DataModel {
    private var count = 0

    fun retrieveData(dataModelInterface: DataModelInterface) {
        Handler().postDelayed({
            if (count % 2 == 0) {
                dataModelInterface.onSuccess("onSuccess New Data $count")
            } else {
                dataModelInterface.onFail("onFail New Data $count")
            }
            count++
        }, 1000)
    }

    interface DataModelInterface {
        fun onSuccess(str: String)
        fun onFail(str: String)
    }
}
