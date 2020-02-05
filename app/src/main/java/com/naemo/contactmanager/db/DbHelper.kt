package com.naemo.contactmanager.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.naemo.contactmanager.db.models.Contacts

class DbHelper(context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) :
SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION){

    companion object {
        private val DATABASE_NAME = "MyData.db"
        private val DATABASE_VERSION = 1

        val CONTACTS_TABLE_NAME = "Contacts"
        val COLUMN_CONTACT_ID = "contactid"
        val COLUMN_CONTACT_FIRSTNAME = "contactfirstname"
        val COLUMN_CONTACT_LASTNAME = "contactlastname"
        val COLUMN_CONTACT_PHONENUMBER = "contactphonenumber"
        val COLUMN_CONTACT_DOB = "contactdob"
        val COLUMN_CONTACT_ADDRESS = "contactaddress"
        val COLUMN_CONTACT_ZIPCODE = "contactzipcode"
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
        { while (cursor.moveToNext()) {
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
            }
            Toast.makeText(context, "${cursor.count} records found", Toast.LENGTH_SHORT).show()
        }
        cursor.close()
        db.close()
        return contacts
    }
}