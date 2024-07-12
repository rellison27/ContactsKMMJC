package com.example.contactscomposemultiplatform.core.data

import com.example.contactscomposemultiplatform.database.ContactsDB
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory
{
    actual fun create(): SqlDriver
    {
         return NativeSqliteDriver(
             ContactsDB.Schema,
             "contact.db"
         )
    }
}