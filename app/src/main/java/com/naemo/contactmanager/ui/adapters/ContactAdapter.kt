package com.naemo.contactmanager.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.naemo.contactmanager.R
import com.naemo.contactmanager.db.models.Contacts
import kotlinx.android.synthetic.main.home_recycler.view.*




class ContactAdapter(context: Context, private val contacts: ArrayList<Contacts>, var itemClickListener: ItemClickListener) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    val context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return contacts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact: Contacts = contacts[position]
        val id = contact.contactId
        val firstName = contact.conctactFirstName
        val lastName = contact.contactLastName
        val phoneNumber =  contact.contactPhoneNumber
        val dob = contact.contactDob
        val address = contact.contactAddress
        val zipcode = contact.contactZipcode
        holder.name.text = firstName.plus(" ").plus(lastName)
        holder.view.setOnClickListener {itemClickListener.onItemClicked(id, firstName.plus(" ").plus(lastName),
            phoneNumber, dob, address, zipcode) }

    }

     class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.recycler_text
         val view: LinearLayout = itemView.recycler_frame
     }

    interface ItemClickListener {
        fun onItemClicked(id: Int, name: String, phone: String, dob: String, address: String, zipcode: String)
    }

}