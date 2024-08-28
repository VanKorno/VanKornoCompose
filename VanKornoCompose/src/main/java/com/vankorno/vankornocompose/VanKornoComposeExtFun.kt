package com.vankorno.vankornocompose

import android.util.Log
import androidx.compose.ui.focus.FocusRequester
import kotlinx.coroutines.flow.StateFlow


fun StateFlow<String>.toNoNullInt(                                               default: Int = 0
) = try {
        this.value.toInt()
    } catch (nfe:NumberFormatException) {
        default
    }



fun FocusRequester.aaakRequest() =  try {
                                        this.requestFocus()
                                        true
                                    } catch (e: Exception) {
                                        // region LOG
                                        Log.e("focusOnTextField", "Error: $e")
                                        // endregion
                                        false
                                    }