package com.ersubhadip.crptoapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ersubhadip.crptoapp.R
import com.ersubhadip.crptoapp.ui.theme.LexendDeca
import com.ersubhadip.crptoapp.ui.theme.PrimaryBackground
import com.ersubhadip.crptoapp.ui.theme.White

@Composable
@Preview
fun FailedScreen() {
    Box(modifier = Modifier.fillMaxSize().background(PrimaryBackground)) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item {
                Image(
                    painter = painterResource(R.drawable.ic_warning),
                    contentDescription = "Warning",
                    modifier = Modifier.size(80.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Something went wrong!",
                    fontSize = 14.sp,
                    fontFamily = LexendDeca,
                    color = White.copy(alpha = 0.5f)
                )
            }
        }
    }
}