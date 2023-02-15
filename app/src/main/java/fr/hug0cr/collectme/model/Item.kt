package fr.hug0cr.collectme.model

import com.google.firebase.firestore.DocumentId

data class Item(
    @DocumentId val id: String = "",
    val name: String = "",
    val category: String = "",
    val creationDate: String = "",
    val description: String = "",
)
