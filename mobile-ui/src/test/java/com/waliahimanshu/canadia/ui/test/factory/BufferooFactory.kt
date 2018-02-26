package com.waliahimanshu.canadia.ui.test.factory

import com.waliahimanshu.canadia.presentation.model.BufferooView
import com.waliahimanshu.canadia.ui.test.factory.DataFactory.Factory.randomUuid

/**
 * Factory class for Bufferoo related instances
 */
class BufferooFactory {

    companion object Factory {

        fun makeBufferooView(): BufferooView {
            return BufferooView(randomUuid(), randomUuid(), randomUuid())
        }

    }

}