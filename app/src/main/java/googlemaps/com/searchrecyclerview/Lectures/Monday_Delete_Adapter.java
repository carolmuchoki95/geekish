package googlemaps.com.searchrecyclerview.Lectures;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import googlemaps.com.searchrecyclerview.R;

/**
 * Created by Narse on 4/4/2017.
 */

public class Monday_Delete_Adapter extends BaseAdapter {

    private Context tcontext;
    private LayoutInflater inflater;
    public  ViewHolder holder=null;
    private List<Monday_Model> monday_models = new ArrayList<>();

    Monday_Delete_Adapter(Context context, List<Monday_Model> values)
    {
        this.tcontext=context;
        this.monday_models = values;
        this.inflater=LayoutInflater.from(context);
    }


    public int getCount() {

        return monday_models.size();
    }

    @Override
    public Object getItem(int position)
    {
        return position;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.layout_listview_delete,null);
            holder=new ViewHolder();
            holder.dActivity=(TextView) convertView.findViewById(R.id.delactivity);
            holder.dTime=(TextView)convertView.findViewById(R.id.deltime);
            holder.dVenue=(TextView)convertView.findViewById(R.id.delvenue);
            holder.delete = (Button) convertView.findViewById(R.id.delete_record_list);
            convertView.setTag(holder);

        }
        else
        {
            holder=(ViewHolder)convertView.getTag();
        }

        holder.dActivity.setText(this.monday_models.get(position).activity);
        holder.dTime.setText(this.monday_models.get(position).time);
        holder.dVenue.setText(String.valueOf(this.monday_models.get(position).venue));
        holder.delete.setTag(position);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Delete button will be available for each row, when pressed it will deleted the particular data from Table,
                 * and also the data in list.
                 */
                int pos = (int) v.getTag();
                Toast.makeText(tcontext,String.valueOf(pos),Toast.LENGTH_LONG).show();
               Monday_Model monday_modelsSugar = Monday_Model.findById(Monday_Model.class, monday_models.get(pos).getId());
                monday_modelsSugar.delete();
                monday_models.remove(pos);
                notifyDataSetChanged();


            }
        });
        return convertView;
    }
    static  class ViewHolder
    {
        TextView dActivity,dTime,dVenue;
        Button delete;
    }
}
