package com.ersubhadip.crptoapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ersubhadip.crptoapp.domain.StandardResponse
import com.ersubhadip.crptoapp.presentation.components.FailedScreen
import com.ersubhadip.crptoapp.presentation.components.HomeScreen
import com.ersubhadip.crptoapp.presentation.components.LoadingScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel = hiltViewModel<HomeViewModel>()

            val liveList by viewModel.liveList.collectAsStateWithLifecycle()
            val feedData by viewModel.feedData.collectAsStateWithLifecycle()
            val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

            val refreshState = rememberPullRefreshState(
                refreshing = isLoading, onRefresh = viewModel::onRefresh
            )

            Box(modifier = Modifier.fillMaxSize().pullRefresh(refreshState)) {
                when (val currentData = feedData) {
                    StandardResponse.Loading -> LoadingScreen()
                    is StandardResponse.Success -> HomeScreen(currentData.data, liveList)
                    is StandardResponse.Failed -> FailedScreen()
                    null -> FailedScreen()
                }
                PullRefreshIndicator(
                    state = refreshState,
                    refreshing = isLoading,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }
        }
    }
}