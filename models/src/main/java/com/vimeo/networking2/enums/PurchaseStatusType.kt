package com.vimeo.networking2.enums

enum class PurchaseStatusType(override val value: String?) : StringValue {

    APP_MISMATCH("app_mismatch"),

    AVAILABLE("available"),

    PURCHASED("purchased"),

    UNAVAILABLE("unavailable"),

    UNKNOWN(null)
}
