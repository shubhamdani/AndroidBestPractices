package com.example.common.dto

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.parcelize.Parcelize

@JsonIgnoreProperties(ignoreUnknown = true)
@Parcelize
data class NetworkError(
    @JsonProperty("code") val code: ErrorCode,
    @JsonProperty("message") val message: String = ""
) : Parcelable
