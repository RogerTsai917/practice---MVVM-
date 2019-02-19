package com.rogertsai.mymvvm.ui

import android.arch.core.util.Function
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.text.TextUtils
import com.rogertsai.mymvvm.api.ApiResponse
import com.rogertsai.mymvvm.data.DataModel
import com.rogertsai.mymvvm.data.model.RepoSearchResponse
import com.rogertsai.mymvvm.utils.AbsentLiveData

class RepoViewModel(private val dataModel: DataModel) : ViewModel() {

    val isLoading = ObservableBoolean(false)

    private val query = MutableLiveData<String>()

    private val repos=
            Transformations.switchMap(query, object : Function<String, LiveData<ApiResponse<RepoSearchResponse>>> {
                override fun apply(userInput: String?): LiveData<ApiResponse<RepoSearchResponse>> {
                    return if (TextUtils.isEmpty(userInput)) {
                        AbsentLiveData.create()
                    } else {
                        dataModel.searchRepo(userInput!!)
                    }
                }
            })

    fun getRepos(): LiveData<ApiResponse<RepoSearchResponse>> {
        return repos
    }

    fun searchRepo(userInput: String) {
        query.value = userInput
    }
}