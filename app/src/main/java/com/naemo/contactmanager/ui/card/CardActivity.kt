 package com.naemo.contactmanager.ui.card

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.naemo.contactmanager.R
import kotlinx.android.synthetic.main.activity_card.*

 class CardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)
        initializeViews()
    }

    private fun initializeViews() {
        val intent = intent
        val name = intent.getStringExtra("name")
        val phone = intent.getStringExtra("phone")
        val dob = intent.getStringExtra("dob")
        val address = intent.getStringExtra("address")
        val zipcode = intent.getStringExtra("zipcode")

        full_name.text = name
        full_number.text = phone
        full_dob.text = dob
        full_address.text = address
        full_zip_code.text = zipcode
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
        Toast.makeText(this, "delete clicked", Toast.LENGTH_SHORT).show()
    }

    private fun editContact() {
        Toast.makeText(this, "edit clicked", Toast.LENGTH_SHORT).show()
    }
}
