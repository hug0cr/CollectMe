package fr.hug0cr.collectme.screens.edititem

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.hug0cr.collectme.model.Item
import fr.hug0cr.collectme.screens.CollectMeViewModel
import javax.inject.Inject

@HiltViewModel
class EditItemViewModel @Inject constructor(
) : CollectMeViewModel() {
    val item = mutableStateOf(Item())

    fun onNameChange(newValue: String) {
        item.value = item.value.copy(name = newValue)
    }

    fun onCategoryChange(newValue: String) {
        item.value = item.value.copy(category = newValue)
    }

    fun onDescriptionChange(newValue: String) {
        item.value = item.value.copy(description = newValue)
    }

}