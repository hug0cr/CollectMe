package fr.hug0cr.collectme

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import fr.hug0cr.collectme.ui.theme.CollectMeTheme

@Composable
@ExperimentalMaterialApi
fun CollectMeApp() {
    CollectMeTheme {
        Surface(color = MaterialTheme.colors.background) {
            Text(text = "Hello CollectMeApp")
        }
    }
}
