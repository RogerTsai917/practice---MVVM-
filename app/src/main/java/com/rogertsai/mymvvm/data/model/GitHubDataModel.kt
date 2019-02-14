package com.rogertsai.mymvvm.data.model

import com.rogertsai.mymvvm.api.GithubService
import com.rogertsai.mymvvm.api.RetrofitManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitHubDataModel {

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
        fun onDataReady(data: List<Repo>)
    }
}