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


public class Activity_Wednesday extends AppCompatActivity {
    private static final String TAG = Activity_Wednesday.class.getSimpleName();
    EditText etActivity, etTime, etVenue;
    final Context mContext = this;
    FloatingActionButton fab;
    Wednesday_Model wednesday_model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wednesday);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        wednesday_model = new Wednesday_Model();
        ListView listView = (ListView) findViewById(R.id.assignments_list);
        long count = Wednesday_Model.count(Wednesday_Model.class);
        if (count > 0) {
            Wednesday_Model.listAll(Wednesday_Model.class);
            List<Wednesday_Model> wednesday_models = Wednesday_Model.listAll(Wednesday_Model.class);
            Wednesday_Add_Adapter wednesday_add_adapter = new Wednesday_Add_Adapter(getApplicationContext(), wednesday_models);
            listView.setAdapter(wednesday_add_adapter);
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

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Activity_Wednesday.this);


        alertDialogBuilder.setTitle("Select option");


        // set positive button: Yes message

        alertDialogBuilder.setPositiveButton("Add Data", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {

                // go to a new activity of the app

                Intent addData = new Intent(getApplicationContext(),

                        Wednesday_Add_Data.class);

                startActivity(addData);
                finish();
            }

        });


        // set neutral button: Exit the app message

        alertDialogBuilder.setNeutralButton("Delete Data", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {

                Intent del = new Intent(getApplicationContext(),

                        Wednesday_Delete_Data.class);

                startActivity(del);
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        // show alert
        alertDialog.show();
    }


}





