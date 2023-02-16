package fr.hug0cr.collectme.model.service

import fr.hug0cr.collectme.model.User
import kotlinx.coroutines.flow.Flow

interface AccountService {
    val currentUserId: String
    val hasUser: Boolean

    val currentUser: Flow<User>

    suspend fun authenticate(email: String, password: String)
    suspend fun sendRecoveryEmail(email: String)
    suspend fun createAnonymousAccount()
    suspend fun linkAccount(email: String, password: String)
    suspend fun deleteAccount()
    suspend fun signOut()

    suspend fun changePassword(password: String)
}