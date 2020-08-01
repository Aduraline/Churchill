package africa.of.designers.churchill;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import javax.inject.Inject;

import africa.of.designers.churchill.features.SoundPlayer;
import butterknife.ButterKnife;

public class PracticeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private PracticeActivity.MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    int token;
    private Button btnSkip, btnNext;

    @Inject
    SoundPlayer mSoundPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_practice);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnSkip = (Button) findViewById(R.id.btn_skip);
        btnNext = (Button) findViewById(R.id.btn_next);

        ButterKnife.bind(this);
        ((WordSearchApp) getApplication()).getAppComponent().inject(this);


        layouts = new int[]{
                R.layout.practice_greeting_slide1,
                R.layout.practice_greeting_slide2,
                R.layout.practice_greeting_slide3,
                R.layout.practice_greeting_slide4,
                R.layout.practice_greeting_slide5,
                R.layout.practice_greeting_slide6,
                R.layout.practice_greeting_slide7,
                R.layout.practice_greeting_slide8,
                R.layout.practice_greeting_slide9,
                R.layout.practice_greeting_slide10};

        // adding bottom dots
        addBottomDots(0);

        myViewPagerAdapter = new PracticeActivity.MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PracticeActivity.this, HomeFragment.class));
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

        startActivity(new Intent(PracticeActivity.this, HomeFragment.class));
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

            ImageView options = (ImageView) view.findViewById(R.id.answer);
            Button btnoptions = (Button) view.findViewById(R.id.answerbtn);

            Button btnoptions1 = (Button) view.findViewById(R.id.button);
            Button btnoptions2 = (Button) view.findViewById(R.id.button2);
            Button btnoptions3 = (Button) view.findViewById(R.id.button3);

            ImageView options1 = (ImageView) view.findViewById(R.id.imaging);
            ImageView options2 = (ImageView) view.findViewById(R.id.imaging2);
            ImageView options3 = (ImageView) view.findViewById(R.id.imaging3);

            try {
                options.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snack("Correct!!!");
                        mSoundPlayer.play(SoundPlayer.Sound.Correct);

                        int current = getItem(+1);
                        if (current < layouts.length) {
                            // move to next screen
                            viewPager.setCurrentItem(current);
                        } else {
                            launchHomeScreen();
                        }
                    }
                });
            }catch (Exception e1){

            }

            try {
                btnoptions.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snack("Correct!!!");
                        mSoundPlayer.play(SoundPlayer.Sound.Correct);

                        int current = getItem(+1);
                        if (current < layouts.length) {
                            // move to next screen
                            viewPager.setCurrentItem(current);
                        } else {
                            launchHomeScreen();
                        }
                    }
                });
            }catch (Exception e){

            }

            try {
                options1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackFailed("Incorrect.");
                        mSoundPlayer.play(SoundPlayer.Sound.Wrong);
                    }
                });
            }catch (Exception e){

            }

            try {
                options2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackFailed("Incorrect.");
                        mSoundPlayer.play(SoundPlayer.Sound.Wrong);
                    }
                });
            }catch (Exception e){

            }

            try {
                options3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackFailed("Incorrect.");
                        mSoundPlayer.play(SoundPlayer.Sound.Wrong);
                    }
                });
            }catch (Exception e){

            }

            try {
                btnoptions1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackFailed("Incorrect.");
                        mSoundPlayer.play(SoundPlayer.Sound.Wrong);
                    }
                });
            }catch (Exception e){

            }

            try {
                btnoptions2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackFailed("Incorrect.");
                        mSoundPlayer.play(SoundPlayer.Sound.Wrong);
                    }
                });
            }catch (Exception e){

            }

            try {
                btnoptions3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackFailed("Incorrect.");
                        mSoundPlayer.play(SoundPlayer.Sound.Wrong);
                    }
                });
            }catch (Exception e){

            }

            return view;
        }

        public void snack(String message) {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
            snackbar.getView().setBackgroundColor(ContextCompat.getColor(PracticeActivity.this, R.color.CTA_bg));
            snackbar.show();
        }

        public void snackFailed(String message) {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
            snackbar.getView().setBackgroundColor(ContextCompat.getColor(PracticeActivity.this, R.color.colorPrimary));
            snackbar.show();
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
