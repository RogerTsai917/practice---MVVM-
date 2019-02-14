package com.rogertsai.mymvvm.api

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


class RetrofitManager {

    private val githubService: GithubService

    fun getAPI(): GithubService {
        return mInstance.githubService
    }

    init {

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        githubService = retrofit.create(GithubService::class.java)
    }

    companion object {
        private val mInstance = RetrofitManager()
    }
}