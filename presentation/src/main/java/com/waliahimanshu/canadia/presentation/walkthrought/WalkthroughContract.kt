package com.waliahimanshu.canadia.presentation.walkthrought

import com.waliahimanshu.canadia.presentation.BasePresenter
import com.waliahimanshu.canadia.presentation.BaseView

interface WalkthroughContract {

    interface Presenter : BasePresenter {

    }
    interface View : BaseView<Presenter>
    {

    }
}