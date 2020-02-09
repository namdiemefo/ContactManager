package com.naemo.contactmanager.ui.card

import android.content.Context
import androidx.databinding.ObservableField
import com.naemo.contactmanager.ui.base.BaseViewModel
import com.naemo.contactmanager.ui.home.HomeActivity

class CardViewModel : BaseViewModel<CardNavigator>() {

    var fullname = ObservableField("")
    var phone = ObservableField("")
    var dob = ObservableField("")
    var address = ObservableField("")
    var zip = ObservableField("")

    fun update(context: Context, id: String): Boolean {
        return HomeActivity.dbhelper.updateContact(context,
            id, fullname.get().toString(), phone.get().toString(), dob.get().toString(),
            address.get().toString(), zip.get().toString())
    }
}