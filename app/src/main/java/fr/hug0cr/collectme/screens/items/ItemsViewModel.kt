package fr.hug0cr.collectme.screens.items

import dagger.hilt.android.lifecycle.HiltViewModel
import fr.hug0cr.collectme.EDIT_ITEM_SCREEN
import fr.hug0cr.collectme.SETTINGS_SCREEN
import fr.hug0cr.collectme.model.service.LogService
import fr.hug0cr.collectme.model.service.StorageService
import fr.hug0cr.collectme.screens.CollectMeViewModel
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    storageService: StorageService,
    logService: LogService
) : CollectMeViewModel(logService) {

    val items = storageService.items

    fun onSettingsClick(openScreen: (String) -> Unit) = openScreen(SETTINGS_SCREEN)

    fun onAddClick(openScreen: (String) -> Unit) = openScreen(EDIT_ITEM_SCREEN)

}