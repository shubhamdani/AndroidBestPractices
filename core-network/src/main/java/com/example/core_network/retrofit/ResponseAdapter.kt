package com.example.core_network.retrofit

import com.example.core_common.Result
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ResponseAdapter<S : Any>(
    private val successType: Type,
) : CallAdapter<S, Call<Result<S>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>): Call<Result<S>> {
        return ResponseCall(call)
    }
}
