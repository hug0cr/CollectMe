package fr.hug0cr.collectme.screens.settings

import dagger.hilt.android.lifecycle.HiltViewModel
import fr.hug0cr.collectme.SIGN_IN_SCREEN
import fr.hug0cr.collectme.SIGN_UP_SCREEN
import fr.hug0cr.collectme.SPLASH_SCREEN
import fr.hug0cr.collectme.model.service.AccountService
import fr.hug0cr.collectme.model.service.LogService
import fr.hug0cr.collectme.screens.CollectMeViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService
) : CollectMeViewModel(logService) {

    val uiState = accountService.currentUser.map {
        SettingsUiState(it.isAnonymous)
    }

    fun onSignInClick(openScreen: (String) -> Unit) = openScreen(SIGN_IN_SCREEN)

    fun onSignUpClick(openScreen: (String) -> Unit) = openScreen(SIGN_UP_SCREEN)

    fun onSignOutClick(restartApp: (String) -> Unit) {
        launchCatching {
            accountService.signOut()
            restartApp(SPLASH_SCREEN)
        }
    }
}