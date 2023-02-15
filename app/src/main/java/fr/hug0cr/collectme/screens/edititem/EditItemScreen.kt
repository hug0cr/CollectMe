package fr.hug0cr.collectme.screens.edititem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import fr.hug0cr.collectme.common.composable.ActionToolbar
import fr.hug0cr.collectme.common.composable.BasicField
import fr.hug0cr.collectme.common.composable.CardSelector
import fr.hug0cr.collectme.common.ext.card
import fr.hug0cr.collectme.common.ext.fieldModifier
import fr.hug0cr.collectme.common.ext.spacer
import fr.hug0cr.collectme.common.ext.toolbarActions
import fr.hug0cr.collectme.model.Category
import fr.hug0cr.collectme.R.string as AppText
import fr.hug0cr.collectme.R.drawable as AppIcon

@ExperimentalMaterialApi
@Composable
fun EditItemScreen(
    modifier: Modifier = Modifier,
    viewModel: EditItemViewModel = hiltViewModel(),
) {
    val item by viewModel.item

    // TODO : Récupération d'un item

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ActionToolbar(
            title = AppText.edit_item,
            modifier = Modifier.toolbarActions(),
            endActionIcon = AppIcon.ic_check,
            endAction = { } // TODO : Gérer la persistance
        )
        Spacer(modifier = Modifier.spacer())
        val fieldModifier = Modifier.fieldModifier()
        BasicField(AppText.name, item.name, viewModel::onNameChange, fieldModifier)
        BasicField(AppText.description, item.description, viewModel::onDescriptionChange, fieldModifier)
        Spacer(modifier = Modifier.spacer())
        val categorySelection = Category.getByName(item.category).name
        CardSelector(AppText.category, Category.getOptions(), categorySelection, Modifier.card()) {
                newValue ->
            viewModel.onCategoryChange(newValue)
        }

    }
}