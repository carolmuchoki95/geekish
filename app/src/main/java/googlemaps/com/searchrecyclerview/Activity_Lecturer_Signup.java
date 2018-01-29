package googlemaps.com.searchrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Activity_Lecturer_Signup extends AppCompatActivity {
    EditText email, password, confirm;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        confirm = (EditText) findViewById(R.id.confirm_password);
        signup = (Button) findViewById(R.id.signup);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Validate();


            }
        });

    }

    String regEx =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@student.maseno.ac.ke$";

    private void Validate() {
        String mail = email.getText().toString();
        String pass = password.getText().toString();
        String conf = confirm.getText().toString();

        if (pass.isEmpty() || pass.length() <= 4) {
            password.setError("Password should have more than 4 characters");
        } else {
            password.setError(null);
        }

        if (conf.isEmpty() || conf.equals(pass)) {
            confirm.setError("Both passwords are not matching");
        } else {
            confirm.setError(null);
        }
        Matcher matcherObj = Pattern.compile(regEx).matcher(mail);
        if (matcherObj.matches()) {
            //Toast.makeText(getApplicationContext(), "Email is valid", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Email format is Invalid", Toast.LENGTH_SHORT).show();
        }


    }
}
