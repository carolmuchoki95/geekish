package googlemaps.com.searchrecyclerview.Lectures;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import googlemaps.com.searchrecyclerview.R;

public class Monday_Delete_Data extends AppCompatActivity {


    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_data);

         listView = (ListView) findViewById(R.id.list_view);
        long count = Monday_Model.count(Monday_Model.class);
        if(count>0)
        {
            /* Get all records in table and display in listview, where a button available for for deleting. on pressing it the
             * particular row will be removed from Table.
            * */
            List<Monday_Model> userTable = Monday_Model.listAll(Monday_Model.class);
            Monday_Delete_Adapter madapter = new Monday_Delete_Adapter(getApplicationContext(),userTable);
            listView.setAdapter(madapter);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No Data Available in Table", Toast.LENGTH_LONG);



        }

    }
}
