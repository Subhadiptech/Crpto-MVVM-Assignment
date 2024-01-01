package com.ersubhadip.crptoapp.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ICryptoAPI {
    @GET("live")
    suspend fun fetchLiveCrypto(@Query("access_key") accessToken: String): Response<LiveDataResponse>

    @GET("list")
    suspend fun fetchCryptoList(@Query("access_key") accessToken: String): Response<CryptoResponse>
}