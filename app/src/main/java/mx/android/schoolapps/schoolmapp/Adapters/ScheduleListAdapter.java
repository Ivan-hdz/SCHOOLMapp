package mx.android.schoolapps.schoolmapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import mx.android.schoolapps.schoolmapp.Models.Schedule;
import mx.android.shcoolapps.schoolmap.R;

/**
 * Created by shiro on 30/03/18.
 */

public class ScheduleListAdapter extends BaseAdapter{

    private Context context;
    private int layout;
    private List<Schedule> scheduleList;

    public ScheduleListAdapter(Context context, int layout, List<Schedule> scheduleList) {
        this.context = context;
        this.layout = layout;
        this.scheduleList = scheduleList;
    }

    @Override
    public int getCount() {
        return scheduleList.size();
    }

    @Override
    public Schedule getItem(int position) {
        return scheduleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh;

        if(convertView == null){
            convertView= LayoutInflater.from(context).inflate(layout,null);

            vh= new ViewHolder();
            vh.classroom= convertView.findViewById(R.id.textViewScheduleClassroom);
            vh.group= convertView.findViewById(R.id.textViewScheduleGroup);

            convertView.setTag(vh);

        }else
            vh= (ViewHolder) convertView.getTag();

        vh.classroom.setText(String.valueOf(scheduleList.get(position).getClassroom()));
        vh.group.setText(scheduleList.get(position).getGrupo());

        return convertView;
    }

    public class ViewHolder{
        private TextView classroom;
        private TextView group;
    }
}
