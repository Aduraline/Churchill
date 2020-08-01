package africa.of.designers.churchill;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseUser;

public class ProfileActivity extends Fragment{

    ImageView dp;
    TextView username;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        View x =  inflater.inflate(R.layout.activity_profile,null);

        username = (TextView)x.findViewById(R.id.userprofilename);
        dp = (ImageView)x.findViewById(R.id.profilepicture);

        if (ParseUser.getCurrentUser() != null){
            username.setText("@" + ParseUser.getCurrentUser().getUsername());
        }else{
            username.setText("@Anonymous");
        }


        return x;

    }


}
