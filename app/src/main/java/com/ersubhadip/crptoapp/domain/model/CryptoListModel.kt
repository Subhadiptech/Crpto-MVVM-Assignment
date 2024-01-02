package com.ersubhadip.crptoapp.domain.model

//Model for List of crypto data
data class CryptoListModel(
    val crypto: Map<String, CryptoDataModel>
)

data class CryptoDataModel(
    val iconURL: String,
    val symbol: String,
    val nameFull: String,
    val name: String,
    val maxSupply: String
)