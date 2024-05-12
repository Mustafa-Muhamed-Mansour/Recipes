package com.recipes.app.composeble

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun CreateAccountToUser(
    addValue: MutableState<String>,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier,
    label: @Composable () -> Unit,
    leadingIcon: @Composable () -> Unit,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    color: TextFieldColors = OutlinedTextFieldDefaults.colors(),
) {

    OutlinedTextField(
        value = addValue.value,
        onValueChange = {
            addValue.value = it
        },
        modifier = modifier,
        keyboardOptions = keyboardOptions,
        label = label,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        colors = color
    )

}