package africa.of.designers.churchill.data.model

data class LessonList (val items: ArrayList<Lesson>)
data class Lesson(
    val id: Int,
    val title: String,
    val thumbnail: Int,
    val description: Int,
    val coverPhoto: Int,
)
