package africa.of.designers.churchill.ui.lessondetail

import africa.of.designers.churchill.*
import africa.of.designers.churchill.base.BaseActivity
import africa.of.designers.churchill.data.model.ExtrasInt
import africa.of.designers.churchill.databinding.ActivityLessonDetailBinding
import africa.of.designers.churchill.ui.lesson.LessonActivity
import africa.of.designers.churchill.util.startNewActivityWithExtra
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_lesson_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class LessonDetailActivity : BaseActivity(), LessonDetailListener {
    private var imageRes: Int? = null
    private var imageCoverRes: Int? = null
    private var desc: Int? = null
    private var lessonId: Int? = null
    private var lessonTitle: String? = null

    lateinit var binding : ActivityLessonDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        binding = DataBindingUtil.setContentView(this, R.layout.activity_lesson_detail)
        val viewModel = ViewModelProvider(this)[LessonDetailViewModel::class.java]
        binding.lessonviewmodel = viewModel
        viewModel.lessonListener = this

        iniViews()
        initializeList()
    }

    private fun iniViews() {
        lessonTitle = intent.extras!!.getString("title")
        lessonId = intent.extras!!.getInt("lessonId")
        imageRes = intent.extras!!.getInt("imgURL")
        imageCoverRes = intent.extras!!.getInt("imgCover")
        desc = intent.extras!!.getInt("imgDesc")

        binding.lessonImg.setImageResource(imageRes!!)
        binding.lessonCoverImg.setImageResource(imageCoverRes!!)
        binding.lessonTitle.text = lessonTitle
        binding.lessonDesc.setText(desc!!)
    }

    private fun initializeList() {
        val list: MutableList<String> = ArrayList()
        list.add("Start Taking Lessons")

        type_list.apply {
            adapter = ArrayAdapter(this@LessonDetailActivity, android.R.layout.simple_list_item_1, list)
            onItemClickListener = OnItemClickListener { _, _, position, _ ->
                if (position == 0) {
                    val extraData: ArrayList<ExtrasInt> = ArrayList()
                    extraData.add(ExtrasInt("id", lessonId!!))
                    startNewActivityWithExtra(LessonActivity::class.java, false, null, extraData, null)
                }
            }
        }
    }
}
