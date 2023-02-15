package fr.hug0cr.collectme.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import fr.hug0cr.collectme.common.composable.BasicToolbar
import fr.hug0cr.collectme.common.composable.RegularCardEditor
import fr.hug0cr.collectme.common.ext.card
import fr.hug0cr.collectme.common.ext.spacer
import fr.hug0cr.collectme.R.drawable as AppIcon
import fr.hug0cr.collectme.R.string as AppText

@ExperimentalMaterialApi
@Composable
fun SettingsScreen(
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier.fillMaxWidth().fillMaxHeight().verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicToolbar(AppText.settings)
        Spacer(modifier = Modifier.spacer())
        // TODO : Afficher ces cartes si l'utilisateur n'est pas connecté
        RegularCardEditor(AppText.sign_in, AppIcon.ic_sign_in, "", Modifier.card()) {
            viewModel.onSigInClick(openScreen)
        }
        RegularCardEditor(AppText.create_account, AppIcon.ic_create_account, "", Modifier.card()) {
            viewModel.onSignUpClick(openScreen)
        }
    }
}