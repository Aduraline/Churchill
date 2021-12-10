package africa.of.designers.churchill.data.model

data class PracticeList (val items: List<Practice>)
data class Practice(
    val id: Int,
    val question: String,
    val type: Int,
    val options: ArrayList<Option>
)
data class Option(
    val isAnswer: Boolean,
    val title: String,
    val image: String
)
