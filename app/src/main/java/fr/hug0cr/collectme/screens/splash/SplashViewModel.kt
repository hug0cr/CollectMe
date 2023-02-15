package fr.hug0cr.collectme.screens.splash

import dagger.hilt.android.lifecycle.HiltViewModel
import fr.hug0cr.collectme.ITEMS_SCREEN
import fr.hug0cr.collectme.SPLASH_SCREEN
import fr.hug0cr.collectme.screens.CollectMeViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : CollectMeViewModel() {
    // TODO : gérer le lancement de l'application

    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        openAndPopUp(ITEMS_SCREEN, SPLASH_SCREEN)
    }
}