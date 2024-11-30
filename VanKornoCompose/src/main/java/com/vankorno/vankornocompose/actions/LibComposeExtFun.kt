package com.vankorno.vankornocompose.actions

import android.util.Log
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.vankorno.vankornocompose.LibScreen.Companion.microUI
import com.vankorno.vankornocompose.LibScreen.Companion.smallUI
import kotlinx.coroutines.flow.StateFlow


fun Color.tweakTransparency(opacity: Float) = Color(this.red, this.green, this.blue, opacity)


fun StateFlow<String>.toNoNullInt(default: Int = 0) = try {
                                                        this.value.toInt()
                                                    } catch (nfe:NumberFormatException) {
                                                        default
                                                    }


fun FocusRequester.libRequest() =  try {
                                        this.requestFocus()
                                        true
                                    } catch (e: Exception) {
                                        // region LOG
                                        Log.e("focusOnTextField", "Error: $e")
                                        // endregion
                                        false
                                    }


fun Int.spOrLess() =    if (this <= 7)
                            this.sp
                        else if (microUI)
                            (this - 4).sp
                        else if (smallUI)
                            (this - 2).sp
                        else
                            this.sp