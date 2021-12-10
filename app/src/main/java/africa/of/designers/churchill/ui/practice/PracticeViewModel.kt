package africa.of.designers.churchill.ui.practice

import africa.of.designers.churchill.data.model.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PracticeViewModel : ViewModel() {

    var practiceListener: PracticeListener? = null

    private var practicecListData: MutableLiveData<PracticeList> = MutableLiveData()

    fun getPracticeListDataObserver(): MutableLiveData<PracticeList> {
        return practicecListData
    }

    fun initializePractice(jsonData: String?) {
        val gson = Gson()
        val listPracticeListType = object : TypeToken<List<Practice>>() {}.type

        val listPractice: List<Practice> = gson.fromJson(jsonData, listPracticeListType)

        practicecListData.postValue(PracticeList(listPractice))
    }
}
