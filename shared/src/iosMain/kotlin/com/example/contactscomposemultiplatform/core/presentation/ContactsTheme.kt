package com.example.contactscomposemultiplatform.core.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.contactscomposemultiplatform.ui.theme.DarkColors
import com.example.contactscomposemultiplatform.ui.theme.LightColors

@Composable
actual fun ContactsTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
)
{
    MaterialTheme(
        colorScheme = if(darkTheme) DarkColors else LightColors,
        typography = MaterialTheme.typography,
        content = content
    )
}