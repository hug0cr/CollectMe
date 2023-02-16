package fr.hug0cr.collectme.screens.items

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.LocalPizza
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import fr.hug0cr.collectme.common.composable.ActionToolbar
import fr.hug0cr.collectme.common.ext.smallSpacer
import fr.hug0cr.collectme.common.ext.toolbarActions
import fr.hug0cr.collectme.model.Item
import fr.hug0cr.collectme.R.drawable as AppIcon
import fr.hug0cr.collectme.R.string as AppText

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@ExperimentalMaterialApi
fun ItemsScreen(
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ItemsViewModel = hiltViewModel(),
) {
    val userItems = viewModel.items.collectAsStateWithLifecycle(emptyList())
    val options by viewModel.options
    var comparator by remember { mutableStateOf(NameComparator) }

    LaunchedEffect(viewModel) { viewModel.loadTaskOptions() }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onAddClick(openScreen) },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary,
                modifier = modifier.padding(16.dp)
            ) {
                Icon(Icons.Filled.Add, "Add")
            }
        },
        bottomBar = {
            if (userItems.value.isNotEmpty()) {
                val darkTheme = isSystemInDarkTheme()
                BottomAppBar(
                    backgroundColor = if (darkTheme) MaterialTheme.colors.secondary
                    else MaterialTheme.colors.primaryVariant
                ) {
                    Spacer(Modifier.weight(0.4f, true))
                    IconButton(onClick = { comparator = NameComparator }) {
                        Icon(Icons.Filled.LocalPizza, contentDescription = "Sort by name")
                    }
                    Spacer(Modifier.weight(0.2f, true))
                    IconButton(
                        onClick = { comparator = CategoryComparator }
                    ) {
                        Icon(Icons.Filled.Coffee, contentDescription = "Sort by category")
                    }
                    Spacer(Modifier.weight(0.4f, true))
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            ActionToolbar(
                title = AppText.items,
                modifier = Modifier.toolbarActions(),
                endActionIcon = AppIcon.ic_settings,
                endAction = { viewModel.onSettingsClick(openScreen) }
            )
            Spacer(modifier = Modifier.smallSpacer())
            LazyColumn {
                val sortedList = userItems.value.sortedWith(comparator)
                items(sortedList, key = { it.id }) { userItem ->
                    ItemRow(
                        item = userItem,
                        options = options,
                        onActionClick = { action ->
                            viewModel.onTaskActionClick(
                                openScreen,
                                userItem,
                                action
                            )
                        }
                    )
                }
            }
        }
    }
}

private val NameComparator = Comparator<Item> { left, right ->
    left.name.compareTo(right.name)
}

private val CategoryComparator = Comparator<Item> { left, right ->
    left.category.compareTo(right.category)
}