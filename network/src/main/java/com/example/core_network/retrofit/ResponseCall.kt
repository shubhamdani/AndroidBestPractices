package com.example.network.retrofit

import com.example.common.dto.ErrorCode
import com.example.common.dto.NetworkError
import com.example.common.Results
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResponseCall<T : Any>(private val delegate: Call<T>) : Call<Results<T>> {

    override fun enqueue(callback: Callback<Results<T>>) {
        return delegate.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()

                when {
                    response.isSuccessful && body != null -> callback.onResponse(
                        this@ResponseCall,
                        Response.success(Results.Success(body))
                    )
                    response.isSuccessful && body == null -> callback.onResponse(
                        this@ResponseCall,
                        Response.success(Results.Error(NetworkError(ErrorCode.UNKNOWN_ERROR)))
                    )
                    else -> {
                        callback.onResponse(
                            this@ResponseCall,
                            Response.success(Results.Error(NetworkError(ErrorCode.UNKNOWN_ERROR)))
                        )
                    }
                }
            }

            override fun onFailure(call: Call<T>, throwable: Throwable) {
                callback.onResponse(
                    this@ResponseCall,
                    Response.success(Results.Error(NetworkError(ErrorCode.UNKNOWN_ERROR)))
                )
            }
        })
    }

    override fun isExecuted() = delegate.isExecuted

    override fun clone() = ResponseCall(delegate.clone())

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<Results<T>> {
        throw UnsupportedOperationException("Result call doesn't support execute")
    }

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}
