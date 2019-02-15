package com.rogertsai.mymvvm.data.model

import com.google.gson.annotations.SerializedName

data class RepoSearchResponse(
        @SerializedName("total_count")val total_count: Int,
        @SerializedName("items")val items: MutableList<Repo>
        )