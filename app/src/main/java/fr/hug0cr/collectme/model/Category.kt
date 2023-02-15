package fr.hug0cr.collectme.model

enum class Category {
    None,
    CD,
    Book,
    Game;

    companion object {
        fun getByName(name: String?): Category {
            values().forEach { category -> if (name == category.name) return category }
            return None
        }

        fun getOptions(): List<String> {
            val options = mutableListOf<String>()
            values().forEach { category -> options.add(category.name) }
            return options
        }
    }
}