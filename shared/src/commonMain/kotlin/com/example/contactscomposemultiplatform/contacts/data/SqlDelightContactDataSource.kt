package com.example.contactscomposemultiplatform.contacts.data

import com.example.contactscomposemultiplatform.contacts.domain.Contact
import com.example.contactscomposemultiplatform.contacts.domain.ContactDataSource
import com.example.contactscomposemultiplatform.core.data.ImageStorage
import com.example.contactscomposemultiplatform.database.ContactsDB
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.supervisorScope
import kotlinx.datetime.Clock

class SqlDelightContactDataSource(
    db: ContactsDB,
    private val imageStorage: ImageStorage
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
                supervisorScope {
                    contactEntities
                        .map {
                            async {
                                it.toContact(imageStorage)

                            }
                        }
                        .map { it.await() }
                }
            }
    }

    override fun getRecentContacts(amount: Int): Flow<List<Contact>>
    {
        return queries
            .getRecentContacts(amount.toLong())
            .asFlow()
            .mapToList()
            .map { contactEntities ->
                supervisorScope {
                    contactEntities
                        .map {
                            async {
                                it.toContact(imageStorage)

                            }
                        }
                        .map { it.await() }
                }
            }
    }

    override suspend fun insertContact(contact: Contact)
    {
        val imagePath = contact.photo?.let {
            imageStorage.saveImage(it)
        }
        queries.insertContactEntity(
            id = contact.id?.toLong(),
            firstName = contact.firstName,
            lastName = contact.lastName,
            phoneNumber = contact.phoneNumber,
            email = contact.email,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            imagePath = imagePath
        )
    }

    override suspend fun deleteContact(contactId: Long)
    {
        val entity = queries.getContactById(contactId).executeAsOne()
        entity.imagePath?.let {
            imageStorage.deleteImage(it)
        }
        queries.deleteContactEntity(contactId)
    }

}