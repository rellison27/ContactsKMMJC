package com.example.contactscomposemultiplatform.contacts.data

import com.example.contactscomposemultiplatform.contacts.domain.Contact
import com.example.contactscomposemultiplatform.contacts.domain.ContactDataSource
import com.example.contactscomposemultiplatform.database.ContactsDB
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock

class SqlDelightContactDataSource(
    db: ContactsDB
): ContactDataSource
{
    private val queries = db.contactQueries
    override fun getContacts(): Flow<List<Contact>>
    {
        return queries
            .getContacts()
            .asFlow()
            .mapToList()
            .map { contactEntities ->
                contactEntities.map { it.toContact() }
            }
    }

    override fun getRecentContacts(amount: Int): Flow<List<Contact>>
    {
        return queries
            .getRecentContacts(amount.toLong())
            .asFlow()
            .mapToList()
            .map { contactEntities ->
                contactEntities.map { it.toContact() }
            }
    }

    override suspend fun insertContact(contact: Contact)
    {
        queries.insertContactEntity(
            id = contact.id?.toLong(),
            firstName = contact.firstName,
            lastName = contact.lastName,
            phoneNumber = contact.phoneNumber,
            email = contact.email,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            imagePath = null
        )
    }

    override suspend fun deleteContact(contactId: Long)
    {
        queries.deleteContactEntity(contactId)
    }

}