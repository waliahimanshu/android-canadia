package com.waliahimanshu.canadia.presentation.browse

import com.waliahimanshu.canadia.presentation.BasePresenter
import com.waliahimanshu.canadia.presentation.BaseView
import com.waliahimanshu.canadia.presentation.model.BufferooView

/**
 * Defines a contract of operations between the Browse Presenter and Browse View
 */
interface BrowseBufferoosContract {

    interface Presenter : BasePresenter {

        fun retrieveBufferoos()

    }


    interface View : BaseView<Presenter> {

        fun showProgress()

        fun hideProgress()

        fun showBufferoos(bufferoos: List<BufferooView>)

        fun hideBufferoos()

        fun showErrorState()

        fun hideErrorState()

        fun showEmptyState()

        fun hideEmptyState()

    }


}