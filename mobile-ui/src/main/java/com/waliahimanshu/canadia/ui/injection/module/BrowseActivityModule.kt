package com.waliahimanshu.canadia.ui.injection.module

import dagger.Module
import dagger.Provides
import com.waliahimanshu.canadia.domain.interactor.browse.GetBufferoos
import com.waliahimanshu.canadia.presentation.browse.BrowseBufferoosContract
import com.waliahimanshu.canadia.presentation.browse.BrowseBufferoosPresenter
import com.waliahimanshu.canadia.presentation.mapper.BufferooMapper
import com.waliahimanshu.canadia.ui.browse.BrowseActivity
import com.waliahimanshu.canadia.ui.injection.scopes.PerActivity



/**
 * Module used to provide dependencies at an activity-level.
 */
@Module
open class BrowseActivityModule {

    @PerActivity
    @Provides
    internal fun provideBrowseView(browseActivity: BrowseActivity): BrowseBufferoosContract.View {
        return browseActivity
    }

    @PerActivity
    @Provides
    internal fun provideBrowsePresenter(mainView: BrowseBufferoosContract.View,
                                        getBufferoos: GetBufferoos, mapper: BufferooMapper):
            BrowseBufferoosContract.Presenter {
        return BrowseBufferoosPresenter(mainView, getBufferoos, mapper)
    }

}
