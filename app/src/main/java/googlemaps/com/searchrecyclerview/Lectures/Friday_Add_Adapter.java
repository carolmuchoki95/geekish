package googlemaps.com.searchrecyclerview.Lectures;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import googlemaps.com.searchrecyclerview.R;

/**
 * Created by Narse on 4/4/2017.
 */

public class Friday_Add_Adapter extends BaseAdapter {
    private LayoutInflater inflater;
    public ViewHolder holder = null;
    private List<Friday_Model> friday_models = new ArrayList<>();

    Friday_Add_Adapter(Context context, List<Friday_Model> values) {
        // this.tcontext=context;
        this.friday_models = values;
        this.inflater = LayoutInflater.from(context);
    }


    public int getCount() {

        return friday_models.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.task_layout, null);
            holder = new ViewHolder();
            holder.activity = (TextView) convertView.findViewById(R.id.txt_activity);
            holder.time = (TextView) convertView.findViewById(R.id.txt_time);
            holder.venue = (TextView) convertView.findViewById(R.id.txt_venue);



            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.activity.setText(this.friday_models.get(position).activity);
        holder.time.setText(this.friday_models.get(position).time);
        holder.venue.setText(this.friday_models.get(position).venue);


        return convertView;
    }

    static class ViewHolder {
        TextView activity, time, venue;


    }

}
