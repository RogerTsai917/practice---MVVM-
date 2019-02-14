package com.rogertsai.mymvvm.viewmodel

import android.databinding.ObservableBoolean
import android.databinding.ObservableField

import com.rogertsai.mymvvm.model.DataModel

class MainViewModel {

    var mData = ObservableField<String>()

    var isLoading = ObservableBoolean(false)

    private val dataModel = DataModel()

    fun refresh() {
        isLoading.set(true)

        dataModel.retrieveData(object : DataModel.DataModelInterface {
            override fun onSuccess(str: String) {

            }

            override fun onFail(str: String) {
            }

        })

    }
}
