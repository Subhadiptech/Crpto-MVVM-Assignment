package com.ersubhadip.crptoapp.data

import android.util.Log
import com.ersubhadip.crptoapp.BuildConfig
import com.ersubhadip.crptoapp.domain.IRemoteDataRepository
import com.ersubhadip.crptoapp.domain.StandardResponse
import com.ersubhadip.crptoapp.domain.model.CryptoListModel
import com.ersubhadip.crptoapp.domain.model.CryptoLiveModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataRepositoryImpl @Inject constructor(
    private val api: ICryptoAPI
) : IRemoteDataRepository {
    override suspend fun fetchLiveCrypto(): Flow<StandardResponse<CryptoLiveModel>> =
        flow {
            val resp = api.fetchLiveCrypto(BuildConfig.API_KEY)
            if (resp.isSuccessful && resp.body() != null) {
                emit(
                    StandardResponse.Success(
                        resp.body()?.toCryptoLiveModel()!!
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

    override suspend fun fetchCryptoList(): Flow<StandardResponse<CryptoListModel>> =
        flow {
            val resp = api.fetchCryptoList(BuildConfig.API_KEY)
            if (resp.isSuccessful && resp.body() != null) {
                Log.d("ActivityLog: Repo", resp.body()?.crypto.toString())
                emit(
                    StandardResponse.Success(
                        resp.body()?.toCryptoListModel()!!
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