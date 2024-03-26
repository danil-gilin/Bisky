package com.example.bisky.common.ext

fun Double.toOneNumberAfterDot() = String.format("%.1f", this).toDouble()
