package com.ersubhadip.crptoapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ersubhadip.crptoapp.R
import com.ersubhadip.crptoapp.ui.theme.Chakra
import com.ersubhadip.crptoapp.ui.theme.Green
import com.ersubhadip.crptoapp.ui.theme.LexendDeca
import com.ersubhadip.crptoapp.ui.theme.PrimaryBackground
import com.ersubhadip.crptoapp.ui.theme.PrimaryBlue
import com.ersubhadip.crptoapp.ui.theme.SlateDark
import com.ersubhadip.crptoapp.ui.theme.White

@Composable
@Preview
fun HomeScreen() {

    val list = (1..10).map { "$it" }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryBackground)
    ) {
        LazyColumn {
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Crypto App",
                    fontFamily = Chakra,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                    color = PrimaryBlue,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            items(list) {
                CryptoItemView()
            }
        }
    }
}

@Composable
@Preview
fun CryptoItemView() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(colors = listOf(SlateDark, PrimaryBackground)),
                shape = RoundedCornerShape(12.dp)
            )
            .background(SlateDark.copy(0.1f))
            .padding(horizontal = 12.dp, vertical = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(64.dp).clip(CircleShape)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://assets.coinlayer.com/icons/ABC.png")
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = "Name",
                placeholder = painterResource(R.drawable.ic_not_found),
                error = painterResource(R.drawable.ic_not_found)
            )
        }
        Spacer(modifier = Modifier.width(6.dp))
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Full Name",
                fontSize = 18.sp,
                fontFamily = LexendDeca,
                color = White
            )
            Text(
                text = "Symbol",
                fontSize = 14.sp,
                fontFamily = LexendDeca,
                color = White.copy(alpha = 0.5f)
            )
            Text(
                text = "Supply",
                fontSize = 10.sp,
                fontFamily = LexendDeca,
                color = White.copy(alpha = 0.3f)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "5.5 USD",
            fontSize = 18.sp,
            fontFamily = LexendDeca,
            color = Green
        )
    }
}
