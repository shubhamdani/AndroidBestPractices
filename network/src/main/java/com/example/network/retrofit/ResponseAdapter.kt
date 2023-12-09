package com.example.network.retrofit

import com.example.common.Results
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ResponseAdapter<S : Any>(
    private val successType: Type,
) : CallAdapter<S, Call<Results<S>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>): Call<Results<S>> {
        return ResponseCall(call)
    }
}
