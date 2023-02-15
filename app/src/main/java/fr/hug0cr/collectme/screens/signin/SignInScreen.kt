package fr.hug0cr.collectme.screens.signin

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
import fr.hug0cr.collectme.common.composable.*
import fr.hug0cr.collectme.common.ext.basicButton
import fr.hug0cr.collectme.common.ext.fieldModifier
import fr.hug0cr.collectme.common.ext.textButton
import fr.hug0cr.collectme.R.string as AppText

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState
    BasicToolbar(AppText.login_details)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailField(uiState.email, viewModel::onEmailChange, Modifier.fieldModifier())
        PasswordField(uiState.password, viewModel::onPasswordChange, Modifier.fieldModifier())
        BasicButton(AppText.sign_in, Modifier.basicButton()) {  } // TODO : Gérer la connexion
        BasicTextButton(AppText.forgot_password, Modifier.textButton()) {
//            viewModel.onForgotPasswordClick() // TODO : Gérer l'oubli de password
        }
    }
}