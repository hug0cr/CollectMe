package fr.hug0cr.collectme.screens.settings

import dagger.hilt.android.lifecycle.HiltViewModel
import fr.hug0cr.collectme.SIGN_IN_SCREEN
import fr.hug0cr.collectme.SIGN_UP_SCREEN
import fr.hug0cr.collectme.model.service.LogService
import fr.hug0cr.collectme.screens.CollectMeViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    logService: LogService
) : CollectMeViewModel(logService) {

    fun onSigInClick(openScreen: (String) -> Unit) = openScreen(SIGN_IN_SCREEN)
    fun onSignUpClick(openScreen: (String) -> Unit) = openScreen(SIGN_UP_SCREEN)
}