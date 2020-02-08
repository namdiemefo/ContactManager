package com.naemo.contactmanager.ui.add

import android.content.Context
import android.text.TextUtils
import androidx.databinding.ObservableField
import com.naemo.contactmanager.db.models.Contacts
import com.naemo.contactmanager.ui.base.BaseViewModel
import com.naemo.contactmanager.ui.home.HomeActivity

class AddViewModel : BaseViewModel<AddNavigator>() {

    var firstName = ObservableField("")
    var lastName = ObservableField("")
    var phoneNumber = ObservableField("")
    var dob = ObservableField("")
    var address = ObservableField("")
    var zipCode = ObservableField("")

    fun add(context: Context) {
        val fName: String? = firstName.get()
        val lName: String? = lastName.get()
        val pNumber: String? = phoneNumber.get()
        val mAddress: String? = address.get()
        val dOb: String? = dob.get()
        val zip: String? = zipCode.get()

        if (TextUtils.isEmpty(fName) && TextUtils.isEmpty(lName)) {
            getNavigator()?.showToast("name fields cannot be empty")
        } else {
            val contacts = Contacts()
            contacts.conctactName = fName.plus(" ").plus(lName)
            contacts.contactPhoneNumber = pNumber.toString()
            contacts.contactAddress = mAddress.toString()
            contacts.contactDob = dOb.toString()
            contacts.contactZipcode = zip.toString()
            HomeActivity.dbhelper.addContact(context, contacts)
        }
    }

}

