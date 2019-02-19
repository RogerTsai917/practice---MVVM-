package com.rogertsai.mymvvm.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.rogertsai.mymvvm.api.ApiResponse
import com.rogertsai.mymvvm.api.GithubService
import com.rogertsai.mymvvm.api.RetrofitManager
import com.rogertsai.mymvvm.data.model.RepoSearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataModel {

    private val githubService: GithubService = RetrofitManager.getAPI()

    fun searchRepo(query: String) : LiveData<ApiResponse<RepoSearchResponse>> {
        return githubService.searchRepos(query)
    }
}