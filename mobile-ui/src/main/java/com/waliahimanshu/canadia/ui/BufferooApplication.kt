package com.waliahimanshu.canadia.ui

import android.app.Activity
import android.app.Application
import android.support.v4.BuildConfig
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import com.waliahimanshu.canadia.ui.injection.DaggerApplicationComponent
import timber.log.Timber
import javax.inject.Inject

class BufferooApplication : Application(), HasActivityInjector {

    @Inject lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
        setupTimber()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

}
