package com.waliahimanshu.canadia.ui.injection.component

import dagger.Subcomponent
import dagger.android.AndroidInjector
import com.waliahimanshu.canadia.ui.browse.BrowseActivity

@Subcomponent
interface BrowseActivitySubComponent : AndroidInjector<BrowseActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<BrowseActivity>()

}