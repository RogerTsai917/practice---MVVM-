package com.rogertsai.mymvvm.data.model

import com.google.gson.annotations.SerializedName

data class Repo(
        @SerializedName("name")val name: String,
        @SerializedName("full_name")val full_name: String,
        @SerializedName("description")val description: String,
        @SerializedName("stargazers_count")val stargazers_count: Int,
        @SerializedName("owner")val owner: Owner
        )