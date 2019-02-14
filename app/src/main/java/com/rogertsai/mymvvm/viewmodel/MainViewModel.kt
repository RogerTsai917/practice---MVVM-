package com.rogertsai.mymvvm.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean

import com.rogertsai.mymvvm.data.DataModel
import com.rogertsai.mymvvm.utils.SingleLiveEvent

class MainViewModel: ViewModel() { // 讓自訂 viewModel 繼承 Lifecycle-aware 的 viewModel

    var mData = MutableLiveData<String>()

    // singleLiveEvent只會發送更新的value，原value若已經發送過就不會再次發送，
    // 即避免了configuration change後又顯示一次同樣內容的問題。因此對於提示訊息、畫面跳轉等動作就很適合用SingleLiveEvent來處理，
    // 使用方式跟MutableLiveData一樣。
    var mToastText = SingleLiveEvent<String>()

    var isLoading = ObservableBoolean(false)

    private val dataModel = DataModel()

    fun refresh() {
        isLoading.set(true)

        dataModel.retrieveData(object : DataModel.DataModelInterface {
            override fun onSuccess(str: String) {
                updateData(str)
            }

            override fun onFail(str: String) {
                updateData(str)
            }

        })
    }

    fun updateData(string: String) {
        // MutableLiveData是方便我們使用的LiveData子類別，提供setValue()和postValue()兩種方式更新value，
        // 差異在於前者是在main thread執行，若需要在background thread則改用後者。
        mData.value = string
        mToastText.value = "download finished"
        isLoading.set(false)
    }


}
