package googlemaps.com.searchrecyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Activity_Home extends AppCompatActivity {
    Button lec, student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        lec = (Button) findViewById(R.id.lec);
        student = (Button) findViewById(R.id.student);

        lec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),Activity_Lecturer_Login.class));

            }
        });

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_Home.this, Activity_Student_Login.class);
                startActivity(intent);
            }
        });

    }
}
