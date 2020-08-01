package africa.of.designers.churchill.CCFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;


import africa.of.designers.churchill.CardFrontFragment;
import africa.of.designers.churchill.CheckOutActivity;
import africa.of.designers.churchill.R;
import africa.of.designers.churchill.Utils.CreditCardEditText;
import africa.of.designers.churchill.Utils.CreditCardExpiryTextWatcher;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CCValidityFragment extends Fragment {


    @BindView(R.id.et_validity)
    CreditCardEditText et_validity;
    @BindView(R.id.et_validity_year)
    CreditCardEditText et_validity_year;
    TextView tv_validity;

    CheckOutActivity activity;
    CardFrontFragment cardFrontFragment;

    public CCValidityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ccvalidity, container, false);
        ButterKnife.bind(this, view);

        activity = (CheckOutActivity) getActivity();
        cardFrontFragment = activity.cardFrontFragment;


        tv_validity = cardFrontFragment.getValidity();
        et_validity.addTextChangedListener(new CreditCardExpiryTextWatcher(et_validity, tv_validity));

        et_validity.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    if (activity != null) {
                        activity.nextClick();
                        return true;
                    }

                }
                return false;
            }
        });

        et_validity.setOnBackButtonListener(new CreditCardEditText.BackButtonListener() {
            @Override
            public void onBackClick() {
                if(activity!=null)
                    activity.onBackPressed();
            }
        });


        return view;
    }

    public String getValidity()
    {
        if(et_validity!=null)
            return et_validity.getText().toString().trim();

        return null;
    }

    public String getValidityYear()
    {
        if(et_validity_year!=null)
            return et_validity_year.getText().toString().trim();

        return null;
    }

}
