package com.example.contactscomposemultiplatform.core.data

import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory
{
    fun create(): SqlDriver
}