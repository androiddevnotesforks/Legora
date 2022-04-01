package com.yazantarifi.legora

import androidx.multidex.MultiDexApplication
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.messaging.FirebaseMessaging
import com.yazantarifi.legora.utils.TimberCrashReportingTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class LegoraApplication: MultiDexApplication(), Thread.UncaughtExceptionHandler {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
        FirebaseCrashlytics.getInstance().sendUnsentReports()
        FirebaseMessaging.getInstance().isAutoInitEnabled = true
        FirebaseAnalytics.getInstance(this).setAnalyticsCollectionEnabled(true)
        registerTimberConfiguration()
    }

    override fun uncaughtException(p0: Thread, p1: Throwable) {
        Timber.e(p1)
    }

    private fun registerTimberConfiguration() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(TimberCrashReportingTree())
        }
    }

}