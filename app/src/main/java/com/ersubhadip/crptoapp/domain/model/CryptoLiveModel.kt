package com.ersubhadip.crptoapp.domain.model

//Model for live Crypto list
data class CryptoLiveModel(
    val loading: Boolean? = true,
    val rates: Map<String, Double>? = null,
    val timestamp: Long? = null,
)