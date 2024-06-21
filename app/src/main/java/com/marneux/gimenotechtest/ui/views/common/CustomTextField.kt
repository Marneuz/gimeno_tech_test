package com.marneux.gimenotechtest.ui.views.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.marneux.gimenotechtest.ui.theme.CorpoBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    var isFocused by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(56.dp)
        ) {
            Icon(
                icon,
                contentDescription = "$label Icon",
                tint = CorpoBlue,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                OutlinedTextField(
                    value = value,
                    onValueChange = onValueChange,
                    label = {
                        Text(
                            label,
                            color = Color.Gray,
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = if (isFocused) 12.sp else 16.sp
                        )
                    },
                    visualTransformation = visualTransformation,
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusState ->
                            isFocused = focusState.isFocused
                        },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent,
                        errorBorderColor = Color.Transparent
                    ),
                    textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 2.dp,
                    color = Color.LightGray
                )
            }
        }
    }
}
