package africa.of.designers.churchill;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CalendarActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private int[] sounds;
    int token;
    private Button btnSkip, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_calendar);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnSkip = (Button) findViewById(R.id.btn_skip);
        btnNext = (Button) findViewById(R.id.btn_next);

        layouts = new int[]{
                R.layout.calendar_slide1,
                R.layout.calendar_slide2,
                R.layout.calendar_slide3,
                R.layout.calendar_slide4,
                R.layout.calendar_slide5,
                R.layout.calendar_slide6,
                R.layout.calendar_slide7,
                R.layout.calendar_slide8,
                R.layout.calendar_slide9,
                R.layout.calendar_slide10,
                R.layout.calendar_slide11,
                R.layout.calendar_slide12,
                R.layout.calendar_slide13,
                R.layout.calendar_slide14,
                R.layout.calendar_slide15,
                R.layout.calendar_slide16,
                R.layout.calendar_slide17,
                R.layout.calendar_slide18,
                R.layout.calendar_slide19,
                R.layout.calendar_slide20,
                R.layout.calendar_slide21,
                R.layout.calendar_slide22,
                R.layout.calendar_slide23,
                R.layout.calendar_slide24,
                R.layout.calendar_slide25,
                R.layout.calendar_slide26};

        sounds = new int[]{
                R.raw.calendar1,
                R.raw.calendar2,
                R.raw.calendar3,
                R.raw.calendar4,
                R.raw.calendar5,
                R.raw.calendar6,
                R.raw.calendar7,
                R.raw.calendar8,
                R.raw.calendar9,
                R.raw.calendar10,
                R.raw.calendar11,
                R.raw.calendar12,
                R.raw.calendar13,
                R.raw.calendar14,
                R.raw.calendar15,
                R.raw.calendar16,
                R.raw.calendar17,
                R.raw.calendar18,
                R.raw.calendar19,
                R.raw.calendar20,
                R.raw.calendar21,
                R.raw.calendar22,
                R.raw.calendar23,
                R.raw.calendar24,
                R.raw.calendar25,
                R.raw.calendar26};

        // adding bottom dots
        addBottomDots(0);

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CalendarActivity.this, HomeFragment.class));
                finish();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking for last page
                // if last page home screen will be launched
                int current = getItem(+1);
                if (current < layouts.length) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                    launchHomeScreen();
                }
            }
        });
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {

        startActivity(new Intent(CalendarActivity.this, HomeFragment.class));
        finish();
    }

    //	viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                btnNext.setVisibility(View.VISIBLE);
            } else {
                // still pages are left
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };



    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            ImageView playButton = (ImageView) view.findViewById(R.id.imageButton2);
            final MediaPlayer mp = MediaPlayer.create(CalendarActivity.this, sounds[position]);
            playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp.start();
                    mp.setVolume(1f, 1f);
                }
            });

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
