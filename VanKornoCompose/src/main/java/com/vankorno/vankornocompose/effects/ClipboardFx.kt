package com.vankorno.vankornocompose.effects

import android.content.ClipboardManager
import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.vankorno.vankornohelpers.values.getClipboard

@Composable
fun rememberClipboardText(): State<String> {
    val context = LocalContext.current
    var clipboardText by remember { mutableStateOf("") }
    
    LaunchedEffect(Unit) {
        clipboardText = getClipboard()
    }
    
    DisposableEffect(Unit) {
        val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE)
        if (clipboardManager is ClipboardManager) {
            val listener = ClipboardManager.OnPrimaryClipChangedListener {
                clipboardText = getClipboard()
            }
            clipboardManager.addPrimaryClipChangedListener(listener)
            onDispose {
                clipboardManager.removePrimaryClipChangedListener(listener)
            }
        }
        onDispose {}
    }
    return remember { derivedStateOf { clipboardText } }
}







