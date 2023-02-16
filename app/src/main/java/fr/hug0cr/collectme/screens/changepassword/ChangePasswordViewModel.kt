package fr.hug0cr.collectme.screens.changepassword

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.hug0cr.collectme.CHANGE_PASSWORD_SCREEN
import fr.hug0cr.collectme.R
import fr.hug0cr.collectme.SETTINGS_SCREEN
import fr.hug0cr.collectme.common.ext.isValidPassword
import fr.hug0cr.collectme.common.ext.passwordMatches
import fr.hug0cr.collectme.common.snackbar.SnackbarManager
import fr.hug0cr.collectme.model.service.AccountService
import fr.hug0cr.collectme.model.service.LogService
import fr.hug0cr.collectme.screens.CollectMeViewModel
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService
) : CollectMeViewModel(logService) {
    var uiState = mutableStateOf(ChangePasswordUiState())
        private set

    private val password
        get() = uiState.value.password

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onRepeatPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(repeatPassword = newValue)
    }

    fun onChangePasswordClick(openAndPopUp: (String, String) -> Unit) {
        if (!password.isValidPassword()) {
            SnackbarManager.showMessage(R.string.password_error)
            return
        }

        if (!password.passwordMatches(uiState.value.repeatPassword)) {
            SnackbarManager.showMessage(R.string.password_match_error)
            return
        }

        launchCatching {
            accountService.changePassword(password)
            openAndPopUp(SETTINGS_SCREEN, CHANGE_PASSWORD_SCREEN)
            SnackbarManager.showMessage(R.string.password_updated)
        }
    }
}