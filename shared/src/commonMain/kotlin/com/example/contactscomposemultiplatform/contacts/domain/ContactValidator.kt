package com.example.contactscomposemultiplatform.contacts.domain

object ContactValidator
{
    data class ValidationResult(
        val firstNameError: String? = null,
        val lastNameError: String? = null,
        val emailError: String? = null,
        val phoneNumberError: String? = null
    )
    fun validateContact(contact: Contact): ValidationResult {
        var result = ValidationResult()

        if(contact.firstName.isBlank()) {
            result = result.copy(firstNameError = "Fill out the First name is field")
        }
        if(contact.lastName.isBlank()) {
            result = result.copy(lastNameError = "Fill out the Last name is field")
        }

        val emailRegex = Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$") // TODO fix this
        if(!emailRegex.matches(contact.email)) {
            result = result.copy(emailError = "The email is not valid")
        }
        if(contact.phoneNumber.isBlank()) {
            result = result.copy(phoneNumberError = "Fill out the Phone number is field")
        }

        return result
    }


}