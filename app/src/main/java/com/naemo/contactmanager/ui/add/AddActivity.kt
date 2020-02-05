package com.naemo.contactmanager.ui.add

import android.annotation.TargetApi
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.naemo.contactmanager.BR
import com.naemo.contactmanager.R
import com.naemo.contactmanager.databinding.ActivityAddBinding
import com.naemo.contactmanager.ui.base.BaseActivity
import com.naemo.contactmanager.ui.helpers.DatePickerFragment
import java.util.*
import javax.inject.Inject




class AddActivity : BaseActivity<ActivityAddBinding, AddViewModel>(), AddNavigator, DatePickerDialog.OnDateSetListener {

    var addViewModel: AddViewModel? = null
    @Inject set

    var mLayoutId = R.layout.activity_add
    @Inject set

    var mBinder: ActivityAddBinding? = null

    private var mDatePickerDialog: DatePickerDialog? = null
    private var listener: DatePickerDialog.OnDateSetListener? = null

    var dob: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doBinding()
        savedInstanceState?.getString("first_name")
        savedInstanceState?.getString("last_name")
        savedInstanceState?.getString("phone")
        savedInstanceState?.getString("birthday")
        savedInstanceState?.getString("zip_code")
        savedInstanceState?.getString("address")

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

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

        outState.putString("first_name", mBinder?.firstName?.text.toString())
        outState.putString("last_name", mBinder?.lastName?.text.toString())
        outState.putString("phone", mBinder?.phoneNumber?.text.toString())
        outState.putString("birthday", dob?.text.toString())
        outState.putString("address", mBinder?.address?.text.toString())
        outState.putString("zip_code", mBinder?.zipCode?.text.toString())

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

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
       // val datePicker: DialogFragment = DatePickerFragment()
       // var arguments = Bundle()
       // arguments.putString("year", Calendar.YEAR.toString())
       // datePicker.show(supportFragmentManager, "date picker")
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val one = 1
        val sum = month + one
        val date = "$dayOfMonth/$sum/$year"
        mBinder?.birthDate?.text = date
       /* var c: Calendar = Calendar.getInstance()
        c.set(Calendar.YEAR, year)
        c.set(Calendar.MONTH, month)
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth)*/

       // var currentDateString: String = DateFormat.getDateInstance().format(c.time)
        dob?.text = date

    }

    override fun saveContact() {

    }


}
