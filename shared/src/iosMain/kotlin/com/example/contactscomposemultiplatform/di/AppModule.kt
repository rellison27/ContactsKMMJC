package com.example.contactscomposemultiplatform.di

import com.example.contactscomposemultiplatform.contacts.data.SqlDelightContactDataSource
import com.example.contactscomposemultiplatform.contacts.domain.ContactDataSource
import com.example.contactscomposemultiplatform.core.data.DatabaseDriverFactory
import com.example.contactscomposemultiplatform.core.data.ImageStorage
import com.example.contactscomposemultiplatform.database.ContactsDB

actual class AppModule
{
    actual val contactDataSource: ContactDataSource by lazy {
        SqlDelightContactDataSource(
            db = ContactsDB(
                driver = DatabaseDriverFactory( ).create()
            ),
            imageStorage = ImageStorage()
        )
    }
}