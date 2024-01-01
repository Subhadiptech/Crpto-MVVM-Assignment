package com.ersubhadip.crptoapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ersubhadip.crptoapp.domain.StandardResponse
import com.ersubhadip.crptoapp.presentation.components.HomeScreen
import com.ersubhadip.crptoapp.presentation.components.LoadingScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = hiltViewModel<HomeViewModel>()

            LaunchedEffect(key1 = Unit) {
                viewModel.fetchLiveList()
                viewModel.fetchData()
            }

            val liveList by viewModel.liveList.collectAsStateWithLifecycle()
            val feedData by viewModel.feedData.collectAsStateWithLifecycle()
            val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

            when (val currentData = feedData) {
                is StandardResponse.Failed -> LoadingScreen()
                StandardResponse.Loading -> LoadingScreen()
                is StandardResponse.Success -> HomeScreen(
                    currentData.data,
                    liveList,
                    isLoading = isLoading,
                    onRefresh = viewModel::fetchLiveList
                )

                null -> LoadingScreen()
            }
        }
    }
}