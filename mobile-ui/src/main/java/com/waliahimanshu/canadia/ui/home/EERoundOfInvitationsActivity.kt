package com.waliahimanshu.canadia.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
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

    private var drawer: DrawerLayout? = null

    private var nvDrawer: NavigationView? = null

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
        drawer = findViewById(R.id.drawer_layout)
        // Setup drawer view

        // Find our drawer view
        nvDrawer =  findViewById(R.id.nvView)
        // Setup drawer view
        setupDrawerContent(nvDrawer)

//        setupDrawerToggle();


        loadData()


    }

    private fun setupDrawerToggle(): ActionBarDrawerToggle {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
    }
    private fun setupDrawerContent(navigationView: NavigationView?) {
        navigationView?.setNavigationItemSelectedListener { menuItem ->
            selectDrawerItem(menuItem)
            true
        }
    }

    private fun selectDrawerItem(menuItem: MenuItem) {

        when (menuItem.itemId) {
            R.id.nav_first_fragment -> {

            }
            R.id.nav_second_fragment -> {

            }
            R.id.nav_third_fragment -> {

            }
            else -> {

            }
        }

        menuItem.isChecked = true;
        // Set action bar title
        title = menuItem.title
        // Close the navigation drawer
        drawer?.closeDrawer(GravityCompat.END)
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
        val eeCrsTable = dataSnapshot.child("ee_crs").child("2018")
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu_filter, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_filter -> {
                drawer?.openDrawer(GravityCompat.END)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
