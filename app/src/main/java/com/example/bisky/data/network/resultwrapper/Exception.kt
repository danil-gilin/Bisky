package com.example.bisky.data.network.resultwrapper

sealed class ApiException : Exception()

data class PayloadApiException(val data: String) : ApiException()
object ServerOverloadException : ApiException()