package africa.of.designers.churchill;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Splashscreen extends AppCompatActivity {

    TextView gotoCreateAcct, forgotPassword;
    Button login;
    EditText username, password;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.layout_login);

        gotoCreateAcct = (TextView)findViewById(R.id.textView2);
        forgotPassword = (TextView)findViewById(R.id.forgot);
        username = (EditText)findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        progressDialog = new ProgressDialog(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(username.getText()) && !TextUtils.isEmpty(password.getText())) {
                    progressDialog.setMessage("Login in progress");
                    progressDialog.show();

                    ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (e == null) {
                                progressDialog.dismiss();
                                startActivity(new Intent(Splashscreen.this, HomeFragment.class));
                                finish();
                            } else {
                                progressDialog.dismiss();
                                snack("Login Unsuccessful");
                            }
                        }
                    });
                }
            }
        });

        gotoCreateAcct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Splashscreen.this, CreateAccount.class)); finish();
            }
        });

    }

    public void snack(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
                .show();
    }

}
