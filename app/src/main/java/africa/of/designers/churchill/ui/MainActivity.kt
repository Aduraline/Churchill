package africa.of.designers.churchill.ui

import africa.of.designers.churchill.R
import africa.of.designers.churchill.base.BaseActivity
import africa.of.designers.churchill.data.model.ExtrasInt
import africa.of.designers.churchill.data.model.ExtrasString
import africa.of.designers.churchill.data.model.Lesson
import africa.of.designers.churchill.databinding.ActivityMainBinding
import africa.of.designers.churchill.ui.adapter.LessonAdapter
import africa.of.designers.churchill.ui.adapter.SliderAdapter
import africa.of.designers.churchill.ui.lessondetail.LessonDetailActivity
import africa.of.designers.churchill.ui.practice.PracticeActivity
import africa.of.designers.churchill.util.snackbar
import africa.of.designers.churchill.util.startNewActivity
import africa.of.designers.churchill.util.startNewActivityWithExtra
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BaseActivity(), MainListener {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var freeLessonAdapter: LessonAdapter
    private lateinit var lessonAdapter: LessonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.mainviewmodel = viewModel
        viewModel.mainListener = this

        //init timer
        val timer = Timer()
        timer.scheduleAtFixedRate(SliderTimer(), 4000, 6000)

        initRecyclerView()
        loadSlider()
        loadRecyclerView()
    }

    private fun initRecyclerView() {
        rv_free_lesson.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL,false)
            freeLessonAdapter = LessonAdapter()
            adapter = freeLessonAdapter
        }

        rv_lesson.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL,false)
            lessonAdapter = LessonAdapter()
            adapter = lessonAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun loadSlider() {
        sliderAdapter = SliderAdapter()
        viewModel.getSlideListDataObserver().observe(this, {
            if (it != null) {
                sliderAdapter.setListData(this@MainActivity, it.items)
                sliderAdapter.notifyDataSetChanged()
                binding.sliderPager.adapter = sliderAdapter
                binding.indicator.setupWithViewPager(binding.sliderPager, true)
            }
        })
        viewModel.initializeSlider()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun loadRecyclerView() {
        viewModel.getFreeLessonListDataObserver().observe(this, {
            if(it!=null) {
                freeLessonAdapter.setListData(this@MainActivity, it.items)
                freeLessonAdapter.notifyDataSetChanged()
            }
        })
        viewModel.getLessonListDataObserver().observe(this, {
            if(it!=null) {
                lessonAdapter.setListData(this@MainActivity, it.items)
                lessonAdapter.notifyDataSetChanged()
            }
        })
        viewModel.initializeFreeLesson()
        viewModel.initializeLesson()
    }

    internal class SliderTimer : TimerTask() {
        override fun run() {}
    }

    override fun onGoToLessonDetail(data: Lesson) {
        val extraDataInt: ArrayList<ExtrasInt> = ArrayList()
        extraDataInt.add(ExtrasInt("lessonId", data.id))
        extraDataInt.add(ExtrasInt("imgURL", data.thumbnail))
        extraDataInt.add(ExtrasInt("imgCover", data.coverPhoto))
        extraDataInt.add(ExtrasInt("imgDesc", data.description))

        val extraDataString: ArrayList<ExtrasString> = ArrayList()
        extraDataString.add(ExtrasString("title", data.title))

        startNewActivityWithExtra(LessonDetailActivity::class.java, false, extraDataString, extraDataInt, null)
    }

    override fun onGoToPractice() {
        startNewActivity(PracticeActivity::class.java, false)
    }

    override fun onGoToComingSoon() {
        window.decorView.rootView.snackbar("Coming Soon...", null, this@MainActivity)
    }
}
