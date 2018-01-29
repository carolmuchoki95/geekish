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


public class Tuesday_delete_adapter extends BaseAdapter {

        private Context tcontext;
        private LayoutInflater inflater;
        public Tuesday_delete_adapter.ViewHolder holder=null;
        private List<Tuesday_Model> tuesday_models = new ArrayList<>();

        Tuesday_delete_adapter(Context context, List<Tuesday_Model> values)
        {
            this.tcontext=context;
            this.tuesday_models = values;
            this.inflater=LayoutInflater.from(context);
        }


        public int getCount() {

            return tuesday_models.size();
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
                holder=new Tuesday_delete_adapter.ViewHolder();
                holder.dActivity=(TextView) convertView.findViewById(R.id.delactivity);
                holder.dTime=(TextView)convertView.findViewById(R.id.deltime);
                holder.dVenue=(TextView)convertView.findViewById(R.id.delvenue);
                holder.delete = (Button) convertView.findViewById(R.id.delete_record_list);
                convertView.setTag(holder);

            }
            else
            {
                holder=(Tuesday_delete_adapter.ViewHolder)convertView.getTag();
            }

            holder.dActivity.setText(this.tuesday_models.get(position).activity);
            holder.dTime.setText(this.tuesday_models.get(position).time);
            holder.dVenue.setText(String.valueOf(this.tuesday_models.get(position).venue));
            holder.delete.setTag(position);
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                /* Delete button will be available for each row, when pressed it will deleted the particular data from Table,
                 * and also the data in list.
                 */
                    int pos = (int) v.getTag();
                    Toast.makeText(tcontext,String.valueOf(pos),Toast.LENGTH_LONG).show();
                    Tuesday_Model tuesday_modelsSugar = Tuesday_Model.findById(Tuesday_Model.class, tuesday_models.get(pos).getId());
                    tuesday_modelsSugar.delete();
                    tuesday_models.remove(pos);
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


