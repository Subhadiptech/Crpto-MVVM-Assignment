package com.ersubhadip.crptoapp.data

import com.ersubhadip.crptoapp.domain.model.CryptoDataModel
import com.ersubhadip.crptoapp.domain.model.CryptoListModel
import com.ersubhadip.crptoapp.domain.model.CryptoLiveModel

fun LiveDataResponse.toCryptoLiveModel() = CryptoLiveModel(
    rates = this.rates,
    timestamp = this.timestamp
)

fun CryptoResponse.toCryptoListModel(): CryptoListModel {
    val cryptoDataMap = this.crypto.mapValues { (_, cryptoModel) ->
        CryptoDataModel(
            iconURL = cryptoModel.icon_url,
            symbol = cryptoModel.symbol,
            nameFull = cryptoModel.name_full,
            name = cryptoModel.name,
            maxSupply = cryptoModel.max_supply
        )
    }
    return CryptoListModel(crypto = cryptoDataMap)
}