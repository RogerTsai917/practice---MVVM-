package com.rogertsai.mymvvm.api

import com.rogertsai.mymvvm.utils.LiveDataCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


class RetrofitManager {

    private val githubService: GithubService

    init {

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()

        githubService = retrofit.create(GithubService::class.java)
    }

    companion object {
        private val mInstance = RetrofitManager()

        fun getAPI(): GithubService {
            return mInstance.githubService
        }
    }
}