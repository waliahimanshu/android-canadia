package com.waliahimanshu.canadia.domain.interactor.browse

import io.reactivex.Single
import com.waliahimanshu.canadia.domain.executor.PostExecutionThread
import com.waliahimanshu.canadia.domain.executor.ThreadExecutor
import com.waliahimanshu.canadia.domain.interactor.SingleUseCase
import com.waliahimanshu.canadia.domain.model.Bufferoo
import com.waliahimanshu.canadia.domain.repository.BufferooRepository
import javax.inject.Inject

/**
 * Use case used for retreiving a [List] of [Bufferoo] instances from the [BufferooRepository]
 */
open class GetBufferoos @Inject constructor(val bufferooRepository: BufferooRepository,
                                            threadExecutor: ThreadExecutor,
                                            postExecutionThread: PostExecutionThread):
        SingleUseCase<List<Bufferoo>, Void?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Void?): Single<List<Bufferoo>> {
        return bufferooRepository.getBufferoos()
    }

}