package fr.hug0cr.collectme.screens.items

enum class ItemActionOption(val title: String) {
    EditItem("Edit item"),
    DeleteItem("Delete item");

    companion object {
        fun getByTitle(title: String): ItemActionOption {
            values().forEach { action -> if (title == action.title) return action }
            return EditItem
        }

        fun getOptions(): List<String> {
            val options = mutableListOf<String>()
            values().forEach { itemAction ->
                options.add(itemAction.title)
            }
            return options
        }
    }
}