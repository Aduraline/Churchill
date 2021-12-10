package africa.of.designers.churchill.ui

import africa.of.designers.churchill.R
import africa.of.designers.churchill.data.model.Lesson
import africa.of.designers.churchill.data.model.LessonList
import africa.of.designers.churchill.data.model.Slide
import africa.of.designers.churchill.data.model.SlideList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.ArrayList

class MainViewModel : ViewModel() {

    var mainListener: MainListener? = null

    private var slideListData: MutableLiveData<SlideList> = MutableLiveData()
    private var freeLessonListData: MutableLiveData<LessonList> = MutableLiveData()
    private var lessonListData: MutableLiveData<LessonList> = MutableLiveData()

    fun getSlideListDataObserver(): MutableLiveData<SlideList> {
        return slideListData
    }
    fun getFreeLessonListDataObserver(): MutableLiveData<LessonList> {
        return freeLessonListData
    }
    fun getLessonListDataObserver(): MutableLiveData<LessonList> {
        return lessonListData
    }

    fun initializeSlider() {
        val list: ArrayList<Slide> = ArrayList<Slide>()
        list.add(Slide(1, R.drawable.chinese_list,"ELEMENTARY CHINESE QUIZ", R.string.main_text, "TRY QUIZ"))
        list.add(Slide(2, R.drawable.thegame,"CHINESE THROUGH GAMES", R.string.main_text, "PLAY GAME"))
        list.add(Slide(3, R.drawable.chicharacters,"CHINESE CHARACTERS", R.string.main_text, "COMING SOON"))
        list.add(Slide(4, R.drawable.gameboard,"EDUCATIONAL CHINESE GAMES", R.string.main_text, "COMING SOON"))
        list.add(Slide(5, R.drawable.articles,"INTERACTIVE LEARNING", R.string.main_text, "COMING SOON"))
        slideListData.postValue(SlideList(list))
    }

    fun initializeLesson() {
        val list: ArrayList<Lesson> = ArrayList<Lesson>()
        list.add(Lesson(2, "Greetings/Conversations", R.drawable.nxt,R.string.main_text ,R.drawable.chinese_list))
        list.add(Lesson(3, "Family",R.drawable.brothers,R.string.main_text ,R.drawable.chinese_list))
        list.add(Lesson(4, "Counting",R.drawable.counting,R.string.main_text ,R.drawable.chinese_list))
        list.add(Lesson(5, "Days/Festive Periods/Seasons",R.drawable.adorable,R.string.main_text ,R.drawable.chinese_list))
        //list.add(Lesson("Alphabets",R.drawable.literature,R.string.main_text ,R.drawable.chinese_list))
        lessonListData.postValue(LessonList(list))
    }

    fun initializeFreeLesson() {
        val list: ArrayList<Lesson> = ArrayList<Lesson>()
        list.add(Lesson(1, "Calendar",R.drawable.calendar,R.string.main_text ,R.drawable.chinese_list))
        freeLessonListData.postValue(LessonList(list))
    }

}
