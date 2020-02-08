package com.naemo.contactmanager.ui.add

import android.annotation.TargetApi
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.naemo.contactmanager.BR
import com.naemo.contactmanager.R
import com.naemo.contactmanager.databinding.ActivityAddBinding
import com.naemo.contactmanager.ui.base.BaseActivity
import java.util.*
import javax.inject.Inject

class AddActivity : BaseActivity<ActivityAddBinding, AddViewModel>(), AddNavigator, DatePickerDialog.OnDateSetListener {

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    var addViewModel: AddViewModel? = null
    @Inject set

    var mLayoutId = R.layout.activity_add
    @Inject set

    private var mBinder: ActivityAddBinding? = null
    private var mDatePickerDialog: DatePickerDialog? = null
    private var dob: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doBinding()
    }

    private fun doBinding() {
        mBinder = getViewDataBinding()
        mBinder?.viewModel = addViewModel
        mBinder?.navigator = this
        mBinder?.viewModel?.setNavigator(this)
        initializeViews()
    }

    private fun initializeViews() {
        dob = mBinder?.birthDate
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): AddViewModel? {
        return addViewModel
    }

    override fun getLayoutId(): Int {
        return mLayoutId
    }

    override fun datePick() {
       showDatePickerDialog()
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun showDatePickerDialog() {
       mDatePickerDialog = DatePickerDialog(
            this,
            this,
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        )
        mDatePickerDialog?.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val one = 1
        val sum = month + one
        val date = "$dayOfMonth/$sum/$year"
        mBinder?.birthDate?.text = date
    }

    override fun saveContact() {
        getViewModel()?.add(this)
        clearFields()
    }

    private fun clearFields() {
        mBinder?.firstName?.text?.clear()
        mBinder?.lastName?.text?.clear()
        mBinder?.phoneNumber?.text?.clear()
        mBinder?.birthDate?.text = ""
        mBinder?.address?.text?.clear()
        mBinder?.zipCode?.text?.clear()
    }

}
