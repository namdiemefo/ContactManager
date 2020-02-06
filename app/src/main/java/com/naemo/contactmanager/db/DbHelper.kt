package com.naemo.contactmanager.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.naemo.contactmanager.db.models.Contacts
import java.lang.Exception

class DbHelper(context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) :
SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION){


    companion object {
        private val DATABASE_NAME = "MyData.db"
        private val DATABASE_VERSION = 1

        const val CONTACTS_TABLE_NAME = "Contacts"
        const val COLUMN_CONTACT_ID = "contactid"
        const val COLUMN_CONTACT_FIRSTNAME = "contactfirstname"
        const val COLUMN_CONTACT_LASTNAME = "contactlastname"
        const val COLUMN_CONTACT_PHONENUMBER = "contactphonenumber"
        const val COLUMN_CONTACT_DOB = "contactdob"
        const val COLUMN_CONTACT_ADDRESS = "contactaddress"
        const val COLUMN_CONTACT_ZIPCODE = "contactzipcode"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE $CONTACTS_TABLE_NAME (" +
                "$COLUMN_CONTACT_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_CONTACT_FIRSTNAME TEXT," +
                "$COLUMN_CONTACT_LASTNAME TEXT," +
                "$COLUMN_CONTACT_PHONENUMBER TEXT," +
                "$COLUMN_CONTACT_DOB TEXT," +
                "$COLUMN_CONTACT_ADDRESS TEXT," +
                "$COLUMN_CONTACT_ZIPCODE TEXT)")

        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun getContacts(context: Context) : ArrayList<Contacts> {
        val query = "Select * From $CONTACTS_TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        val contacts = ArrayList<Contacts>()

        if (cursor.count == 0)
            Toast.makeText(context, "No records found", Toast.LENGTH_SHORT).show() else
        {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                val contact = Contacts()
                contact.contactId = cursor.getInt(cursor.getColumnIndex(COLUMN_CONTACT_ID))
                contact.conctactFirstName = cursor.getString(cursor.getColumnIndex(
                    COLUMN_CONTACT_FIRSTNAME))
                contact.contactLastName = cursor.getString(cursor.getColumnIndex(
                    COLUMN_CONTACT_LASTNAME))
                contact.contactPhoneNumber = cursor.getString(cursor.getColumnIndex(
                    COLUMN_CONTACT_PHONENUMBER))
                contact.contactDob = cursor.getString(cursor.getColumnIndex(
                    COLUMN_CONTACT_DOB))
                contact.contactAddress = cursor.getString(cursor.getColumnIndex(
                    COLUMN_CONTACT_ADDRESS))
                contact.contactZipcode = cursor.getString(cursor.getColumnIndex(
                    COLUMN_CONTACT_ZIPCODE))
                contacts.add(contact)
                cursor.moveToNext()
            }
           // Toast.makeText(context, "${cursor.count} records found", Toast.LENGTH_SHORT).show()
        }
        cursor.close()
        db.close()
        return contacts
    }

    fun addContact(context: Context, contacts: Contacts) {
        val values = ContentValues()
        values.put(COLUMN_CONTACT_FIRSTNAME, contacts.conctactFirstName)
        values.put(COLUMN_CONTACT_LASTNAME, contacts.contactLastName)
        values.put(COLUMN_CONTACT_PHONENUMBER, contacts.contactPhoneNumber)
        values.put(COLUMN_CONTACT_DOB, contacts.contactDob)
        values.put(COLUMN_CONTACT_ADDRESS, contacts.contactAddress)
        values.put(COLUMN_CONTACT_ZIPCODE, contacts.contactZipcode)
        val db = this.writableDatabase
        try {
            db.insert(CONTACTS_TABLE_NAME, null, values)
            Toast.makeText(context, "customer added", Toast.LENGTH_SHORT).show() //use snackbar
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
        db.close()
    }

    fun deleteContact(contactId: Int): Boolean {
        val query = "Delete From $CONTACTS_TABLE_NAME where $COLUMN_CONTACT_ID = $contactId"
        val db = this.writableDatabase
        var result = false
        try {
            val cursor = db.execSQL(query)
            result = true
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Error Deleting")
        }
        db.close()
        return result
    }
 }