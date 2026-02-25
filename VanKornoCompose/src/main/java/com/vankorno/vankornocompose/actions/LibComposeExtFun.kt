package com.vankorno.vankornocompose.actions

import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import com.vankorno.vankornohelpers.eLog
import kotlinx.coroutines.flow.StateFlow


fun Color.tweakTransparency(opacity: Float) = Color(this.red, this.green, this.blue, opacity)


fun StateFlow<String>.toNoNullInt(default: Int = 0) = try {
                                                        this.value.toInt()
                                                    } catch (nfe:NumberFormatException) {
                                                        default
                                                    }


fun FocusRequester.libRequest() {
    try {
        requestFocus()
    } catch (e: Exception) {
        // region LOG
            eLog("focusOnTextField", "Error: $e")
        // endregion
    }
}



/** Convenience functions for applying modifiers conditionally */

inline fun Modifier.applyIf(                                       condition: Boolean,
                                                                       block: Modifier.()->Modifier,
) = if (condition) this.block() else this


inline fun Modifier.applyIf(                                       condition: Boolean,
                                                                   trueBlock: Modifier.()->Modifier,
                                                                  falseBlock: Modifier.()->Modifier,
) = if (condition) this.trueBlock() else this.falseBlock()


fun Modifier.applyIf(                        vararg conditions: Pair<Boolean, Modifier.()->Modifier>
) = conditions.fold(this) { accumulator, (cond, mod) ->
    if (cond) accumulator.mod() else accumulator
}









