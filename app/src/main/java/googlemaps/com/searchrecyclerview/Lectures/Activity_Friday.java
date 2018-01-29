package googlemaps.com.searchrecyclerview.Lectures;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import java.util.List;

import googlemaps.com.searchrecyclerview.Activity_Student_Dashboard;
import googlemaps.com.searchrecyclerview.R;


public class Activity_Friday extends AppCompatActivity {
    private static final String TAG = Activity_Friday.class.getSimpleName();
    EditText etActivity, etTime, etVenue;
    final Context mContext = this;
    FloatingActionButton fab;
    Friday_Model friday_model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friday);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        friday_model = new Friday_Model();
        ListView listView = (ListView) findViewById(R.id.assignments_list);
        long count = Friday_Model.count(Friday_Model.class);
        if (count > 0) {
            Friday_Model.listAll(Friday_Model.class);
            List<Friday_Model> friday_models = Friday_Model.listAll(Friday_Model.class);
            Friday_Add_Adapter fridayAdd_adapter = new Friday_Add_Adapter(getApplicationContext(), friday_models);
            listView.setAdapter(fridayAdd_adapter);
        } else {
            Toast.makeText(getApplicationContext(), "No Data Available in Table", Toast.LENGTH_LONG);

        }
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                openAlert(view);

            }
        });
    }




    /**
     * Let's the user tap the activity icon to go 'home'.
     * Requires setHomeButtonEnabled() in onCreate().
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                // ProjectsActivity is my 'home' activity
                startActivityAfterCleanup(Activity_Student_Dashboard.class);
                return true;
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    private void startActivityAfterCleanup(Class<?> cls) {
        Intent intent = new Intent(getApplicationContext(), cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }



    private void openAlert(View view) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Activity_Friday.this);


        alertDialogBuilder.setTitle("Select option");


        // set positive button: Yes message

        alertDialogBuilder.setPositiveButton("Add Data", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {

                // go to a new activity of the app

                Intent addData = new Intent(getApplicationContext(),

                        Friday_Add_Data.class);

                startActivity(addData);
                finish();
            }

        });


        // set neutral button: Exit the app message

        alertDialogBuilder.setNeutralButton("Delete Data", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {

                Intent del = new Intent(getApplicationContext(),

                        Friday_Delete_Data.class);

                startActivity(del);
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        // show alert
        alertDialog.show();
    }


}





