package com.vankorno.vankornocompose.theme_main

import androidx.compose.ui.graphics.Color

// functional colors
private const val IntColorBackground    = 0xFF242424.toInt()
private const val IntColorSurfaceD = 0xFF2C2C2C.toInt()
private const val IntColorTextField = 0xFF353535.toInt()
private const val IntColorTextFieldDark = 0xFF2A2A2A.toInt()

private const val IntColorWhiteText     = 0xFFE6E6E6.toInt()

private const val IntColorBlackBtn      = 0xFF000000.toInt()
private const val IntColorBlackBtnDisabled = 0x4D000000.toInt()
private const val IntColorBlackBtnBorder   = 0xFF5A5A5A.toInt()
private const val IntColorBlackBtnBorderDisabled = 0x1AFFFFFF.toInt()


// accent colors
private const val IntColorRedM800     = 0xFFC62828.toInt()
private const val IntColorPinkM800    = 0xFFAD1457.toInt()
private const val IntColorMagentaM800 = 0xFF6A1B9A.toInt()
private const val IntColorVioletM800  = 0xFF4527A0.toInt()
private const val IntColorBlueM800    = 0xFF283593.toInt()
private const val IntColorLightBlueM800 = 0xFF0277BD.toInt()
private const val IntColorGreenM800   = 0xFF00695C.toInt()
private const val IntColorLightGreenM800 = 0xFF558B2F.toInt()
private const val IntColorYellowM800  = 0xFFF9A825.toInt()
private const val IntColorPumpkinM800 = 0xFFEF6C00.toInt()
private const val IntColorOrangeM800  = 0xFFD84315.toInt()


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
    Red(Color(IntColorRedM800), IntColorRedM800),
    Pink(Color(IntColorPinkM800), IntColorPinkM800),
    Magenta(Color(IntColorMagentaM800), IntColorMagentaM800),
    Violet(Color(IntColorVioletM800), IntColorVioletM800),
    Blue(Color(IntColorBlueM800), IntColorBlueM800),
    LightBlue(Color(IntColorLightBlueM800), IntColorLightBlueM800),
    Green(Color(IntColorGreenM800), IntColorGreenM800),
    LightGreen(Color(IntColorLightGreenM800), IntColorLightGreenM800),
    Yellow(Color(IntColorYellowM800), IntColorYellowM800),
    Pumpkin(Color(IntColorPumpkinM800), IntColorPumpkinM800),
    Orange(Color(IntColorOrangeM800), IntColorOrangeM800),
}










