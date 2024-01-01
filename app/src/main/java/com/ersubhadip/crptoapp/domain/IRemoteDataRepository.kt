package com.ersubhadip.crptoapp.domain

import com.ersubhadip.crptoapp.domain.model.CryptoListModel
import com.ersubhadip.crptoapp.domain.model.CryptoLiveModel
import kotlinx.coroutines.flow.Flow

interface IRemoteDataRepository {
    suspend fun fetchLiveCrypto(): Flow<StandardResponse<CryptoLiveModel>>
    suspend fun fetchCryptoList(): Flow<StandardResponse<CryptoListModel>>
}