package fr.hug0cr.collectme.screens.items

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import fr.hug0cr.collectme.common.composable.ActionToolbar
import fr.hug0cr.collectme.common.ext.toolbarActions
import fr.hug0cr.collectme.R.string as AppText
import fr.hug0cr.collectme.R.drawable as AppIcon

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ItemsScreen(
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ItemsViewModel = hiltViewModel(),
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onAddClick(openScreen) }, // TODO : Pour ajouter des articles
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                modifier = modifier.padding(16.dp)
            ) {
                Icon(Icons.Filled.Add, "Add")
            }
        }
    )  {
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
            ActionToolbar(
                title = AppText.items,
                modifier = Modifier.toolbarActions(),
                endActionIcon = AppIcon.ic_settings,
                endAction = { viewModel.onSettingsClick(openScreen) }
            )

            // TODO : Afficher les items de l'utilisateur
        }
    }
}