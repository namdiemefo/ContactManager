package com.naemo.contactmanager.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.naemo.contactmanager.R
import kotlinx.android.synthetic.main.home_recycler.view.*




class HomeAdapter() : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {


    var recycle_name = arrayOf("Bill Kayden", "Bill Kayden", "Bill Kayden", "Bill Kayden", "Bill Kayden", "Bill Kayden")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.home_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return if (null == recycle_name) 0 else this.recycle_name.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewholder: ViewHolder = holder
       // val contactName: Contacts = contacts[position]
        val checkName = recycle_name[position]
       // var cName = contactName.name
        viewholder.name.text = checkName

    }

     class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.recycler_text

     }

}