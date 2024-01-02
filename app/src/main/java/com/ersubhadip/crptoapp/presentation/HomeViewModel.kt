package com.ersubhadip.crptoapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ersubhadip.crptoapp.domain.IRemoteDataRepository
import com.ersubhadip.crptoapp.domain.StandardResponse
import com.ersubhadip.crptoapp.domain.model.CryptoListModel
import com.ersubhadip.crptoapp.domain.model.CryptoLiveModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val respository: IRemoteDataRepository
) : ViewModel() {

    private val _liveList = MutableStateFlow<StandardResponse<CryptoLiveModel>?>(null)
    val liveList = _liveList.asStateFlow()

    private val _feedData = MutableStateFlow<StandardResponse<CryptoListModel>?>(null)
    val feedData = _feedData.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun fetchLiveList() = viewModelScope.launch {
        respository.fetchLiveCrypto().collectLatest { data ->
            _liveList.value = data
        }
    }

    fun onRefresh() {
        _isLoading.value = true
        if (feedData.value is StandardResponse.Failed) {
            //Call Complete API
            onRefreshForFailure()
            onRefreshLiveCrypto()
        } else {
            //Call only Live data API
            onRefreshLiveCrypto()
        }
        _isLoading.value = false
    }

    private fun onRefreshLiveCrypto() {
        fetchLiveList()
    }

    private fun onRefreshForFailure() {
        fetchData()
        fetchLiveList()
    }

    fun fetchData() = viewModelScope.launch {
        respository.fetchCryptoList().collectLatest { data ->
            _feedData.value = data
        }
    }
}