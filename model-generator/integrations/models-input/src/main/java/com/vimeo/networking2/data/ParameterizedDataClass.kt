package com.vimeo.networking2.data

import java.util.Date

data class ParameterizedDataClass<FIRST_TYPE, SECOND_TYPE, THIRD_TYPE>(
    val first: FIRST_TYPE,
    val second: SECOND_TYPE,
    val third: THIRD_TYPE,
    val date: Date? = null
)