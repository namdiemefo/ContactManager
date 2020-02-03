package com.naemo.contactmanager.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.naemo.contactmanager.BR
import com.naemo.contactmanager.R
import com.naemo.contactmanager.databinding.ActivityHomeBinding
import com.naemo.contactmanager.ui.add.AddActivity
import com.naemo.contactmanager.ui.base.BaseActivity
import javax.inject.Inject

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), HomeNavigator {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        doBinding()
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


}
