package fr.hug0cr.collectme.screens.splash

import dagger.hilt.android.lifecycle.HiltViewModel
import fr.hug0cr.collectme.SIGN_IN_SCREEN
import fr.hug0cr.collectme.SPLASH_SCREEN
import fr.hug0cr.collectme.screens.CollectMeViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : CollectMeViewModel() {

    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        openAndPopUp(SIGN_IN_SCREEN, SPLASH_SCREEN)
    }
}