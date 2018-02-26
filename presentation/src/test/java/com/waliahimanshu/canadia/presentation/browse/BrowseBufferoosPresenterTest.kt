package com.waliahimanshu.canadia.presentation.browse

import com.nhaarman.mockito_kotlin.*
import io.reactivex.observers.DisposableSingleObserver
import com.waliahimanshu.canadia.domain.interactor.browse.GetBufferoos
import com.waliahimanshu.canadia.domain.model.Bufferoo
import com.waliahimanshu.canadia.presentation.mapper.BufferooMapper
import com.waliahimanshu.canadia.presentation.test.factory.BufferooFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BrowseBufferoosPresenterTest {

    private lateinit var mockBrowseBufferoosView: BrowseBufferoosContract.View
    private lateinit var mockBufferooMapper: BufferooMapper
    private lateinit var mockGetBufferoos: GetBufferoos

    private lateinit var browseBufferoosPresenter: BrowseBufferoosPresenter
    private lateinit var captor: KArgumentCaptor<DisposableSingleObserver<List<Bufferoo>>>

    @Before
    fun setup() {
        captor = argumentCaptor<DisposableSingleObserver<List<Bufferoo>>>()
        mockBrowseBufferoosView = mock()
        mockBufferooMapper = mock()
        mockGetBufferoos = mock()
        browseBufferoosPresenter = BrowseBufferoosPresenter(mockBrowseBufferoosView,
                mockGetBufferoos, mockBufferooMapper)
    }

    //<editor-fold desc="Retrieve Bufferoos">
    @Test
    fun retrieveBufferoosHidesErrorState() {
        browseBufferoosPresenter.retrieveBufferoos()

        verify(mockGetBufferoos).execute(captor.capture(), eq(null))
        captor.firstValue.onSuccess(BufferooFactory.makeBufferooList(2))
        verify(mockBrowseBufferoosView).hideErrorState()
    }

    @Test
    fun retrieveBufferoosHidesEmptyState() {
        browseBufferoosPresenter.retrieveBufferoos()

        verify(mockGetBufferoos).execute(captor.capture(), eq(null))
        captor.firstValue.onSuccess(BufferooFactory.makeBufferooList(2))
        verify(mockBrowseBufferoosView).hideEmptyState()
    }

    @Test
    fun retrieveBufferoosShowsBufferoos() {
        val bufferoos = BufferooFactory.makeBufferooList(2)
        browseBufferoosPresenter.retrieveBufferoos()

        verify(mockGetBufferoos).execute(captor.capture(), eq(null))
        captor.firstValue.onSuccess(bufferoos)
        verify(mockBrowseBufferoosView).showBufferoos(
                bufferoos.map { mockBufferooMapper.mapToView(it) })
    }

    @Test
    fun retrieveBufferoosShowsEmptyState() {
        browseBufferoosPresenter.retrieveBufferoos()

        verify(mockGetBufferoos).execute(captor.capture(), eq(null))
        captor.firstValue.onSuccess(BufferooFactory.makeBufferooList(0))
        verify(mockBrowseBufferoosView).showEmptyState()
    }

    @Test
    fun retrieveBufferoosHidesBufferoos() {
        browseBufferoosPresenter.retrieveBufferoos()

        verify(mockGetBufferoos).execute(captor.capture(), eq(null))
        captor.firstValue.onSuccess(BufferooFactory.makeBufferooList(0))
        verify(mockBrowseBufferoosView).hideBufferoos()
    }

    @Test
    fun retrieveBufferoosShowsErrorState() {
        browseBufferoosPresenter.retrieveBufferoos()

        verify(mockGetBufferoos).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())
        verify(mockBrowseBufferoosView).showErrorState()
    }

    @Test
    fun retrieveBufferoosHidesBufferoosWhenErrorThrown() {
        browseBufferoosPresenter.retrieveBufferoos()

        verify(mockGetBufferoos).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())
        verify(mockBrowseBufferoosView).hideBufferoos()
    }

    @Test
    fun retrieveBufferoosHidesEmptyStateWhenErrorThrown() {
        browseBufferoosPresenter.retrieveBufferoos()

        verify(mockGetBufferoos).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())
        verify(mockBrowseBufferoosView).hideEmptyState()
    }
    //</editor-fold>

}