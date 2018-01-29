package googlemaps.com.searchrecyclerview.Lectures;

import android.app.TimePickerDialog;
import android.content.Intent;
import java.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import googlemaps.com.searchrecyclerview.R;

import static googlemaps.com.searchrecyclerview.R.id.time;

public class Tuesday_Add_Data extends AppCompatActivity {
    EditText act, tym, ven;

    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        act = (EditText) findViewById(R.id.activity);
        tym = (EditText) findViewById(time);
        ven = (EditText) findViewById(R.id.venue);

        tym.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Tuesday_Add_Data.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tym.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String activity = act.getText().toString();
                String time = tym.getText().toString();
                String venue = ven.getText().toString();


               Tuesday_Model tueModel = new Tuesday_Model(activity,time,venue);
                tueModel.save();
                startActivity(new Intent(getApplicationContext(),Activity_Tuesday.class));

//                clear the edit text fields
                act.setText("");
                tym.setText("");
                ven.setText("");

                finish();
            }
        });
    }
}
