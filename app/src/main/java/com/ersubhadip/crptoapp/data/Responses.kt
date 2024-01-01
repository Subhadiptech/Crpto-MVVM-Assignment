package com.ersubhadip.crptoapp.data

import androidx.annotation.Keep

@Keep
data class LiveDataResponse(
    val success: Boolean,
    val terms: String,
    val privacy: String,
    val rates: Map<String, Double>,
    val timestamp: Long,
    val target: String
)

@Keep
data class CryptoResponse(
    val success: Boolean,
    val crypto: Map<String, CryptoModel>
)

@Keep
data class CryptoModel(
    val icon_url: String,
    val symbol: String,
    val name_full: String,
    val name: String,
    val max_supply: String
)