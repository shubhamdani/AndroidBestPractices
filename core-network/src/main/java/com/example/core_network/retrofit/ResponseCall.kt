package com.example.core_network.retrofit

import com.example.core_common.dto.dto.ErrorCode
import com.example.core_common.dto.dto.NetworkError
import com.example.core_common.dto.Result
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResponseCall<T : Any>(private val delegate: Call<T>) : Call<Result<T>> {

    override fun enqueue(callback: Callback<Result<T>>) {
        return delegate.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()

                when {
                    response.isSuccessful && body != null -> callback.onResponse(
                        this@ResponseCall,
                        Response.success(Result.Success(body))
                    )
                    response.isSuccessful && body == null -> callback.onResponse(
                        this@ResponseCall,
                        Response.success(Result.Error(NetworkError(ErrorCode.UNKNOWN_ERROR)))
                    )
                    else -> {
                        callback.onResponse(
                            this@ResponseCall,
                            Response.success(Result.Error(NetworkError(ErrorCode.UNKNOWN_ERROR)))
                        )
                    }
                }
            }

            override fun onFailure(call: Call<T>, throwable: Throwable) {
                callback.onResponse(
                    this@ResponseCall,
                    Response.success(Result.Error(NetworkError(ErrorCode.UNKNOWN_ERROR)))
                )
            }
        })
    }

    override fun isExecuted() = delegate.isExecuted

    override fun clone() = ResponseCall(delegate.clone())

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<Result<T>> {
        throw UnsupportedOperationException("Result call doesn't support execute")
    }

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}
