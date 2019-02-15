package com.rogertsai.mymvvm.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import com.rogertsai.mymvvm.data.DataModel
import com.rogertsai.mymvvm.data.model.Repo

class RepoViewModel constructor(private var dataModel: DataModel) : ViewModel() {

    var isLoading = ObservableBoolean(false)
    var repos = MutableLiveData<MutableList<Repo>>()

    fun getRepos(): LiveData<MutableList<Repo>> {
        return repos
    }

    fun searchRepo(query: String) {
        isLoading.set(true)

        dataModel.searchRepo(query, object : DataModel.OnDataReadyCallback {
            override fun onDataReady(data: MutableList<Repo>) {
                repos.value = data

                isLoading.set(false)
            }
        })
    }


}