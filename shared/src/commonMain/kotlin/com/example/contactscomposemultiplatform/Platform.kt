package com.example.contactscomposemultiplatform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform