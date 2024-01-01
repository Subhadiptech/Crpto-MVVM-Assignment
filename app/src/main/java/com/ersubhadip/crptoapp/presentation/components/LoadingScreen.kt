package com.ersubhadip.crptoapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ersubhadip.crptoapp.ui.theme.LexendDeca
import com.ersubhadip.crptoapp.ui.theme.PrimaryBackground
import com.ersubhadip.crptoapp.ui.theme.PrimaryBlue
import com.ersubhadip.crptoapp.ui.theme.SlateDark
import com.ersubhadip.crptoapp.ui.theme.White

@Composable
@Preview
fun LoadingScreen() {
    Column(
        modifier = Modifier.fillMaxSize().background(PrimaryBackground),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(80.dp),
            trackColor = SlateDark,
            color = PrimaryBlue
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Loading your crypto's ...",
            fontSize = 14.sp,
            fontFamily = LexendDeca,
            color = White.copy(alpha = 0.5f)
        )
    }
}