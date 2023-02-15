package fr.hug0cr.collectme

import android.content.res.Resources
import androidx.compose.material.ScaffoldState
import androidx.navigation.NavHostController
import fr.hug0cr.collectme.common.snackbar.SnackbarManager
import fr.hug0cr.collectme.common.snackbar.SnackbarMessage.Companion.toMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class CollectMeAppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
    private val snackbarManager: SnackbarManager,
    private val resources: Resources,
    coroutineScope: CoroutineScope,
) {
    init {
        coroutineScope.launch {
            snackbarManager.snackbarMessages.filterNotNull().collect { snackbarMessage ->
                val text = snackbarMessage.toMessage(resources)
                scaffoldState.snackbarHostState.showSnackbar(text)
            }
        }
    }

    fun popUp() {
        navController.popBackStack()
    }

    fun navigate(route: String) {
        navController.navigate(route) { launchSingleTop = true }
    }

    fun navigateAndPopUp(route: String, popUp: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(popUp) { inclusive = true }
        }
    }

    fun clearAndNavigate(route: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(0) { inclusive = true }
        }
    }
}