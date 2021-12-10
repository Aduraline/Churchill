package africa.of.designers.churchill.ui.practice

import africa.of.designers.churchill.R
import africa.of.designers.churchill.base.BaseActivity
import africa.of.designers.churchill.data.model.Practice
import africa.of.designers.churchill.databinding.ActivityPracticeBinding
import africa.of.designers.churchill.ui.MainActivity
import africa.of.designers.churchill.util.getJsonDataFromAsset
import africa.of.designers.churchill.util.snackbar
import africa.of.designers.churchill.util.startNewActivity
import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.PagerAdapter

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.lesson_row.view.*
import kotlinx.android.synthetic.main.practice_item_row.*
import kotlinx.android.synthetic.main.practice_item_row.view.*
import kotlinx.android.synthetic.main.slide_item.view.*
import java.util.*

class PracticeActivity : BaseActivity(), PracticeListener {
    private lateinit var viewModel: PracticeViewModel
    private lateinit var binding: ActivityPracticeBinding

    private lateinit var myViewPagerAdapter: MyViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_practice)
        viewModel = ViewModelProvider(this)[PracticeViewModel::class.java]
        binding.practiceviewmodel = viewModel
        viewModel.practiceListener = this

        binding.btnSkip.setOnClickListener {
            launchHomeScreen()
        }

        loadSlider()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun loadSlider() {
        myViewPagerAdapter = MyViewPagerAdapter()
        viewModel.getPracticeListDataObserver().observe(this, { data ->
            showLoadingDialog("Loading content...")
            if (data != null && data.items.isNotEmpty()) {
                dialog.dismiss()
                myViewPagerAdapter.setListData(this@PracticeActivity, data.items)
                binding.viewPager.adapter = myViewPagerAdapter

                binding.btnNext.setOnClickListener {
                    updatePagerView(data.items.size)
                }
            } else {
                dialog.dismiss()
            }
        })

        val jsonData = getJsonDataFromAsset(this@PracticeActivity, "json/dummy_practice.json")
        viewModel.initializePractice(jsonData)
    }

    private fun updatePagerView(itemSize: Int) {
        val current = getItem(+1)
        if (current < itemSize) {
            binding.viewPager.currentItem = current
        } else {
            launchHomeScreen()
        }
    }

    override fun onAnswerCorrect(itemSize: Int) {
        window.decorView.rootView.snackbar("Correct!", null, this@PracticeActivity)

        val mp = MediaPlayer.create(this@PracticeActivity,  R.raw.correct)
        mp.start()
        mp.setVolume(1f, 1f)

        updatePagerView(itemSize)
    }

    override fun onAnswerWrong() {
        window.decorView.rootView.snackbar("Incorrect", 1, this@PracticeActivity)

        val mp = MediaPlayer.create(this@PracticeActivity,  R.raw.wrong)
        mp.start()
        mp.setVolume(1f, 1f)
    }

    private fun getItem(i: Int): Int {
        return binding.viewPager.currentItem + i
    }

    private fun launchHomeScreen() {
        startNewActivity(MainActivity::class.java, true)
    }

    inner class MyViewPagerAdapter : PagerAdapter() {
        private var items = listOf<Practice>()
        private lateinit var mOnClickListener: PracticeListener

        fun setListData(context: Context, data: List<Practice>) {
            this.items = data
            mOnClickListener = context as PracticeListener
        }

        @SuppressLint("InflateParams", "SetTextI18n")
        override fun instantiateItem(parent: ViewGroup, position: Int): Any {
            val inflater = LayoutInflater.from(parent.context) as LayoutInflater
            val view = inflater.inflate(R.layout.practice_item_row, null)

            parent.addView(view)

            val practiceData: Practice = items[position]

            val buttonOption1: Button = view.option1
            val buttonOption2: Button = view.option2
            val buttonOption3: Button = view.option3

            val imageOption1: LinearLayout = view.imageOption1
            val imageOption2: LinearLayout = view.imageOption2
            val imageOption3: LinearLayout = view.imageOption3
            val imageOption4: LinearLayout = view.imageOption4

            val imageOptionLayout: LinearLayout = view.imageOptionLayout
            val buttonOptionLayout: LinearLayout = view.btnOptionLayout

            val image1: ImageView = view.optionimg1
            val image2: ImageView = view.optionimg2
            val image3: ImageView = view.optionimg3
            val image4: ImageView = view.optionimg4

            val txt1: TextView = view.optiontxt1
            val txt2: TextView = view.optiontxt2
            val txt3: TextView = view.optiontxt3
            val txt4: TextView = view.optiontxt4

            val questiontxt: TextView = view.question_txt
            questiontxt.text = "Translate " + practiceData.question

            if (practiceData.type == 0) {
                imageOptionLayout.visibility = GONE
                buttonOptionLayout.visibility = VISIBLE

                buttonOption1.text = practiceData.options[0].title
                buttonOption2.text = practiceData.options[1].title
                buttonOption3.text = practiceData.options[2].title
            } else {
                buttonOptionLayout.visibility = GONE
                imageOptionLayout.visibility = VISIBLE

                txt1.text = practiceData.options[0].title
                txt2.text = practiceData.options[1].title
                txt3.text = practiceData.options[2].title
                txt4.text = practiceData.options[3].title

                image1.setImageResource(
                    parent.context.resources.getIdentifier(
                        practiceData.options[0].image,
                        "drawable",
                        parent.context.packageName
                    )
                )
                image2.setImageResource(
                    parent.context.resources.getIdentifier(
                        practiceData.options[1].image,
                        "drawable",
                        parent.context.packageName
                    )
                )
                image3.setImageResource(
                    parent.context.resources.getIdentifier(
                        practiceData.options[2].image,
                        "drawable",
                        parent.context.packageName
                    )
                )
                image4.setImageResource(
                    parent.context.resources.getIdentifier(
                        practiceData.options[3].image,
                        "drawable",
                        parent.context.packageName
                    )
                )
            }

            buttonOption1.setOnClickListener {
                if (practiceData.options[0].isAnswer) {
                    mOnClickListener.onAnswerCorrect(items.size)
                } else {
                    mOnClickListener.onAnswerWrong()
                }
            }
            buttonOption2.setOnClickListener {
                if (practiceData.options[1].isAnswer) {
                    mOnClickListener.onAnswerCorrect(items.size)
                } else {
                    mOnClickListener.onAnswerWrong()
                }
            }
            buttonOption3.setOnClickListener {
                if (practiceData.options[2].isAnswer) {
                    mOnClickListener.onAnswerCorrect(items.size)
                } else {
                    mOnClickListener.onAnswerWrong()
                }
            }
            imageOption1.setOnClickListener {
                if (practiceData.options[0].isAnswer) {
                    mOnClickListener.onAnswerCorrect(items.size)
                } else {
                    mOnClickListener.onAnswerWrong()
                }
            }
            imageOption2.setOnClickListener {
                if (practiceData.options[1].isAnswer) {
                    mOnClickListener.onAnswerCorrect(items.size)
                } else {
                    mOnClickListener.onAnswerWrong()
                }
            }
            imageOption3.setOnClickListener {
                if (practiceData.options[2].isAnswer) {
                    mOnClickListener.onAnswerCorrect(items.size)
                } else {
                    mOnClickListener.onAnswerWrong()
                }
            }
            imageOption4.setOnClickListener {
                if (practiceData.options[3].isAnswer) {
                    mOnClickListener.onAnswerCorrect(items.size)
                } else {
                    mOnClickListener.onAnswerWrong()
                }
            }

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
