package africa.of.designers.churchill.data.model

data class CategoryList(
    val items: List<Category>
)

data class Category(
    val id: Int,
    val items: ChineseList
)

data class ChineseList(
    val items: ArrayList<Chinese>
)

data class Chinese(
    val chinesetxt: String,
    val translation: String,
    val audio: Int,
)

