package africa.of.designers.churchill;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class LessonDetailActivity extends AppCompatActivity {

    private ImageView MovieThumbnailImg,MovieCoverImg;
    private TextView tv_title,tv_description;
    private FloatingActionButton play_fab;
    private String movieTitle;
    private ListView ActorsRV ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_movie_detail);
        // ini views
        iniViews();

        ActorsRV = (ListView) findViewById(R.id.Rv_actors);


        List<String> list = new ArrayList<>();

        if (movieTitle.equals("Alphabets")){
            list.add("$5 Coming soon");
        }else{
            list.add("Start Taking Lessons");
        }

        ActorsRV.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
        ActorsRV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    if(movieTitle.equals("Greetings/Conversations")) {
                        if (ParseUser.getCurrentUser() != null) {

                                startActivity(new Intent(LessonDetailActivity.this, GreetingsActivity.class));
                        }
                    }
                    if(movieTitle.equals("Family")) {
                        if (ParseUser.getCurrentUser() != null) {

                                startActivity(new Intent(LessonDetailActivity.this, FamilyActivity.class));
                        }
                    }
                    if(movieTitle.equals("Counting")) {
                        if (ParseUser.getCurrentUser() != null) {

                                startActivity(new Intent(LessonDetailActivity.this, CountingActivity.class));
                        }
                    }
                    if(movieTitle.equals("Days/Festive Periods/Seasons")) {
                        if (ParseUser.getCurrentUser() != null) {

                                startActivity(new Intent(LessonDetailActivity.this, SeasonsActivity.class));
                        }
                    }
                    if(movieTitle.equals("Calendar")) {
                        startActivity(new Intent(LessonDetailActivity.this, CalendarActivity.class));
                    }
                }
            }
        });

    }

    void iniViews() {

        movieTitle = getIntent().getExtras().getString("title");
        int imageResourceId = getIntent().getExtras().getInt("imgURL");
        int imagecover = getIntent().getExtras().getInt("imgCover");
        int desc = getIntent().getExtras().getInt("imgDesc");
        MovieThumbnailImg = findViewById(R.id.detail_movie_img);

        MovieThumbnailImg.setImageResource(imageResourceId);
        MovieCoverImg = findViewById(R.id.detail_movie_cover);

        tv_title = findViewById(R.id.detail_movie_title);
        MovieCoverImg.setImageResource(imagecover);
        try {
            tv_title.setText(movieTitle);
        }catch (Exception e19){
            e19.printStackTrace();
        }

        tv_description = findViewById(R.id.detail_movie_desc);
        tv_description.setText(desc);

    }


}
