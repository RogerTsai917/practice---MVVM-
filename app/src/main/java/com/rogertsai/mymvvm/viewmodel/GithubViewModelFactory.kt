package com.rogertsai.mymvvm.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.rogertsai.mymvvm.data.DataModel
import com.rogertsai.mymvvm.ui.RepoViewModel

class GithubViewModelFactory : ViewModelProvider.Factory {

    private var dataModel: DataModel = DataModel()

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RepoViewModel::class.java)) {
            return  RepoViewModel(dataModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}