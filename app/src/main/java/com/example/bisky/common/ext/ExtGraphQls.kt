package com.example.bisky.common.ext

import com.apollographql.apollo3.api.Optional

fun <T> T.toOptional(): Optional<T> {
    return Optional.present(this)
}
