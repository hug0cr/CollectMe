package fr.hug0cr.collectme.screens.settings

import dagger.hilt.android.lifecycle.HiltViewModel
import fr.hug0cr.collectme.SIGN_UP_SCREEN
import fr.hug0cr.collectme.screens.CollectMeViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor() : CollectMeViewModel() {

    fun onSignUpClick(openScreen: (String) -> Unit) = openScreen(SIGN_UP_SCREEN)
}