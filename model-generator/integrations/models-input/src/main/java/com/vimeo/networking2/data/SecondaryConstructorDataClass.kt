package com.vimeo.networking2.data

import java.util.Date

data class SecondaryConstructorDataClass(val foo: String, val date: Date) {
    constructor(date: Date) : this("foo", date)
}