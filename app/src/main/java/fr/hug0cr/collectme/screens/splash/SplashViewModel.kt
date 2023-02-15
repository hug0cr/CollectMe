package fr.hug0cr.collectme.screens.splash

import dagger.hilt.android.lifecycle.HiltViewModel
import fr.hug0cr.collectme.screens.CollectMeViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : CollectMeViewModel() {

    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        // TODO : Mettre en place le lancement de l'écran suivant le chargement de l'app
//        openAndPopUp(ITEMS_SCREEN, SPLASH_SCREEN)
    }
}