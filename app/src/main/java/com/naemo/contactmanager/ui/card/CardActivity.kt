 package com.naemo.contactmanager.ui.card

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.naemo.contactmanager.BR
import com.naemo.contactmanager.R
import com.naemo.contactmanager.databinding.ActivityCardBinding
import com.naemo.contactmanager.helpers.SnackBarManager
import com.naemo.contactmanager.ui.base.BaseActivity
import com.naemo.contactmanager.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_card.*
import java.util.*
import javax.inject.Inject

 class CardActivity : BaseActivity<ActivityCardBinding, CardViewModel>(), CardNavigator, DatePickerDialog.OnDateSetListener {

     private var mBinder: ActivityCardBinding? = null
     var cardViewModel: CardViewModel? = null
     @Inject set

     var mLayoutId = R.layout.activity_card
     @Inject set

     var snackBarManager: SnackBarManager? = null
     @Inject set

     private var clickedEdit: Boolean? = false
     private var fabIcon: FloatingActionButton? = null
     private var mDatePickerDialog: DatePickerDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doBinding()
        initializeViews()
    }

     private fun doBinding() {
         mBinder = getViewDataBinding()
         mBinder?.viewModel = cardViewModel
         mBinder?.navigator = this
         mBinder?.viewModel?.setNavigator(this)

     }

     private fun showPopUp() {
         AlertDialog.Builder(this)
             .setTitle("Warning")
             .setMessage("Are you sure you want to leave without saving ?")
             .setPositiveButton("Yes") {_, _  ->
                 super.onBackPressed()
             }
             .setNegativeButton("No") {_, _  -> }
             .setIcon(R.drawable.ic_warning)
             .show()
     }

     @SuppressLint("ResourceAsColor")
     private fun initializeViews() {
         fabIcon = mBinder?.saveIcon
        val intent = intent
        val name = intent.getStringExtra("name")
        val phone = intent.getStringExtra("phone")
        val dob = intent.getStringExtra("dob")
        val address = intent.getStringExtra("address")
        val zipcode = intent.getStringExtra("zipcode")

        full_name.setText(name)
        full_number.setText(phone)
         full_dob.text = dob
        full_address.setText(address)
        full_zip_code.setText(zipcode)
    }

     private fun showDatePickerDialog() {
         clickedEdit = true
         mDatePickerDialog = DatePickerDialog(
             this,
             this,
             Calendar.getInstance().get(Calendar.YEAR),
             Calendar.getInstance().get(Calendar.MONTH),
             Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
         )
         mDatePickerDialog?.show()
     }

     override fun getBindingVariable(): Int {
        return BR.viewModel
     }

     override fun getViewModel(): CardViewModel? {
         return cardViewModel
     }

     override fun getLayoutId(): Int {
         return mLayoutId
     }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_edit -> editContact()
            R.id.action_delete -> deleteContact()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteContact() {
        val intent = intent
        val id = intent.getIntExtra("id", 0)
        val name = intent.getStringExtra("name")
        AlertDialog.Builder(this)
            .setTitle("Delete contact?")
            .setMessage("Are you sure you want to delete $name?")
            .setPositiveButton("Yes") { _, _ ->
                if (HomeActivity.dbhelper.deleteContact(id)) {
                    snackBarManager?.showToast(this, "contact deleted")
                    super.onBackPressed()
                } else {
                    snackBarManager?.showToast(this, "contact error occurred")
                }
            }
            .setNegativeButton("No") { _, _ -> }
            .setIcon(R.drawable.ic_warning)
            .show()
    }

     override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
         clickedEdit = true
         val one = 1
         val sum = month + one
         val date = "$dayOfMonth/$sum/$year"
         mBinder?.fullDob?.text = date
     }

    @SuppressLint("RestrictedApi")
    private fun editContact() {
        clickedEdit = true
        save_icon.visibility = View.VISIBLE
        full_name.isEnabled = true
        full_number.isEnabled = true
        full_dob.isEnabled = true
        full_address.isEnabled = true
        full_zip_code.isEnabled = true
    }

      override fun updateContact() {
         val intent = intent
         val id = intent.getIntExtra("id", 0)
         val isUpdate =  getViewModel()?.update(this, id.toString())

         if (isUpdate!!) {
             super.onBackPressed()
             snackBarManager?.showToast(this, "contact updated")
            // snackBarManager?.showSnackBar(this, mBinder?.cardFrame!!, "contact updated" )
         } else {
             snackBarManager?.showToast(this, "error occurred")
            // snackBarManager?.showSnackBar(this, mBinder?.cardFrame!!, "error occurred" )
         }

     }

     override fun onBackPressed() {

         if (clickedEdit!!) {
             showPopUp()
         } else {
             super.onBackPressed()
         }
     }

     fun updateDob(view: View?) {
         clickedEdit = true
         showDatePickerDialog()
     }
 }
