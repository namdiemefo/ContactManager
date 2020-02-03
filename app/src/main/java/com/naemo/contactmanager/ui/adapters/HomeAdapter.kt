package com.naemo.contactmanager.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.naemo.contactmanager.R
import com.naemo.contactmanager.data.models.Contacts
import kotlinx.android.synthetic.main.home_recycler.view.*

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    val contacts : ArrayList<Contacts>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.home_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return if (null == contacts) 0 else this.contacts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

     class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val name = itemView.recycler_text

     }

}