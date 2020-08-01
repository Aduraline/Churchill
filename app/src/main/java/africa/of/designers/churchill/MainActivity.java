package africa.of.designers.churchill;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import africa.of.designers.churchill.Adapter.LessonAdapter;
import africa.of.designers.churchill.Adapter.SliderPagerAdapter;
import africa.of.designers.churchill.model.Lessons;
import africa.of.designers.churchill.model.Slide;

public class MainActivity extends Fragment {

    private List<Slide> lstSlides ;
    private ViewPager sliderpager;
    private TabLayout indicator;
    private RecyclerView MoviesRV, MoviesRVWeek ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        View x =  inflater.inflate(R.layout.activity_main,null);

        iniViews(x);
        iniSlider();
        iniMovies();
        iniMoviesWeek();

        return x;

    }

    private void iniViews(View x) {
        sliderpager = x.findViewById(R.id.slider_pager) ;
        indicator = x.findViewById(R.id.indicator);
        MoviesRV = x.findViewById(R.id.Rv_movies);

        MoviesRVWeek = x.findViewById(R.id.Rv_movies_week);
    }

    private void iniSlider() {
        // prepare a list of slides ..
        lstSlides = new ArrayList<>() ;
        lstSlides.add(new Slide(R.drawable.chinese_list,"ELEMENTARY CHINESE QUIZ", R.string.main_text, "TRY QUIZ"));
        lstSlides.add(new Slide(R.drawable.thegame,"CHINESE THROUGH GAMES", R.string.main_text, "PLAY GAME"));
        lstSlides.add(new Slide(R.drawable.chicharacters,"CHINESE CHARACTERS", R.string.main_text, "COMING SOON"));
        lstSlides.add(new Slide(R.drawable.gameboard,"EDUCATIONAL CHINESE GAMES", R.string.main_text, "COMING SOON"));
        lstSlides.add(new Slide(R.drawable.articles,"INTERACTIVE LEARNING", R.string.main_text, "COMING SOON"));


        SliderPagerAdapter adapter = new SliderPagerAdapter(getActivity(),lstSlides);
        sliderpager.setAdapter(adapter);
        // setup timer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(),4000,6000);
        indicator.setupWithViewPager(sliderpager,true);
    }

    private void iniMoviesWeek() {
        List<Lessons> lstMoviesWeek = new ArrayList<>();
        lstMoviesWeek.add(new Lessons("Greetings/Conversations", R.drawable.nxt,R.string.main_text ,R.drawable.chinese_list));
        lstMoviesWeek.add(new Lessons("Family",R.drawable.brothers,R.string.main_text ,R.drawable.chinese_list));
        lstMoviesWeek.add(new Lessons("Counting",R.drawable.counting,R.string.main_text ,R.drawable.chinese_list));
        lstMoviesWeek.add(new Lessons("Days/Festive Periods/Seasons",R.drawable.adorable,R.string.main_text ,R.drawable.chinese_list));
        lstMoviesWeek.add(new Lessons("Alphabets",R.drawable.literature,R.string.main_text ,R.drawable.chinese_list));


        LessonAdapter lessonAdapterWeek = new LessonAdapter(getActivity(),lstMoviesWeek);
        MoviesRVWeek.setAdapter(lessonAdapterWeek);
        MoviesRVWeek.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
    }

    private void iniMovies() {
        List<Lessons> lstMovies = new ArrayList<>();
        lstMovies.add(new Lessons("Calendar",R.drawable.calendar,R.string.main_text ,R.drawable.chinese_list));

        LessonAdapter lessonAdapter = new LessonAdapter(getActivity(),lstMovies);
        MoviesRV.setAdapter(lessonAdapter);
        MoviesRV.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
    }

    class SliderTimer extends TimerTask {


        @Override
        public void run() {



        }
    }

}
