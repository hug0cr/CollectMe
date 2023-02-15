package fr.hug0cr.collectme

import android.content.res.Resources
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import fr.hug0cr.collectme.common.snackbar.SnackbarManager
import fr.hug0cr.collectme.screens.edititem.EditItemScreen
import fr.hug0cr.collectme.screens.items.ItemsScreen
import fr.hug0cr.collectme.screens.settings.SettingsScreen
import fr.hug0cr.collectme.screens.signin.SignInScreen
import fr.hug0cr.collectme.screens.signup.SignUpScreen
import fr.hug0cr.collectme.screens.splash.SplashScreen
import fr.hug0cr.collectme.ui.theme.CollectMeTheme
import kotlinx.coroutines.CoroutineScope

@Composable
@ExperimentalMaterialApi
fun CollectMeApp() {
    CollectMeTheme {
        Surface(color = MaterialTheme.colors.background) {
            val appState = rememberAppState()

            Scaffold(
                snackbarHost = {
                    SnackbarHost(
                        hostState = it,
                        modifier = Modifier.padding(8.dp),
                        snackbar = { snackbarData ->
                            Snackbar(snackbarData, contentColor = MaterialTheme.colors.onPrimary)
                        }
                    )
                },
                scaffoldState = appState.scaffoldState
            ) { innerPaddingModifier ->
                NavHost(
                    navController = appState.navController,
                    startDestination = SPLASH_SCREEN,
                    modifier = Modifier.padding(innerPaddingModifier)
                ) {
                    collectMeGraph(appState)
                }
            }
        }
    }
}

@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) =
    remember(scaffoldState, navController, snackbarManager, resources, coroutineScope) {
        CollectMeAppState(scaffoldState, navController, snackbarManager, resources, coroutineScope)
    }

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

@ExperimentalMaterialApi
fun NavGraphBuilder.collectMeGraph(appState: CollectMeAppState) {
    composable(SPLASH_SCREEN) {
        SplashScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(ITEMS_SCREEN) {
        ItemsScreen(openScreen = { route -> appState.navigate(route) })
    }

    composable(
        route = "$EDIT_ITEM_SCREEN$ITEM_ID_ARG",
        arguments = listOf(navArgument(ITEM_ID) { defaultValue = ITEM_DEFAULT_ID })
    ) {
        EditItemScreen(
            popUpScreen = { appState.popUp() },
            itemId = it.arguments?.getString(ITEM_ID) ?: ITEM_DEFAULT_ID
        )
    }

    composable(SETTINGS_SCREEN) {
        SettingsScreen(
            restartApp = { route -> appState.clearAndNavigate(route) },
            openScreen = { route -> appState.navigate(route) })
    }

    composable(SIGN_IN_SCREEN) {
        SignInScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(SIGN_UP_SCREEN) {
        SignUpScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }


}