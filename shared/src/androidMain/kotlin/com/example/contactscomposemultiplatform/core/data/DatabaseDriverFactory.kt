package com.example.contactscomposemultiplatform.core.data

import android.content.Context
import com.example.contactscomposemultiplatform.database.ContactsDB
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(
    private val context: Context
)
{
    actual fun create(): SqlDriver
    {
        return AndroidSqliteDriver(
            ContactsDB.Schema,
            context,
           "contact.db"
        )
    }
}