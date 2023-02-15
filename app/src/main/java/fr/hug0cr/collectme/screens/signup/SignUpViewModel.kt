package fr.hug0cr.collectme.screens.signup

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.hug0cr.collectme.screens.CollectMeViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
) : CollectMeViewModel() {
    var uiState = mutableStateOf(SignUpUiState())
        private set

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onRepeatPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(repeatPassword = newValue)
    }

    // TODO : Gérer le click d'inscription
}