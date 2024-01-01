package com.ersubhadip.crptoapp.domain

import com.ersubhadip.crptoapp.BuildConfig
import com.ersubhadip.crptoapp.data.CryptoResponse
import com.ersubhadip.crptoapp.data.ICryptoAPI
import com.ersubhadip.crptoapp.data.IRemoteDataRepository
import com.ersubhadip.crptoapp.data.LiveDataResponse
import com.ersubhadip.crptoapp.data.StandardResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataRepositoryImpl @Inject constructor(
    private val api: ICryptoAPI
) : IRemoteDataRepository {
    override suspend fun fetchLiveCrypto(accessToken: String): Flow<StandardResponse<LiveDataResponse>> =
        flow {
            val resp = api.fetchLiveCrypto(BuildConfig.API_KEY)
            if (resp.isSuccessful && resp.body() != null) {
                emit(
                    StandardResponse.Success(
                        resp.body()!!
                    )
                )
            } else {
                emit(
                    StandardResponse.Failed(
                        "Something went wrong!"
                    )
                )
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun fetchCryptoList(accessToken: String): Flow<StandardResponse<CryptoResponse>> =
        flow {
            val resp = api.fetchCryptoList(BuildConfig.API_KEY)
            if (resp.isSuccessful && resp.body() != null) {
                emit(
                    StandardResponse.Success(
                        resp.body()!!
                    )
                )
            } else {
                emit(
                    StandardResponse.Failed(
                        "Something went wrong!"
                    )
                )
            }
        }.flowOn(Dispatchers.IO)
}