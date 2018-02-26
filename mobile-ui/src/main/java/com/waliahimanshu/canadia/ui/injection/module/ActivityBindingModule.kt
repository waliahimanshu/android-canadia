package com.waliahimanshu.canadia.ui.injection.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.waliahimanshu.canadia.ui.browse.BrowseActivity
import com.waliahimanshu.canadia.ui.injection.scopes.PerActivity

@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(BrowseActivityModule::class))
    abstract fun bindMainActivity(): BrowseActivity

}