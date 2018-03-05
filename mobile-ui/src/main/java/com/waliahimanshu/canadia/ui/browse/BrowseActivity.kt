package com.waliahimanshu.canadia.ui.browse

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.waliahimanshu.canadia.presentation.browse.BrowseBufferoosContract
import com.waliahimanshu.canadia.presentation.model.BufferooView
import com.waliahimanshu.canadia.ui.R
import com.waliahimanshu.canadia.ui.mapper.BufferooMapper
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_browse.*
import javax.inject.Inject

class BrowseActivity: AppCompatActivity(), BrowseBufferoosContract.View {

    @Inject lateinit var onboardingPresenter: BrowseBufferoosContract.Presenter
    @Inject lateinit var browseAdapter: BrowseAdapter
    @Inject lateinit var mapper: BufferooMapper

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, BrowseActivity::class.java)
//        intent.putExtra(INTENT_USER_ID, user.id)
            return intent
        }

    }

    override fun setPresenter(presenter: BrowseBufferoosContract.Presenter) {
        onboardingPresenter = presenter
    }

    override fun hideProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun showProgress() {
        progress.visibility = View.GONE
    }

    override fun showBufferoos(bufferoos: List<BufferooView>) {
        browseAdapter.bufferoos = bufferoos.map { mapper.mapToViewModel(it) }
        browseAdapter.notifyDataSetChanged()
        recycler_browse.visibility = View.VISIBLE
    }

    override fun hideBufferoos() {
        recycler_browse.visibility = View.VISIBLE
    }

    override fun showErrorState() {
    }

    override fun hideErrorState() {
    }

    override fun showEmptyState() {
    }

    override fun hideEmptyState() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)
        AndroidInjection.inject(this)
        setupBrowseRecycler()
    }

    override fun onStart() {
        super.onStart()
        onboardingPresenter.start()
    }

    private fun setupBrowseRecycler() {
        recycler_browse.layoutManager = LinearLayoutManager(this)
        recycler_browse.adapter = browseAdapter
    }
}