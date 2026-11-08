package com.vankorno.vankornocompose.transl.data

import com.vankorno.vankornohelpers.values.LibLangConst.ENG
import com.vankorno.vankornohelpers.values.LibLangConst.UKR

var excludedTranslLangs = emptySet<TranslLang>()

val LanguagesWithFemForms = lazy { setOf(UKR) }


val TrWeekDayLetters = lazy { mapOf(
    ENG to lazy { listOf("M", "T", "W", "T", "F", "S", "S") },
    UKR to lazy { listOf("П", "В", "С", "Ч", "П", "С", "Н") },
)}


val TrWeekDay2Letters = lazy { mapOf(
    ENG to lazy { listOf("Mo", "Tu", "We", "Th", "Fr", "Sa", "Su") },
    UKR to lazy { listOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Нд") },
)}





