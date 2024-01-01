package com.ersubhadip.crptoapp.data

interface IRemoteDataRepository {
    suspend fun fetchLiveCrypto(accessToken: String): StandardResponse<LiveDataResponse>
    suspend fun fetchCryptoList(accessToken: String): StandardResponse<CryptoResponse>
}