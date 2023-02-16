package fr.hug0cr.collectme.screens.changepassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import fr.hug0cr.collectme.common.composable.BasicButton
import fr.hug0cr.collectme.common.composable.BasicToolbar
import fr.hug0cr.collectme.common.composable.PasswordField
import fr.hug0cr.collectme.common.composable.RepeatPasswordField
import fr.hug0cr.collectme.common.ext.basicButton
import fr.hug0cr.collectme.common.ext.fieldModifier
import fr.hug0cr.collectme.R.string as AppText

@Composable
fun ChangePasswordScreen(
    openAndPopUp: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ChangePasswordViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState
    val fieldModifier = Modifier.fieldModifier()

    BasicToolbar(AppText.change_password)
    Column(
        modifier = modifier.fillMaxWidth().fillMaxHeight().verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PasswordField(uiState.password, viewModel::onPasswordChange, fieldModifier)
        RepeatPasswordField(uiState.repeatPassword, viewModel::onRepeatPasswordChange, fieldModifier)

        BasicButton(AppText.change_password, Modifier.basicButton()) {
            viewModel.onChangePasswordClick(openAndPopUp)
        }
    }
}