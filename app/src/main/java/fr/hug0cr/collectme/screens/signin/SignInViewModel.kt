package fr.hug0cr.collectme.screens.signin

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.hug0cr.collectme.SETTINGS_SCREEN
import fr.hug0cr.collectme.SIGN_IN_SCREEN
import fr.hug0cr.collectme.common.ext.isValidEmail
import fr.hug0cr.collectme.common.snackbar.SnackbarManager
import fr.hug0cr.collectme.model.service.AccountService
import fr.hug0cr.collectme.model.service.LogService
import fr.hug0cr.collectme.screens.CollectMeViewModel
import javax.inject.Inject
import fr.hug0cr.collectme.R.string as AppText

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService,
) : CollectMeViewModel(logService) {
    var uiState = mutableStateOf(SignInUiState())
        private set

    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onSignInClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        if (password.isBlank()) {
            SnackbarManager.showMessage(AppText.empty_password_error)
            return
        }

        launchCatching {
            accountService.authenticate(email, password)
            openAndPopUp(SETTINGS_SCREEN, SIGN_IN_SCREEN)
        }
    }

    fun onForgotPasswordClick() {
        if (!email.isValidEmail()) {
            SnackbarManager.showMessage(AppText.email_error)
            return
        }

        launchCatching {
            accountService.sendRecoveryEmail(email)
            SnackbarManager.showMessage(AppText.recovery_email_sent)
        }
    }
}