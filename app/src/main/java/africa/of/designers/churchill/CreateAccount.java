//package africa.of.designers.churchill;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.Log;
//import android.util.Patterns;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import com.parse.ParseException;
//import com.parse.ParseUser;
//import com.parse.SignUpCallback;
//
//public class CreateAccount extends AppCompatActivity {
//
//    TextView gotoCreateAcct;
//    Button createAcc;
//    EditText username, email, password;
//    ProgressDialog progressDialog;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // remove title
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//        setContentView(R.layout.layout_create_acct);
//
//        gotoCreateAcct = (TextView)findViewById(R.id.textView2);
//        username = (EditText)findViewById(R.id.username);
//        email = (EditText) findViewById(R.id.email);
//        password = (EditText) findViewById(R.id.password);
//        createAcc = (Button) findViewById(R.id.login);
//        progressDialog = new ProgressDialog(this);
//
//        createAcc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
//                    email.setError("Invalid email address.");
//                    return;
//                }
//                if (username.getText().toString().length() < 3) {
//                    username.setError("Invalid username. Too short");
//                    return;
//                }
//                if (password.getText().toString().length() < 4) {
//                    password.setError("Invalid password. Too short");
//                    return;
//                }
//
//                if (!TextUtils.isEmpty(username.getText()) && !TextUtils.isEmpty(email.getText()) && !TextUtils.isEmpty(password.getText())) {
//
//                    progressDialog.setMessage("Setting up your account...");
//                    progressDialog.setCanceledOnTouchOutside(false);
//                    progressDialog.show();
//
//                    ParseUser parseUser = new ParseUser();
//                    parseUser.setUsername(username.getText().toString());
//                    parseUser.setEmail(email.getText().toString());
//                    parseUser.setPassword(password.getText().toString());
//
//                    parseUser.signUpInBackground(new SignUpCallback() {
//                        @Override
//                        public void done(ParseException e) {
//                            if (e == null) {
//                                progressDialog.dismiss();
//                                startActivity(new Intent(CreateAccount.this, HomeFragment.class));
//                                finish();
//                            } else {
//                                progressDialog.dismiss();
//                                switch (e.getCode()) {
//                                    case ParseException.USERNAME_TAKEN:
//                                        snack(e.getMessage());
//                                        username.setError(e.getMessage());
//                                        break;
//
//                                    case ParseException.EMAIL_TAKEN:
//                                        snack(e.getMessage());
//                                        email.setError(e.getMessage());
//                                        break;
//
//                                    case ParseException.EMAIL_NOT_FOUND:
//                                        snack(e.getMessage());
//                                        email.setError(e.getMessage());
//                                        break;
//
//                                    case ParseException.USERNAME_MISSING:
//                                        snack(e.getMessage());
//                                        username.setError(e.getMessage());
//                                        break;
//                                    default:
//                                        Log.e("see", e.toString());
//                                        break;
//                                }
//
//                                return;
//                            }
//                        }
//                    });
//                }
//            }
//        });
//
//
//        gotoCreateAcct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(CreateAccount.this, Splashscreen.class)); finish();
//            }
//        });
//
//    }
//
//    public void snack(String message) {
//        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
//                .show();
//    }
//
//}
