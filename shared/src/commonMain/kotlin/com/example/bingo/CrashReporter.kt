package com.example.bingo

interface CrashReporter {
    fun logException(throwable: Throwable)
    fun log(message: String)
    fun setCustomKey(key: String, value: String)
}