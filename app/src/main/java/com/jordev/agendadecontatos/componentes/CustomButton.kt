package com.jordev.agendadecontatos.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jordev.agendadecontatos.ui.theme.PURPLE500
import com.jordev.agendadecontatos.ui.theme.WHITE

@Composable
fun CustomButton(
    onClickId: () -> Unit,
    modifierId: Modifier = Modifier,
    txt: String,
    enabled: Boolean = true
) {
    TextButton(
        onClick = onClickId,
        modifier = modifierId.fillMaxWidth().padding(20.dp, 10.dp),
        colors = ButtonDefaults.textButtonColors(
            containerColor = PURPLE500,
            contentColor = WHITE
        ),
    ) {
        Text(
            txt,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}