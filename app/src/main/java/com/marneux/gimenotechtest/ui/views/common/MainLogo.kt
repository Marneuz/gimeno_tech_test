package com.marneux.gimenotechtest.ui.views.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.marneux.gimenotechtest.R

@Composable
fun MainLogo() {
    Image(
        painter = painterResource(R.drawable.logo),
        contentDescription = "Logo",
        modifier = Modifier
            .height(70.dp)
            .fillMaxSize(),
        contentScale = ContentScale.Fit
    )
}