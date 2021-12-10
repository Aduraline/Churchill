package africa.of.designers.churchill.ui.adapter

import africa.of.designers.churchill.R
import africa.of.designers.churchill.data.model.Lesson
import africa.of.designers.churchill.ui.MainListener
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_lesson.view.*
import kotlinx.android.synthetic.main.slide_item.view.*

class LessonAdapter: RecyclerView.Adapter<LessonAdapter.MyHolder>() {
    private var items = ArrayList<Lesson>()
    private lateinit var mOnClickListener: MainListener

    fun setListData(context: Context, data: ArrayList<Lesson>) {
        this.items = data
        mOnClickListener = context as MainListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_lesson, parent, false)

        return MyHolder(inflater, items, mOnClickListener)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MyHolder(view: View, items: ArrayList<Lesson>, mOnClickListener: MainListener): RecyclerView.ViewHolder(view) {
        private val lessonImage: ImageView = view.item_lesson_img
        private val lessonTitle: TextView = view.item_lesson_title

        init {
            view.setOnClickListener {
                mOnClickListener.onGoToLessonDetail(items[adapterPosition])
            }
        }

        fun bind(data: Lesson) {
            lessonTitle.text = data.title
            lessonImage.setImageResource(data.thumbnail)
        }
    }

}
