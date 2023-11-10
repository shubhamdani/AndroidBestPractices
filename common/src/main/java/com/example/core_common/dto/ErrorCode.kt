package com.example.common.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class ErrorCode : Parcelable {
    object UNKNOWN_ERROR : ErrorCode()
}
