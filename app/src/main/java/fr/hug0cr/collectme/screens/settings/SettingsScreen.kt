package fr.hug0cr.collectme.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import fr.hug0cr.collectme.common.composable.BasicToolbar
import fr.hug0cr.collectme.common.composable.DialogCancelButton
import fr.hug0cr.collectme.common.composable.DialogConfirmButton
import fr.hug0cr.collectme.common.composable.RegularCardEditor
import fr.hug0cr.collectme.common.ext.card
import fr.hug0cr.collectme.common.ext.spacer
import fr.hug0cr.collectme.R.drawable as AppIcon
import fr.hug0cr.collectme.R.string as AppText

@ExperimentalMaterialApi
@Composable
fun SettingsScreen(
    restartApp: (String) -> Unit,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState(
        initial = SettingsUiState(false)
    )
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicToolbar(AppText.settings)

        Spacer(modifier = Modifier.spacer())

        if (uiState.isAnonymousAccount) {
            RegularCardEditor(AppText.sign_in, AppIcon.ic_sign_in, "", Modifier.card()) {
                viewModel.onSignInClick(openScreen)
            }

            RegularCardEditor(
                AppText.create_account,
                AppIcon.ic_create_account,
                "",
                Modifier.card()
            ) {
                viewModel.onSignUpClick(openScreen)
            }
        } else {
            SignOutCard { viewModel.onSignOutClick(restartApp) }
            // TODO : Supression de compte
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun SignOutCard(signOut: () -> Unit) {
    var showWarningDialog by remember { mutableStateOf(false) }

    RegularCardEditor(AppText.sign_out, AppIcon.ic_exit, "", Modifier.card()) {
        showWarningDialog = true
    }

    if (showWarningDialog) {
        AlertDialog(
            title = { Text(stringResource(AppText.sign_out_title)) },
            text = { Text(stringResource(AppText.sign_out_description)) },
            dismissButton = { DialogCancelButton(AppText.cancel) { showWarningDialog = false } },
            confirmButton = {
                DialogConfirmButton(AppText.sign_out) {
                    signOut()
                    showWarningDialog = false
                }
            },
            onDismissRequest = { showWarningDialog = false }
        )
    }
}