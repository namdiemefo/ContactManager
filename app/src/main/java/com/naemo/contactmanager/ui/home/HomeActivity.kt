package com.naemo.contactmanager.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.naemo.contactmanager.BR
import com.naemo.contactmanager.R
import com.naemo.contactmanager.databinding.ActivityHomeBinding
import com.naemo.contactmanager.db.DbHelper
import com.naemo.contactmanager.db.models.Contacts
import com.naemo.contactmanager.ui.adapters.ContactAdapter
import com.naemo.contactmanager.ui.add.AddActivity
import com.naemo.contactmanager.ui.base.BaseActivity
import javax.inject.Inject

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), HomeNavigator {

    //var mAdapter: RecyclerView.Adapter<*>? = null
   // var layoutmanager: LinearLayoutManager? = null

    override fun addContact() {
        val intent = Intent(this, AddActivity::class.java)
        startActivity(intent)
    }

    var homeViewModel: HomeViewModel? = null
    @Inject set

    var mLayoutId = R.layout.activity_home
    @Inject set

    var mBinder: ActivityHomeBinding? = null
    var fabIcon: FloatingActionButton? = null

    companion object {
        lateinit var dbhelper: DbHelper
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doBinding()
        dbhelper = DbHelper(this, null, null, 1)
        viewContacts()
        /*mAdapter = ContactAdapter()
        layoutmanager = LinearLayoutManager(this)
        mBinder?.homeRecyclerView?.layoutManager = layoutmanager
        mBinder?.homeRecyclerView?.adapter = mAdapter*/
    }

    private fun viewContacts() {
        val contactList: ArrayList<Contacts> = dbhelper.getContacts(this)
        val adapter = ContactAdapter(this, contactList)
        val rv: RecyclerView = mBinder!!.homeRecyclerView
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv.adapter = adapter
    }

    private fun doBinding() {
        mBinder = getViewDataBinding()
        mBinder?.viewModel = homeViewModel
        mBinder?.navigator = this
        mBinder?.viewModel?.setNavigator(this)
        initializeViews()
    }

    private fun initializeViews() {
        fabIcon = mBinder?.fabIcon
    }


    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): HomeViewModel? {
        return homeViewModel
    }

    override fun getLayoutId(): Int {
        return mLayoutId
    }

    override fun onResume() {
        viewContacts()
        super.onResume()
    }


}
