package com.ersubhadip.crptoapp.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ICryptoAPI {
    @GET("/live/{access_token}")
    suspend fun fetchLiveCrypto(@Query("access_token") accessToken: String): Response<LiveDataResponse>

    @GET("/list/{access_token}")
    suspend fun fetchCryptoList(@Query("access_token") accessToken: String): Response<CryptoResponse>
}