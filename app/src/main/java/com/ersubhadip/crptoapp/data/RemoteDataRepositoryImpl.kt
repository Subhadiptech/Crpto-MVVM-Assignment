package com.ersubhadip.crptoapp.data

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
) : IRemoteDataRepository, BaseRepository() {
    override suspend fun fetchLiveCrypto(): Flow<StandardResponse<CryptoLiveModel>> =
        flow {
            emit(StandardResponse.Loading)
            safeWrap(
                block = {
                    emit(
                        retryIO {
                            val resp = api.fetchLiveCrypto(BuildConfig.API_KEY)
                            if (resp.isSuccessful && resp.body() != null) {
                                StandardResponse.Success(resp.body()?.toCryptoLiveModel()!!)
                            } else {
                                StandardResponse.Failed("Something went wrong!")
                            }
                        }
                    )
                },
                errorHandler = {
                    emit(StandardResponse.Failed("Something went wrong!"))
                }
            )

        }.flowOn(Dispatchers.IO)

    override suspend fun fetchCryptoList(): Flow<StandardResponse<CryptoListModel>> =
        flow {
            emit(StandardResponse.Loading)
            safeWrap(
                block = {
                    emit(
                        retryIO {
                            val resp = api.fetchCryptoList(BuildConfig.API_KEY)
                            if (resp.isSuccessful && resp.body() != null) {
                                StandardResponse.Success(resp.body()?.toCryptoListModel()!!)
                            } else {
                                StandardResponse.Failed("Something went wrong!")
                            }
                        }
                    )
                },
                errorHandler = {
                    emit(StandardResponse.Failed("Something went wrong!"))
                }
            )

        }.flowOn(Dispatchers.IO)
}