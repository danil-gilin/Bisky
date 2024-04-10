package com.example.bisky.common.ext

fun<T> List<T>.saveSubList(from: Int,to: Int) = try {
    this.subList(from, to)
} catch (e: Throwable) {
    this
}