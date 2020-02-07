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
import com.naemo.contactmanager.ui.card.CardActivity
import javax.inject.Inject

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), HomeNavigator, ContactAdapter.ItemClickListener {

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
    }

    override fun onItemClicked(id: Int, name: String, phone: String, dob: String, address: String, zipcode: String) {
        val intent = Intent(this@HomeActivity, CardActivity::class.java)
        intent.putExtra("id", id)
        intent.putExtra("name", name)
        intent.putExtra("phone", phone)
        intent.putExtra("dob", dob)
        intent.putExtra("address", address)
        intent.putExtra("zipcode", zipcode)
        this@HomeActivity.startActivity(intent)
    }

    private fun viewContacts() {
        val contactList: ArrayList<Contacts> = dbhelper.getContacts(this)
        val adapter = ContactAdapter(this, contactList, this)
        val rv: RecyclerView = mBinder!!.homeRecyclerView
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv.adapter = adapter
    }


    override fun addContact() {
        val intent = Intent(this, AddActivity::class.java)
        startActivity(intent)
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

    override fun onRestart() {
        viewContacts()
        super.onRestart()
    }


}
