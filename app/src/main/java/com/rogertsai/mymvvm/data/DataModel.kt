package com.rogertsai.mymvvm.data

import com.rogertsai.mymvvm.api.GithubService
import com.rogertsai.mymvvm.api.RetrofitManager
import com.rogertsai.mymvvm.data.model.Repo
import com.rogertsai.mymvvm.data.model.RepoSearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataModel {

    private val githubService: GithubService = RetrofitManager().getAPI()

    fun searchRepo(query: String, callback: OnDataReadyCallback) {
        githubService.searchRepos(query)
                .enqueue(object : Callback<RepoSearchResponse> {
                    override fun onResponse(call: Call<RepoSearchResponse>, response: Response<RepoSearchResponse>) {
                        callback.onDataReady(response.body()!!.items)
                    }

                    override fun onFailure(call: Call<RepoSearchResponse>, t: Throwable) {

                    }
                })
    }


    interface OnDataReadyCallback {
        fun onDataReady(data: MutableList<Repo>)
    }
}