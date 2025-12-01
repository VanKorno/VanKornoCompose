package com.vankorno.vankornocompose.transl.data

import com.vankorno.vankornohelpers.values.LibLangConst.ENG
import com.vankorno.vankornohelpers.values.LibLangConst.UKR

var ExcludedTranslations = emptySet<TranslLang>()

val LanguagesWithFemForms = lazy { setOf(UKR) }


val TrWeekDayLetters = lazy { mapOf(
    ENG to lazy { listOf("M", "T", "W", "T", "F", "S", "S") },
    UKR to lazy { listOf("П", "В", "С", "Ч", "П", "С", "Н") },
)}








