package fr.hug0cr.collectme.screens.items

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.hug0cr.collectme.EDIT_ITEM_SCREEN
import fr.hug0cr.collectme.ITEM_ID
import fr.hug0cr.collectme.SETTINGS_SCREEN
import fr.hug0cr.collectme.model.Item
import fr.hug0cr.collectme.model.service.LogService
import fr.hug0cr.collectme.model.service.StorageService
import fr.hug0cr.collectme.screens.CollectMeViewModel
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val storageService: StorageService,
    logService: LogService
) : CollectMeViewModel(logService) {

    val items = storageService.items
    val options = mutableStateOf<List<String>>(listOf())

    fun loadTaskOptions() {
        options.value = ItemActionOption.getOptions()
    }

    fun onSettingsClick(openScreen: (String) -> Unit) = openScreen(SETTINGS_SCREEN)

    fun onAddClick(openScreen: (String) -> Unit) = openScreen(EDIT_ITEM_SCREEN)

    fun onTaskActionClick(openScreen: (String) -> Unit, item: Item, action: String) {
        when (ItemActionOption.getByTitle(action)) {
            ItemActionOption.EditItem -> openScreen("$EDIT_ITEM_SCREEN?$ITEM_ID={${item.id}}")
            ItemActionOption.DeleteItem -> onDeleteTaskClick(item)
        }
    }

    private fun onDeleteTaskClick(item: Item) {
        launchCatching { storageService.delete(item.id) }
    }

}