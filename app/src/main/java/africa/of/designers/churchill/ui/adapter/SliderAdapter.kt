package africa.of.designers.churchill.ui.adapter

import africa.of.designers.churchill.R
import africa.of.designers.churchill.data.model.Slide
import africa.of.designers.churchill.ui.MainListener
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.slide_item.view.*

class SliderAdapter: PagerAdapter() {
    private var items = ArrayList<Slide>()
    private lateinit var mOnClickListener: MainListener

    fun setListData(context: Context, data: ArrayList<Slide>) {
        this.items = data
        mOnClickListener = context as MainListener
    }

    @SuppressLint("InflateParams")
    override fun instantiateItem(parent: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(parent.context) as LayoutInflater
        val view = inflater.inflate(R.layout.slide_item, null)

        val sliderImage: ImageView = view.slide_img
        val slideTitle: TextView = view.slide_title
        val slideCaption: TextView = view.slide_caption
        val buttonEnroll: Button = view.button_enroll

        slideTitle.text = items[position].title
        slideCaption.setText(items[position].caption)
        sliderImage.setImageResource(items[position].image)
        buttonEnroll.text = items[position].buttonTxt

        buttonEnroll.setOnClickListener {
            when(items[position].id) {
                1-> {
                    mOnClickListener.onGoToPractice()
                }
                2-> {
                    //do nothing
                }
                else-> {
                    mOnClickListener.onGoToComingSoon()
                }
            }
        }

        parent.addView(view)
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

