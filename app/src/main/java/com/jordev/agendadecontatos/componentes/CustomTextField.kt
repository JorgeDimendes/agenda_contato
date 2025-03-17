package com.jordev.agendadecontatos.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    valueId: String,
    onValueChangeId: (String) -> Unit,
    labelId: String,
    modifierId: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    keyboardOptionsId: KeyboardOptions = KeyboardOptions.Default,
    isError: Boolean = false
) {
    OutlinedTextField(
        value = valueId,
        onValueChange = onValueChangeId,
        label = { Text(text = labelId) },
        modifier = modifierId
            .fillMaxWidth()
            .padding(20.dp, 5.dp),
        singleLine = true,
        keyboardOptions = keyboardOptionsId,
        isError = isError,
        maxLines = 1,
        leadingIcon = leadingIcon?.let { { Icon(it, contentDescription = null) } },
        trailingIcon = {
            if (valueId.isNotEmpty()) {
                IconButton(onClick = { onValueChangeId("") }) { // 🔥 Exclui o texto
                    Icon(imageVector = Icons.Default.Clear, contentDescription = "Limpar texto")
                }
            }
        }
    )
}
