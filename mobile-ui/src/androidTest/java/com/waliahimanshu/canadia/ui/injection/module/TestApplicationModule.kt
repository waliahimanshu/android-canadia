package com.waliahimanshu.canadia.ui.injection.module

import android.app.Application
import android.content.Context
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import com.waliahimanshu.canadia.cache.PreferencesHelper
import com.waliahimanshu.canadia.data.executor.JobExecutor
import com.waliahimanshu.canadia.data.repository.BufferooCache
import com.waliahimanshu.canadia.data.repository.BufferooRemote
import com.waliahimanshu.canadia.domain.executor.PostExecutionThread
import com.waliahimanshu.canadia.domain.executor.ThreadExecutor
import com.waliahimanshu.canadia.domain.repository.BufferooRepository
import com.waliahimanshu.canadia.remote.BufferooService
import com.waliahimanshu.canadia.ui.UiThread
import com.waliahimanshu.canadia.ui.injection.scopes.PerApplication

@Module
class TestApplicationModule {

    @Provides
    @PerApplication
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @PerApplication
    internal fun providePreferencesHelper(): PreferencesHelper {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideBufferooRepository(): BufferooRepository {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideBufferooCache(): BufferooCache {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideBufferooRemote(): BufferooRemote {
        return mock()
    }

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @PerApplication
    internal fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @PerApplication
    internal fun provideBufferooService(): BufferooService {
        return mock()
    }

}