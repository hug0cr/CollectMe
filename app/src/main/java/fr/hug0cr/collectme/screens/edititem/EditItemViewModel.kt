package fr.hug0cr.collectme.screens.edititem

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.hug0cr.collectme.ITEM_DEFAULT_ID
import fr.hug0cr.collectme.common.ext.idFromParameter
import fr.hug0cr.collectme.model.Item
import fr.hug0cr.collectme.model.service.LogService
import fr.hug0cr.collectme.model.service.StorageService
import fr.hug0cr.collectme.screens.CollectMeViewModel
import javax.inject.Inject

@HiltViewModel
class EditItemViewModel @Inject constructor(
    private val storageService: StorageService,
    logService: LogService
) : CollectMeViewModel(logService) {

    val item = mutableStateOf(Item())

    fun initialize(itemId: String) {
        launchCatching {
            if (itemId != ITEM_DEFAULT_ID) {
                item.value = storageService.getItem(itemId.idFromParameter()) ?: Item()
            }
        }
    }

    fun onNameChange(newValue: String) {
        item.value = item.value.copy(name = newValue)
    }

    fun onCategoryChange(newValue: String) {
        item.value = item.value.copy(category = newValue)
    }

    fun onDescriptionChange(newValue: String) {
        item.value = item.value.copy(description = newValue)
    }

    fun onDoneClick(popUpScreen: () -> Unit) {
        launchCatching {
            val editedTask = item.value
            if (editedTask.id.isBlank()) {
                storageService.save(editedTask)
            } else {
                storageService.update(editedTask)
            }
            popUpScreen()
        }
    }

}