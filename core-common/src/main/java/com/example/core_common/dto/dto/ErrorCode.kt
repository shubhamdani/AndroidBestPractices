package com.example.core_common.dto.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class ErrorCode : Parcelable {
    object UNKNOWN_ERROR : ErrorCode()
}
