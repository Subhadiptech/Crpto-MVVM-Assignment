package com.ersubhadip.crptoapp.domain

sealed class StandardResponse<out T> {
    class Success<T>(val data: T) : StandardResponse<T>()
    class Failed(val error: String) : StandardResponse<Nothing>()
    data object Loading : StandardResponse<Nothing>()
}