package com.waliahimanshu.canadia.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import com.google.firebase.database.*
import com.waliahimanshu.canadia.ui.R
import com.waliahimanshu.canadia.ui.home.model.RoundOfInvitationModel
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*


class EERoundOfInvitationsActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    private lateinit var database: DatabaseReference

    private var progressBar: ProgressBar? = null

    companion object {
        fun getLaunchIntent(context: Context): Intent {
            return Intent(context, EERoundOfInvitationsActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        fab.setOnClickListener { view ->
            //todo
        }

        if (item_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        database = FirebaseDatabase.getInstance().reference
        progressBar = findViewById(R.id.progress_bar_home)
        progressBar?.visibility = View.VISIBLE
        loadData()
    }

    private fun loadData(): ArrayList<RoundOfInvitationModel> {
        val itemList: ArrayList<RoundOfInvitationModel> = java.util.ArrayList()

        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                loadData(dataSnapshot, itemList)
                if (itemList.isNotEmpty()) {
                    progressBar?.visibility = View.GONE
                    setupRecyclerView(item_list, itemList)
                }
            }

            override fun onCancelled(dataSnapshot: DatabaseError?) {
                TODO("logging db fetch failed")
            }
        })
        return itemList
    }

    private fun loadData(dataSnapshot: DataSnapshot, itemList: ArrayList<RoundOfInvitationModel>) {
        val eeCrsTable = dataSnapshot.child("ee_crs")
        val children = eeCrsTable.children

        for (item: DataSnapshot in children) {
            val invitationModel: RoundOfInvitationModel? = item.getValue(RoundOfInvitationModel::class.java)
            if (invitationModel != null) {
                itemList.add(invitationModel)
            }
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, items: ArrayList<RoundOfInvitationModel>) {
        recyclerView.adapter = RoundOfInvitationsAdapter(this, items, twoPane)
    }
}
