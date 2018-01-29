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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Activity_Student_Login extends AppCompatActivity {
    private EditText etemail, etpassword;
    private Button btn_login;
    private ProgressBar pd;
    private FirebaseAuth auth;
    private TextView signup_Link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        get firebase instance
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(Activity_Student_Login.this, Activity_Student_Dashboard.class));
            finish();
        }
//        set the view
        setContentView(R.layout.activity_student_login);

        /*getting the fields in the layout*/

        etemail = (EditText) findViewById(R.id.email);
        etpassword = (EditText) findViewById(R.id.password);
        btn_login = (Button) findViewById(R.id.login);
        pd = (ProgressBar) findViewById(R.id.progressBar);
        signup_Link = (TextView) findViewById(R.id.link_signup);

        auth = FirebaseAuth.getInstance();

        signup_Link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Activity_Student_Signup.class));
                finish();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*getting data entered in the text fields*/
                String mail = etemail.getText().toString();
                final String pass = etpassword.getText().toString();


//                validating the data
                if (TextUtils.isEmpty(mail)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
/*displaying a progress dialog*/
                pd.setVisibility(View.VISIBLE);

//                authenticating the user
                auth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(Activity_Student_Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.

                        pd.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {
                            if (pass.length() < 6) {
                                etpassword.setError(getString(R.string.minimum_password));
                            } else {
                                Toast.makeText(Activity_Student_Login.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Intent intent = new Intent(Activity_Student_Login.this, Activity_Lecturer_Dashboard.class);
                            startActivity(intent);
                            finish();
                        }
                    }


                });
            }
        });

    }
}
