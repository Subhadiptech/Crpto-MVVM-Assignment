package com.ersubhadip.crptoapp.domain.model

data class CommonCryptoDetail(
    val priceInUSD: String,
    val iconURL: String,
    val symbol: String,
    val nameFull: String,
    val name: String,
    val maxSupply: String
)

fun CryptoListModel.toCommonCryptoDetails(cryptoLiveModel: CryptoLiveModel): List<CommonCryptoDetail> {
    return crypto.mapValues { (_, cryptoDataModel) ->
        val priceInUSD = cryptoLiveModel.rates?.get(cryptoDataModel.symbol)?.toString() ?: "N/A"
        CommonCryptoDetail(
            priceInUSD = priceInUSD,
            iconURL = cryptoDataModel.iconURL,
            symbol = cryptoDataModel.symbol,
            nameFull = cryptoDataModel.nameFull,
            name = cryptoDataModel.name,
            maxSupply = cryptoDataModel.maxSupply
        )
    }.values.toList()
}