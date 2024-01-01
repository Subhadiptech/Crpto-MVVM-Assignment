package com.ersubhadip.crptoapp.domain.model

data class CryptoLiveModel(
    val loading: Boolean? = true,
    val rates: Map<String, Double>? = null,
    val timestamp: Long? = null,
)