package fr.hug0cr.collectme.screens.splash

import androidx.compose.runtime.mutableStateOf
import com.google.firebase.auth.FirebaseAuthException
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.hug0cr.collectme.ITEMS_SCREEN
import fr.hug0cr.collectme.SPLASH_SCREEN
import fr.hug0cr.collectme.model.service.AccountService
import fr.hug0cr.collectme.model.service.LogService
import fr.hug0cr.collectme.screens.CollectMeViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService
) : CollectMeViewModel(logService) {
    val showError = mutableStateOf(false)

    init {
        launchCatching {} // Récupération de la configuration
    }

    fun onAppStart(openAndPopUp: (String, String) -> Unit) {

        showError.value = false
        if (accountService.hasUser) openAndPopUp(ITEMS_SCREEN, SPLASH_SCREEN)
        else createAnonymousAccount(openAndPopUp)
    }

    private fun createAnonymousAccount(openAndPopUp: (String, String) -> Unit) {
        launchCatching(snackbar = false) {
            try {
                accountService.createAnonymousAccount()
            } catch (ex: FirebaseAuthException) {
                showError.value = true
                throw ex
            }
            openAndPopUp(ITEMS_SCREEN, SPLASH_SCREEN)
        }
    }
}