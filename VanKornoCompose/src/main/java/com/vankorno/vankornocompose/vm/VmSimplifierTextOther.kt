package com.vankorno.vankornocompose.vm

import androidx.lifecycle.SavedStateHandle


class VmPasswordText(                                                      ssh: SavedStateHandle,
                                                                           key: String,
                                                                       default: String = "",
                                                                     maxLength: Int? = null,
                                                                   val minSize: Int? = null,
                                                                     onTextSet: (String)->Unit = {},
) : VmText(ssh, key, default, maxLength = maxLength, maxLines = 1, onTextSet = onTextSet) {
    
    fun isValid(): Boolean {
        val len = text.length
        minSize?.let { if (len < it) return false }
        this@VmPasswordText.maxLength?.let { if (len > it) return false }
        return true
    }
}



class VmEmailText(                                                         ssh: SavedStateHandle,
                                                                           key: String,
                                                                       default: String = "",
                                                                     maxLength: Int? = null,
                                                                     onTextSet: (String)->Unit = {},
) : VmText(ssh, key, default, maxLength = maxLength, maxLines = 1, onTextSet = onTextSet) {
    
    fun isValid(): Boolean {
        val trimmed = text.trim()
        return trimmed.contains('@') && trimmed.substringAfter('@').contains('.')
    }
}

private const val HTTP_START = "http://"
private const val HTTPS_START = "https://"


class VmUrlText(                                                           ssh: SavedStateHandle,
                                                                           key: String,
                                                                       default: String = "",
                                                                     maxLength: Int? = null,
                                                                     onTextSet: (String)->Unit = {},
) : VmText(ssh, key, default, maxLength = maxLength, maxLines = 1, onTextSet = onTextSet) {
    
    fun normalizeUrl(                                           defaultScheme: String = HTTPS_START
    ) {
        if (text.isEmpty()) return
        if (!text.startsWith(HTTP_START) && !text.startsWith(HTTPS_START)) {
            text = defaultScheme + text
        }
    }
    
    fun isValid(): Boolean = text.startsWith(HTTP_START) || text.startsWith(HTTPS_START)
}



class VmHexText(                                                           ssh: SavedStateHandle,
                                                                           key: String,
                                                                       default: String = "",
                                                                     maxLength: Int? = null,
                                                                     onTextSet: (String)->Unit = {},
) : VmText(ssh, key, default, maxLength = maxLength, maxLines = 1, onTextSet = onTextSet) {
    
    override val additionalTextModifier: (String) -> String = { input ->
        input.filter { it.isDigit() || it in 'A'..'F' || it in 'a'..'f' }
    }
}


class VmUpperCaseText(                                                     ssh: SavedStateHandle,
                                                                           key: String,
                                                                       default: String = "",
                                                                     maxLength: Int? = null,
                                                                      maxLines: Int? = null,
                                                                     onTextSet: (String)->Unit = {},
) : VmText(ssh, key, default, maxLength = maxLength, maxLines = maxLines, onTextSet = onTextSet) {
    
    override val additionalTextModifier: (String)->String = { it.uppercase() }
}



class VmLowerCaseText(                                                     ssh: SavedStateHandle,
                                                                           key: String,
                                                                       default: String = "",
                                                                     maxLength: Int? = null,
                                                                      maxLines: Int? = null,
                                                                     onTextSet: (String)->Unit = {},
) : VmText(ssh, key, default, maxLength = maxLength, maxLines = maxLines, onTextSet = onTextSet) {
    
    override val additionalTextModifier: (String)->String = { it.lowercase() }
}



class VmTrimmedText(                                                       ssh: SavedStateHandle,
                                                                           key: String,
                                                                       default: String = "",
                                                                     maxLength: Int? = null,
                                                                      maxLines: Int? = null,
                                                                     onTextSet: (String)->Unit = {},
) : VmText(ssh, key, default, maxLength = maxLength, maxLines = maxLines, onTextSet = onTextSet) {
    
    override val additionalTextModifier: (String)->String = { it.trim() }
}

