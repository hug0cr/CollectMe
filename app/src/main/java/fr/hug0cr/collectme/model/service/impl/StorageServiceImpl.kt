package fr.hug0cr.collectme.model.service.impl

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import fr.hug0cr.collectme.model.Item
import fr.hug0cr.collectme.model.service.AccountService
import fr.hug0cr.collectme.model.service.StorageService
import fr.hug0cr.collectme.model.service.trace
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: AccountService
) : StorageService {

    @OptIn(ExperimentalCoroutinesApi::class)
    override val items: Flow<List<Item>>
        get() =
            auth.currentUser.flatMapLatest { user ->
                currentCollection(user.id)
                    .snapshots()
                    .map { snapshot -> snapshot.toObjects() }
            }

    override suspend fun getItem(itemId: String): Item? =
        currentCollection(auth.currentUserId).document(itemId).get().await().toObject()

    override suspend fun save(item: Item): String =
        trace(SAVE_ITEM_TRACE) { currentCollection(auth.currentUserId).add(item).await().id }

    override suspend fun update(item: Item): Unit =
        trace(UPDATE_ITEM_TRACE) {
            currentCollection(auth.currentUserId).document(item.id).set(item).await()
        }

    override suspend fun delete(itemId: String) {
        currentCollection(auth.currentUserId).document(itemId).delete().await()
    }

    private fun currentCollection(uid: String): CollectionReference =
        firestore.collection(USER_COLLECTION).document(uid).collection(TASK_COLLECTION)

    companion object {
        private const val USER_COLLECTION = "users"
        private const val TASK_COLLECTION = "items"
        private const val SAVE_ITEM_TRACE = "saveItem"
        private const val UPDATE_ITEM_TRACE = "updateItem"
    }
}