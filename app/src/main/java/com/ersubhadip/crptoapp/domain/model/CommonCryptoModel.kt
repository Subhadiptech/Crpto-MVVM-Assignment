package com.ersubhadip.crptoapp.domain.model

import com.ersubhadip.crptoapp.utils.roundToXDecimalPlaces

data class CommonCryptoDetail( //UI related Model
    val priceInUSD: String,
    val iconURL: String,
    val symbol: String,
    val nameFull: String,
    val name: String,
    val maxSupply: String
)

fun CryptoListModel.toCommonCryptoDetails(cryptoLiveModel: CryptoLiveModel): List<CommonCryptoDetail> {
    return crypto.mapValues { (_, cryptoDataModel) ->
        val priceInUSD =
            cryptoLiveModel.rates?.get(cryptoDataModel.symbol)?.roundToXDecimalPlaces(6.0)
                ?.toString() ?: "N/A"
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