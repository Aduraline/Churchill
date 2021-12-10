package africa.of.designers.churchill.ui.lesson

import africa.of.designers.churchill.R
import africa.of.designers.churchill.base.BaseActivity
import africa.of.designers.churchill.data.model.Chinese
import africa.of.designers.churchill.databinding.ActivityLessonBinding
import africa.of.designers.churchill.ui.MainActivity
import africa.of.designers.churchill.util.getJsonDataFromAsset
import africa.of.designers.churchill.util.startNewActivity
import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.PagerAdapter

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.lesson_row.view.*
import kotlinx.android.synthetic.main.slide_item.view.*
import java.util.*

class LessonActivity : BaseActivity(), LessonListener {
    private lateinit var viewModel: LessonViewModel
    private lateinit var binding: ActivityLessonBinding

    private var lessonId: Int? = null

    private lateinit var myViewPagerAdapter: MyViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_lesson)
        viewModel = ViewModelProvider(this)[LessonViewModel::class.java]
        binding.lessonviewmodel = viewModel
        viewModel.lessonListener = this

        binding.btnSkip.setOnClickListener {
            launchHomeScreen()
        }

        getIntentExtras()
        loadSlider()
    }

    private fun getIntentExtras() {
        lessonId = intent.extras!!.getInt("id")
    }

    @SuppressLint("NotifyDataSetChanged")
    fun loadSlider() {
        myViewPagerAdapter = MyViewPagerAdapter()
        viewModel.getChineseListDataObserver().observe(this, { data ->
            showLoadingDialog("Loading content...")
            if (data != null && data.items.isNotEmpty()) {
                dialog.dismiss()
                myViewPagerAdapter.setListData(
                    this@LessonActivity,
                    data.items.first { it.id == lessonId }.items.items
                )
                binding.viewPager.adapter = myViewPagerAdapter

                binding.btnNext.setOnClickListener {
                    val current = getItem(+1)
                    if (current < data.items.first { it.id == lessonId }.items.items.size) {
                        binding.viewPager.currentItem = current
                    } else {
                        launchHomeScreen()
                    }
                }
            } else {
                dialog.dismiss()
                launchHomeScreen()
            }
        })

        val jsonData = getJsonDataFromAsset(this@LessonActivity, "json/dummy_data.json")
        viewModel.initializeCategory(jsonData)
    }

    private fun getItem(i: Int): Int {
        return binding.viewPager.currentItem + i
    }

    private fun launchHomeScreen() {
        startNewActivity(MainActivity::class.java, true)
    }

    inner class MyViewPagerAdapter : PagerAdapter() {
        private var items = ArrayList<Chinese>()
        private lateinit var mOnClickListener: LessonListener

        fun setListData(context: Context, data: ArrayList<Chinese>) {
            this.items = data
            mOnClickListener = context as LessonListener
        }

        @SuppressLint("InflateParams")
        override fun instantiateItem(parent: ViewGroup, position: Int): Any {
            val inflater = LayoutInflater.from(parent.context) as LayoutInflater
            val view = inflater.inflate(R.layout.lesson_row, null)

            parent.addView(view)

            val playButton: ImageView = view.play_btn
            val chineseTxt: TextView = view.chinese_txt
            val englishTxt: TextView = view.english_txt

            val mp = MediaPlayer.create(parent.context, items[position].audio)
            playButton.setOnClickListener {
                mp.start()
                mp.setVolume(1f, 1f)
            }

            chineseTxt.text = items[position].chinesetxt
            englishTxt.text = items[position].translation

            return view
        }

        override fun getCount(): Int {
            return items.size
        }

        override fun isViewFromObject(view: View, o: Any): Boolean {
            return view === o
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }
    }
}
