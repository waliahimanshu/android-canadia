package com.waliahimanshu.canadia.ui.injection.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import com.waliahimanshu.canadia.cache.BufferooCacheImpl
import com.waliahimanshu.canadia.cache.PreferencesHelper
import com.waliahimanshu.canadia.cache.db.DbOpenHelper
import com.waliahimanshu.canadia.cache.mapper.BufferooEntityMapper
import com.waliahimanshu.canadia.data.BufferooDataRepository
import com.waliahimanshu.canadia.data.executor.JobExecutor
import com.waliahimanshu.canadia.data.mapper.BufferooMapper
import com.waliahimanshu.canadia.data.repository.BufferooCache
import com.waliahimanshu.canadia.data.repository.BufferooRemote
import com.waliahimanshu.canadia.data.source.BufferooDataStoreFactory
import com.waliahimanshu.canadia.domain.executor.PostExecutionThread
import com.waliahimanshu.canadia.domain.executor.ThreadExecutor
import com.waliahimanshu.canadia.domain.repository.BufferooRepository
import com.waliahimanshu.canadia.remote.BufferooRemoteImpl
import com.waliahimanshu.canadia.remote.BufferooService
import com.waliahimanshu.canadia.remote.BufferooServiceFactory
import com.waliahimanshu.canadia.ui.BuildConfig
import com.waliahimanshu.canadia.ui.UiThread
import com.waliahimanshu.canadia.ui.injection.scopes.PerApplication

/**
 * Module used to provide dependencies at an application-level.
 */
@Module
open class ApplicationModule {

    @Provides
    @PerApplication
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @PerApplication
    internal fun providePreferencesHelper(context: Context): PreferencesHelper {
        return PreferencesHelper(context)
    }


    @Provides
    @PerApplication
    internal fun provideBufferooRepository(factory: BufferooDataStoreFactory,
                                           mapper: BufferooMapper): BufferooRepository {
        return BufferooDataRepository(factory, mapper)
    }

    @Provides
    @PerApplication
    internal fun provideBufferooCache(factory: DbOpenHelper,
                                      entityMapper: BufferooEntityMapper,
                                      mapper: com.waliahimanshu.canadia.cache.db.mapper.BufferooMapper,
                                      helper: PreferencesHelper): BufferooCache {
        return BufferooCacheImpl(factory, entityMapper, mapper, helper)
    }

    @Provides
    @PerApplication
    internal fun provideBufferooRemote(service: BufferooService,
                                       factory: com.waliahimanshu.canadia.remote.mapper.BufferooEntityMapper): BufferooRemote {
        return BufferooRemoteImpl(service, factory)
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
        return BufferooServiceFactory.makeBuffeoorService(BuildConfig.DEBUG)
    }
}
