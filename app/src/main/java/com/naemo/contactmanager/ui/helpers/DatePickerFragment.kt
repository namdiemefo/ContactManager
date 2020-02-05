package com.naemo.contactmanager.ui.helpers

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment : DialogFragment() {

    var c: Calendar = Calendar.getInstance()
    var year: Int = c.get(Calendar.YEAR)
    var month: Int = c.get(Calendar.MONTH)
    var day: Int = c.get(Calendar.DAY_OF_MONTH)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let { DatePickerDialog(it, (activity as DatePickerDialog.OnDateSetListener), year, month, day) }!!
    }
}