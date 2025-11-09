package com.example.bingo

import dev.gitlive.firebase.crashlytics.crashlytics
import platform.Foundation.NSLog

class FirebaseCrashReporter : CrashReporter {
    private val crashlytics = dev.gitlive.firebase.Firebase.crashlytics

    override fun logException(throwable: Throwable) {
        crashlytics.recordException(throwable)
    }

    override fun log(message: String) {
        crashlytics.log(message)
        // Also log to console for debugging
        NSLog("Crashlytics: $message")
    }

    override fun setCustomKey(key: String, value: String) {
        crashlytics.setCustomKey(key, value)
    }
}