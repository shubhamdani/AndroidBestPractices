package com.example.core_network.retrofit

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import javax.inject.Inject
import com.example.core_common.dto.Result

class CallAdapterFactory @Inject constructor() : CallAdapter.Factory() {

    override fun get(
        returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (Call::class.java != getRawType(returnType)) return null

        check(returnType is ParameterizedType) { "return type must be parameterized as Call<Result<Any>>" }

        val containerType = getParameterUpperBound(0, returnType)
        if (getRawType(containerType) != Result::class.java) {
            return null
        }

        check(containerType is ParameterizedType) { "Response must be parameterized as Result<Any> or Result<out Any>" }

        val successBodyType = getParameterUpperBound(0, containerType)

        return ResponseAdapter<Any>(successBodyType)
    }
}
