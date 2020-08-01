package africa.of.designers.churchill.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import africa.of.designers.churchill.PracticeActivity;
import africa.of.designers.churchill.R;
import africa.of.designers.churchill.features.SplashScreenActivity;
import africa.of.designers.churchill.model.Slide;

public class SliderPagerAdapter extends PagerAdapter {

    private Context mContext ;
    private List<Slide> mList ;


    public SliderPagerAdapter(Context mContext, List<Slide> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, int position) {


        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout = inflater.inflate(R.layout.slide_item,null);

        ImageView slideImg = slideLayout.findViewById(R.id.slide_img);
        final TextView slideText = slideLayout.findViewById(R.id.slide_title);
        TextView slideDesc = slideLayout.findViewById(R.id.detail_movie_desc);
        Button buttonTxt = slideLayout.findViewById(R.id.button_enroll);
        slideImg.setImageResource(mList.get(position).getImage());
        slideText.setText(mList.get(position).getTitle());
        slideDesc.setText(mList.get(position).getCaption());
        buttonTxt.setText(mList.get(position).getButtonTxt());

        buttonTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (slideText.getText() == "CHINESE THROUGH GAMES"){
                    mContext.startActivity(new Intent(mContext, SplashScreenActivity.class));
                }else if(slideText.getText() == "ELEMENTARY CHINESE QUIZ") {
                    mContext.startActivity(new Intent(mContext, PracticeActivity.class));
                }else{
                    Toast.makeText(mContext, "Coming Soon...",Toast.LENGTH_SHORT).show();
                }
            }
        });

        container.addView(slideLayout);
        return slideLayout;

    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
