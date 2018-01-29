package googlemaps.com.searchrecyclerview;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import googlemaps.com.searchrecyclerview.Lectures.Activity_Friday;
import googlemaps.com.searchrecyclerview.Lectures.Activity_Monday;
import googlemaps.com.searchrecyclerview.Lectures.Activity_Thursday;
import googlemaps.com.searchrecyclerview.Lectures.Activity_Tuesday;
import googlemaps.com.searchrecyclerview.Lectures.Activity_Wednesday;

public class Activity_Student_Dashboard extends AppCompatActivity {
Button lectures, assignment, register,notice_board;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

/*get the view*/
        register=(Button) findViewById(R.id.register);

        /*register click listener*/
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://41.89.192.20/Login.aspx";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        lectures=(Button) findViewById(R.id.lectures);
        lectures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence options[] = new CharSequence[] {"Monday", "Tuesday", "Wednesday", "Thursday","Friday"};

                AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Student_Dashboard.this);
                builder.setCancelable(false);
                builder.setTitle("Select Day:");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int days) {

                        // the user clicked on options[which]
                        if(days==0){
                            startActivity(new Intent(getApplicationContext(),Activity_Monday.class));

                        }

                        if(days==1){
                            startActivity(new Intent(getApplicationContext(),Activity_Tuesday.class));

                        }

                        if(days==2){
                            startActivity(new Intent(getApplicationContext(),Activity_Wednesday.class));

                        }

                        if(days==3){
                            startActivity(new Intent(getApplicationContext(),Activity_Thursday.class));

                        }

                        if(days==4){
                            startActivity(new Intent(getApplicationContext(),Activity_Friday.class));

                        }

                    }
                });
                builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //the user clicked on Cancel
                    }
                });
                builder.show();
            }
        });
        assignment=(Button) findViewById(R.id.assignments);
        assignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
startActivity(new Intent(getApplicationContext(),Activity_Assignments.class));
            }
        });

        notice_board=(Button) findViewById(R.id.noticeboard);
        notice_board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Activity_NoticeBoard.class));

            }
        });
    }
}
