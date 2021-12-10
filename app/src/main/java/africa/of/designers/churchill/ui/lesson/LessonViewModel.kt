package africa.of.designers.churchill.ui.lesson

import africa.of.designers.churchill.data.model.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LessonViewModel : ViewModel() {

    var lessonListener: LessonListener? = null

    private var categoryListData: MutableLiveData<CategoryList> = MutableLiveData()

    fun getChineseListDataObserver(): MutableLiveData<CategoryList> {
        return categoryListData
    }

    fun initializeCategory(jsonData: String?) {
        val gson = Gson()
        val listCategoryListType = object : TypeToken<List<Category>>() {}.type

        val listCategory: List<Category> = gson.fromJson(jsonData, listCategoryListType)

        categoryListData.postValue(CategoryList(listCategory))
    }

}
