package com.example.contactscomposemultiplatform.contacts.data

import com.example.contactscomposemultiplatform.contacts.domain.Contact
import database.ContactEntity

fun ContactEntity.toContact(): Contact {
     return Contact(
         id = id,
         firstName = firstName,
         lastName = lastName,
         email = email,
         phoneNumber = phoneNumber,
         photo = null // TODO get actual image
     )
}