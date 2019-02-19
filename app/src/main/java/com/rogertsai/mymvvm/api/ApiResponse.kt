package com.rogertsai.mymvvm.api

import android.support.annotation.Nullable
import retrofit2.Response
import timber.log.Timber
import java.io.IOException


class ApiResponse<T> {

    val code: Int

    @Nullable
    val body: T?

    @Nullable
    val errorMessage: String?

    constructor(error: Throwable) {
        code = 500
        body = null
        errorMessage = error.message
    }

    constructor(response: Response<T>) {
        code = response.code()
        if (response.isSuccessful) {
            body = response.body()
            errorMessage = null
        } else {
            var message: String? = null
            if (response.errorBody() != null) {
                try {
                    message = response.errorBody()!!.string()
                } catch (ignored: IOException) {
                    Timber.e(ignored, "error while parsing response")
                }

            }
            if (message == null || message.trim { it <= ' ' }.isEmpty()) {
                message = response.message()
            }
            errorMessage = message
            body = null
        }
    }

    fun isSuccessful() : Boolean {
        return code in 200..299
    }
}