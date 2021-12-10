package africa.of.designers.churchill.data.model

data class SlideList (val items: ArrayList<Slide>)
data class Slide(
    val id: Int,
    val image: Int,
    val title: String,
    val caption: Int,
    val buttonTxt: String
)
