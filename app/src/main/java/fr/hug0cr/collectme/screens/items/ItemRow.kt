package fr.hug0cr.collectme.screens.items

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.hug0cr.collectme.common.composable.DropdownContextMenu
import fr.hug0cr.collectme.common.ext.contextMenu
import fr.hug0cr.collectme.model.Item

@Composable
@ExperimentalMaterialApi
fun ItemRow(
    item: Item,
    options: List<String>,
    onActionClick: (String) -> Unit,
) {
    Card(
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(modifier = Modifier.weight(1f).padding(4.dp)) {
                Text(text = item.name, style = MaterialTheme.typography.body1)
            }
            DropdownContextMenu(options, Modifier.contextMenu(), onActionClick)
        }
    }
}