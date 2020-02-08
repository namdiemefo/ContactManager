 package com.naemo.contactmanager.ui.card

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.naemo.contactmanager.BR
import com.naemo.contactmanager.R
import com.naemo.contactmanager.databinding.ActivityCardBinding
import com.naemo.contactmanager.ui.base.BaseActivity
import com.naemo.contactmanager.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_card.*
import java.sql.Date
import java.util.*
import javax.inject.Inject

 class CardActivity : BaseActivity<ActivityCardBinding, CardViewModel>(), CardNavigator, DatePickerDialog.OnDateSetListener {

     private var mBinder: ActivityCardBinding? = null
     var cardViewModel: CardViewModel? = null
     @Inject set

     var mLayoutId = R.layout.activity_card
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

     fun updateDob() {

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
       // Toast.makeText(this, "delete clicked", Toast.LENGTH_SHORT).show()
        val intent = intent
        val id = intent.getIntExtra("id", 0)
        val name = intent.getStringExtra("name")
        AlertDialog.Builder(this)
            .setTitle("Delete contact?")
            .setMessage("Are you sure you want to delete $name?")
            .setPositiveButton("Yes") { _, _ ->
                if (HomeActivity.dbhelper.deleteContact(id)) {
                    Toast.makeText(this, "contact deleted", Toast.LENGTH_SHORT).show()
                    super.onBackPressed()
                } else {
                    Toast.makeText(this, "error deleting", Toast.LENGTH_SHORT).show()
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
       // Toast.makeText(this, "edit clicked", Toast.LENGTH_SHORT).show()
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
         val isUpdate: Boolean = HomeActivity.dbhelper.updateContact(this,
             id.toString(),
             full_name.text.toString(),
             full_number.text.toString(),
             full_dob.text.toString(),
             full_address.text.toString(),
             full_zip_code.text.toString())

         if (isUpdate) {
             super.onBackPressed()
         } else {
             Toast.makeText(this, "error updating", Toast.LENGTH_SHORT).show()
         }

     }

     override fun onBackPressed() {

         if (clickedEdit!!) {
             showPopUp()
         } else {
             super.onBackPressed()
         }
     }

     fun updateDob(view: View) {
         clickedEdit = true
         showDatePickerDialog()
     }
 }
