package googlemaps.com.searchrecyclerview;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Activity_Student_Signup extends AppCompatActivity {
    EditText etemail, etPassword, etConfirmPassword;
    Button btn_signup;
    private FirebaseAuth auth;
    ProgressBar pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

//        get firebase instance
        auth = FirebaseAuth.getInstance();

        pd = (ProgressBar) findViewById(R.id.progressBar);
        etemail = (EditText) findViewById(R.id.adm_no);
        etPassword = (EditText) findViewById(R.id.password);
        etConfirmPassword = (EditText) findViewById(R.id.confirm_password);
        btn_signup = (Button) findViewById(R.id.signup);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etemail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String confirmpassword = etConfirmPassword.getText().toString().trim();

                pd.setVisibility(View.VISIBLE);

                /*create user in the db*/
                validate();

                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Activity_Student_Signup.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(Activity_Student_Signup.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        pd.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(Activity_Student_Signup.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(Activity_Student_Signup.this, Activity_Student_Dashboard.class));
                            finish();
                        }
                    }
                });
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        pd.setVisibility(View.GONE);
    }

    String regEx =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@student.maseno.ac.ke$";

    /* validating user input to differentiate students and lecturers*/
    private void validate() {

        String email = etemail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmpassword = etConfirmPassword.getText().toString().trim();

         /*validate edit text fields*/
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter Email address.!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password) && TextUtils.isEmpty(confirmpassword)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(confirmpassword)) {
            Toast.makeText(getApplicationContext(), "Confirm password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!confirmpassword.equals(password)) {
            Toast.makeText(getApplicationContext(), "Both passwords should match", Toast.LENGTH_LONG).show();
            return;
        }

        Matcher matcherObj = Pattern.compile(regEx).matcher(email);
        if (matcherObj.matches()) {
            //Toast.makeText(getApplicationContext(), "Email is valid", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Email format is Invalid", Toast.LENGTH_SHORT).show();
        }


        if (password.length() < 6 && confirmpassword.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }

    }
}
