 package com.naemo.contactmanager.ui.card

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.naemo.contactmanager.R

class CardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)
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
