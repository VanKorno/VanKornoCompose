package com.vankorno.vankornocompose.theme_main

import androidx.compose.ui.graphics.Color
import com.vankorno.vankornohelpers.values.LibColors.*

// functional colors
private const val IntColorBackground = 0xFF151515.toInt()
private const val IntColorSurfaceD = 0xFF2F2F2F.toInt()
private const val IntColorTextField = 0xFF353535.toInt()
private const val IntColorTextFieldDark = 0xFF2A2A2A.toInt()

private const val IntColorWhiteText = 0xFFECECEC.toInt()

private const val IntColorBlackBtn = 0xFF000000.toInt()
private const val IntColorBlackBtnDisabled = 0x4D000000.toInt()
private const val IntColorBlackBtnBorder = 0xFF5A5A5A.toInt()
private const val IntColorBlackBtnBorderDisabled = 0x1AFFFFFF.toInt()



enum class LibColor(                                                           val color: Color,
                                                                                val argb: Int,
) {
    Background(Color(IntColorBackground), IntColorBackground),
    Surface(Color(IntColorSurfaceD), IntColorSurfaceD),
    TextField(Color(IntColorTextField), IntColorTextField),
    TextFieldDark(Color(IntColorTextFieldDark), IntColorTextFieldDark),
    
    WhiteText(Color(IntColorWhiteText), IntColorWhiteText),
    BlackBtn(Color(IntColorBlackBtn), IntColorBlackBtn),
    BlackBtnDisabled(Color(IntColorBlackBtnDisabled), IntColorBlackBtnDisabled),
    BlackBtnBorder(Color(IntColorBlackBtnBorder), IntColorBlackBtnBorder),
    BlackBtnBorderDisabled(Color(IntColorBlackBtnBorderDisabled), IntColorBlackBtnBorderDisabled),
}


enum class LibAccentColor(                                                     val color: Color,
                                                                                val argb: Int,
) {
    Black(Color(PlainBlack), PlainBlack),
    Grey(Color(PlainGrey), PlainGrey),
    
    Red(Color(PlainRed), PlainRed),
    Pink(Color(PlainPink), PlainPink),
    Magenta(Color(PlainMagenta), PlainMagenta),
    Violet(Color(PlainViolet), PlainViolet),
    Blue(Color(PlainBlueDark), PlainBlueDark),
    LightBlue(Color(PlainBlueLight), PlainBlueLight),
    Green(Color(PlainGreenDark), PlainGreenDark),
    LightGreen(Color(PlainGreenLight), PlainGreenLight),
    Olive(Color(PlainOlive), PlainOlive),
    Brown(Color(PlainBrown), PlainBrown),
    Yellow(Color(PlainYellow), PlainYellow),
    Avocado(Color(PlainAvocado), PlainAvocado),
    Orange(Color(PlainOrange), PlainOrange),
    Cyan(Color(PlainCyan), PlainCyan),
}










