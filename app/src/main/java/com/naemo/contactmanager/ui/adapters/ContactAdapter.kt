package com.naemo.contactmanager.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.naemo.contactmanager.R
import com.naemo.contactmanager.db.models.Contacts
import kotlinx.android.synthetic.main.home_recycler.view.*




class ContactAdapter(context: Context, val contacts: ArrayList<Contacts>) : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    val context = context

    var recycle_name = arrayOf("Bill Kayden", "Bill Kayden", "Bill Kayden", "Bill Kayden", "Bill Kayden", "Bill Kayden")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return contacts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewholder: ViewHolder = holder
        val contact: Contacts = contacts[position]
       // val checkName = recycle_name[position]
       // var cName = contactName.name
        val firstName = contact.conctactFirstName
        val lastName = contact.contactLastName
        viewholder.name.text = firstName.plus(" ").plus(lastName)

    }

     class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.recycler_text

     }

}