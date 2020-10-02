package com.mobiquity.testapp.core.ext

import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.isConnectionError() = this.isNetworkException() || this.cause?.isNetworkException() == true

private fun Throwable.isNetworkException() =
    this is ConnectException || this is UnknownHostException || this is SocketTimeoutException