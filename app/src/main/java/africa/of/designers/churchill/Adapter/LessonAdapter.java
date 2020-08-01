package africa.of.designers.churchill.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import africa.of.designers.churchill.LessonDetailActivity;
import africa.of.designers.churchill.R;
import africa.of.designers.churchill.model.Lessons;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.MyViewHolder> {

    Context context ;
    List<Lessons> mData;


    public LessonAdapter(Context context, List<Lessons> mData) {
        this.context = context;
        this.mData = mData;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(context).inflate(R.layout.item_movie,viewGroup,false);
        return new MyViewHolder(view);


        }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.TvTitle.setText(mData.get(i).getTitle());
        myViewHolder.ImgCourse.setImageResource(mData.get(i).getThumbnail());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private TextView TvTitle;
        private ImageView ImgCourse, LockedImg;


        public MyViewHolder(@NonNull final View itemView) {

            super(itemView);
            TvTitle = itemView.findViewById(R.id.item_movie_title);
            ImgCourse = itemView.findViewById(R.id.item_movie_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Lessons mov = mData.get(getAdapterPosition());

                    context.startActivity(new Intent(context, LessonDetailActivity.class)
                            .putExtra("title", mov.getTitle())
                            .putExtra("imgURL", mov.getThumbnail())
                            .putExtra("imgCover", mov.getCoverPhoto())
                            .putExtra("imgDesc", mov.getDescription()));

                }
            });

        }
    }
}
