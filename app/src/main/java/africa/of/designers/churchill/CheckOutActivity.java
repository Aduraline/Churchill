package africa.of.designers.churchill;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;


import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import africa.of.designers.churchill.CCFragment.CCNameFragment;
import africa.of.designers.churchill.CCFragment.CCNumberFragment;
import africa.of.designers.churchill.CCFragment.CCSecureCodeFragment;
import africa.of.designers.churchill.CCFragment.CCValidityFragment;
import africa.of.designers.churchill.Utils.CreditCardUtils;
import africa.of.designers.churchill.Utils.ViewPagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import co.paystack.android.Paystack;
import co.paystack.android.PaystackSdk;
import co.paystack.android.Transaction;
import co.paystack.android.model.Card;
import co.paystack.android.model.Charge;

public class CheckOutActivity extends FragmentActivity implements FragmentManager.OnBackStackChangedListener {

    @BindView(R.id.btnNext)
    Button btnNext;

    public CardFrontFragment cardFrontFragment;
    public CardBackFragment cardBackFragment;

    //This is our viewPager
    private ViewPager viewPager;

    CCNumberFragment numberFragment;
    CCNameFragment nameFragment;
    CCValidityFragment validityFragment;
    CCSecureCodeFragment secureCodeFragment;

    int total_item;
    boolean backTrack = false;

    private boolean mShowingBack = false;

    String cardNumber, cardCVV, cardValidity, cardValidityYear, cardName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_check_out);

        ButterKnife.bind(this);


        cardFrontFragment = new CardFrontFragment();
        cardBackFragment = new CardBackFragment();

        if (savedInstanceState == null) {
            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, cardFrontFragment).commit();

        } else {
            mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
        }

        getFragmentManager().addOnBackStackChangedListener(this);

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(4);
        setupViewPager(viewPager);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == total_item)
                    btnNext.setText("SUBMIT");
                else
                    btnNext.setText("NEXT");

                Log.d("track", "onPageSelected: " + position);

                if (position == total_item) {
                    flipCard();
                    backTrack = true;
                } else if (position == total_item - 1 && backTrack) {
                    flipCard();
                    backTrack = false;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = viewPager.getCurrentItem();
                if (pos < total_item) {
                    viewPager.setCurrentItem(pos + 1);
                } else {
                    checkEntries();
                }

            }
        });


    }

    public void snack(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
                .show();
    }


    private String generateReference() {
        String keys = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(10);

        for (int i = 0; i < 10; i++) {
            int index = (int)(keys.length() * Math.random());
            sb.append(keys.charAt(index));
        }

        return sb.toString();
    }

    public void checkEntries() {
        cardName = nameFragment.getName();
        cardNumber = numberFragment.getCardNumber();
        cardValidity = validityFragment.getValidity();
        cardValidityYear = validityFragment.getValidityYear();
        cardCVV = secureCodeFragment.getValue();

        String customRef = generateReference();

        if (TextUtils.isEmpty(cardName)) {
            snack("Enter Valid Name");
        } else if (TextUtils.isEmpty(cardNumber) || !CreditCardUtils.isValid(cardNumber.replace(" ",""))) {
            snack("Enter Valid card number");
        } else if (TextUtils.isEmpty(cardValidity)||!CreditCardUtils.isValidDate(cardValidity)) {
            snack("Enter correct validity");
        } else if (TextUtils.isEmpty(cardCVV)||cardCVV.length()<3) {
            snack("Enter valid security number");
        } else {
            Card card = new Card(cardNumber, Integer.valueOf(cardValidity), Integer.valueOf(cardValidityYear), cardCVV);
            if (card.isValid()) {
                // charge card
                //create a Charge object
                Charge charge = new Charge();
                charge.setAmount(100000);
                charge.setReference(customRef);
                charge.setCurrency("NGN");
                charge.setCard(card); //sets the card to charge

                PaystackSdk.chargeCard(CheckOutActivity.this, charge, new Paystack.TransactionCallback() {
                    @Override
                    public void onSuccess(Transaction transaction) {
                        // This is called only after transaction is deemed successful.
                        // Retrieve the transaction, and send its reference to your server
                        // for verification.
                        if (ParseUser.getCurrentUser() != null) {
                            ParseUser.getCurrentUser().put("transRef", transaction.getReference());
                            ParseUser.getCurrentUser().put("plan", 1);
                            ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    snack("Transaction Successful");
                                }
                            });
                        }else{
                            snack("Invalid User!");
                        }

                        snack("Transaction Complete");
                    }

                    @Override
                    public void beforeValidate(Transaction transaction) {
                        // This is called only before requesting OTP.
                        // Save reference so you may send to server. If
                        // error occurs with OTP, you should still verify on server.
                    }

                    @Override
                    public void onError(Throwable error, Transaction transaction) {
                        //handle error here
                    }

                });
            } else {
                snack("Invalid Card");
            }
        }

    }

    @Override
    public void onBackStackChanged() {
        mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        numberFragment = new CCNumberFragment();
        nameFragment = new CCNameFragment();
        validityFragment = new CCValidityFragment();
        secureCodeFragment = new CCSecureCodeFragment();
        adapter.addFragment(numberFragment);
        adapter.addFragment(nameFragment);
        adapter.addFragment(validityFragment);
        adapter.addFragment(secureCodeFragment);

        total_item = adapter.getCount() - 1;
        viewPager.setAdapter(adapter);

    }

    private void flipCard() {
        if (mShowingBack) {
            getFragmentManager().popBackStack();
            return;
        }
        // Flip to the back.
        //setCustomAnimations(int enter, int exit, int popEnter, int popExit)

        mShowingBack = true;

        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out)
                .replace(R.id.fragment_container, cardBackFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        int pos = viewPager.getCurrentItem();
        if (pos > 0) {
            viewPager.setCurrentItem(pos - 1);
        } else
            super.onBackPressed();
    }

    public void nextClick() {
        btnNext.performClick();
    }
}
