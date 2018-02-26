package com.waliahimanshu.canadia.ui.injection.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import com.waliahimanshu.canadia.domain.executor.PostExecutionThread
import com.waliahimanshu.canadia.domain.repository.BufferooRepository
import com.waliahimanshu.canadia.ui.injection.ApplicationComponent
import com.waliahimanshu.canadia.ui.injection.module.ActivityBindingModule
import com.waliahimanshu.canadia.ui.injection.module.TestApplicationModule
import com.waliahimanshu.canadia.ui.injection.scopes.PerApplication
import com.waliahimanshu.canadia.ui.test.TestApplication

@Component(modules = arrayOf(TestApplicationModule::class, ActivityBindingModule::class,
        AndroidSupportInjectionModule::class))
@PerApplication
interface TestApplicationComponent : ApplicationComponent {

    fun bufferooRepository(): BufferooRepository

    fun postExecutionThread(): PostExecutionThread

    fun inject(application: TestApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): TestApplicationComponent.Builder

        fun build(): TestApplicationComponent
    }

}