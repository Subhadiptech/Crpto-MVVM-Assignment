package com.ersubhadip.crptoapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ersubhadip.crptoapp.domain.IRemoteDataRepository
import com.ersubhadip.crptoapp.domain.StandardResponse
import com.ersubhadip.crptoapp.domain.model.CryptoListModel
import com.ersubhadip.crptoapp.domain.model.CryptoLiveModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
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

    private var job: Job? = null

    init {
        //Initial Call
        fetchData()
        startServerPolling { fetchLiveList() }
    }

    fun fetchLiveList() = viewModelScope.launch {
        respository.fetchLiveCrypto().collectLatest { data ->
            _liveList.value = data
        }
    }

    //common refresh function which handles both normal refresh as well as failed refresh
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

    //refresh function which handles pull to refresh
    private fun onRefreshLiveCrypto() {
        fetchLiveList()
    }

    //refresh all the API in case of complete Failure
    private fun onRefreshForFailure() {
        fetchData()
        fetchLiveList()
    }

    //Fetch the Main Feed crypto list
    fun fetchData() = viewModelScope.launch {
        respository.fetchCryptoList().collectLatest { data ->
            _feedData.value = data
        }
    }

    //Utility which enables Polling (periodic call after X mins)
    private fun startServerPolling(apiCall: suspend () -> Unit) {
        job?.cancel() //Clearing Job before starting new

        //Calling API after every 3 mins
        job = viewModelScope.launch {
            while (true) {
                apiCall.invoke()
                delay(TimeUnit.MINUTES.toMillis(POLLING_INTERVAL))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        //Cancelling Periodic API call Job
        job?.cancel()
    }

    companion object {
        const val POLLING_INTERVAL = 3L
    }
}