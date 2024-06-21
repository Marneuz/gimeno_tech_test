package com.marneux.gimenotechtest.ui.views.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.marneux.gimenotechtest.R

@Composable
fun BottomLogo(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp),
    ) {
        Text(
            "Powered by ", // No lo saco a recurso ya que siempre va a ser el mismo texto
            color = Color.LightGray,
            style = MaterialTheme.typography.bodyMedium
        )
        Image(
            painter = painterResource(R.drawable.mobile),
            contentDescription = "Endalia Mobile Logo",
            modifier = Modifier
                .height(50.dp)
                .size(200.dp)
        )
    }
}