package fr.hug0cr.collectme.model.service

import fr.hug0cr.collectme.model.Item
import kotlinx.coroutines.flow.Flow

interface StorageService {
    val items: Flow<List<Item>>

    suspend fun getItem(itemId: String): Item?

    suspend fun save(item: Item): String
    suspend fun update(item: Item)
    suspend fun delete(itemId: String)
}