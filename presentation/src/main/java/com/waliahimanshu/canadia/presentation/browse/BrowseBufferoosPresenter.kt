package com.waliahimanshu.canadia.presentation.browse

import io.reactivex.observers.DisposableSingleObserver
import com.waliahimanshu.canadia.domain.interactor.SingleUseCase
import com.waliahimanshu.canadia.domain.model.Bufferoo
import com.waliahimanshu.canadia.presentation.mapper.BufferooMapper
import javax.inject.Inject

class BrowseBufferoosPresenter @Inject constructor(val browseView: BrowseBufferoosContract.View,
                                                   val getBufferoosUseCase: SingleUseCase<List<Bufferoo>, Void>,
                                                   val bufferooMapper: BufferooMapper):
        BrowseBufferoosContract.Presenter {

    init {
        browseView.setPresenter(this)
    }

    override fun start() {
        retrieveBufferoos()
    }

    override fun stop() {
        getBufferoosUseCase.dispose()
    }

    override fun retrieveBufferoos() {
        getBufferoosUseCase.execute(BufferooSubscriber())
    }

    internal fun handleGetBufferoosSuccess(bufferoos: List<Bufferoo>) {
        browseView.hideErrorState()
        if (bufferoos.isNotEmpty()) {
            browseView.hideEmptyState()
            browseView.showBufferoos(bufferoos.map { bufferooMapper.mapToView(it) })
        } else {
            browseView.hideBufferoos()
            browseView.showEmptyState()
        }
    }

    inner class BufferooSubscriber: DisposableSingleObserver<List<Bufferoo>>() {

        override fun onSuccess(t: List<Bufferoo>) {
            handleGetBufferoosSuccess(t)
        }

        override fun onError(exception: Throwable) {
            browseView.hideBufferoos()
            browseView.hideEmptyState()
            browseView.showErrorState()
        }

    }

}