package com.ersubhadip.crptoapp.data

import kotlinx.coroutines.flow.Flow

interface IRemoteDataRepository {
    suspend fun fetchLiveCrypto(accessToken: String): Flow<StandardResponse<LiveDataResponse>>
    suspend fun fetchCryptoList(accessToken: String): Flow<StandardResponse<CryptoResponse>>
}