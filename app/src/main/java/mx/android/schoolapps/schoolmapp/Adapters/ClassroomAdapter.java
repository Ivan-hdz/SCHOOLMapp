package mx.android.schoolapps.schoolmapp.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mx.android.schoolapps.schoolmapp.Models.Classroom;
import mx.android.schoolapps.schoolmapp.Models.Schedule;
import mx.android.shcoolapps.schoolmap.R;

/**
 * Created by Shiro on 31/10/2017.
 */

public class ClassroomAdapter extends BaseAdapter implements Filterable{

    private Context context;
    private int layout;
    private ArrayList<Schedule> schedulesList;
    private ArrayList<Schedule> schedulesListFiltered;
    private String textToDisplay;

    public ClassroomAdapter(Context context, int layout, ArrayList<Schedule> schedulesList, String textToDisplay) {
        this.context = context;
        this.layout = layout;
        this.schedulesList = schedulesList;
        this.schedulesListFiltered= schedulesList;
        this.textToDisplay= textToDisplay;
    }

    @Override
    public int getCount() {
        return schedulesListFiltered.size();
    }

    @Override
    public Schedule getItem(int position) {
        return schedulesListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh;

        if(convertView == null){
            convertView= LayoutInflater.from(context).inflate(layout, null);

            vh= new ViewHolder();
            vh.classroomNumber= convertView.findViewById(R.id.textViewClassroomAdapterNumber);
            vh.classroomName= convertView.findViewById(R.id.textViewClassroomAdapterName);

            convertView.setTag(vh);
        }
        else
            vh= (ViewHolder) convertView.getTag();

        Schedule schedule= schedulesListFiltered.get(position);

        if(textToDisplay.equals("classroom")){
            vh.classroomName.setText("Salón");
            vh.classroomNumber.setText(String.valueOf(schedule.getClassroom()));
        }else{
            vh.classroomName.setText("Grupo");
            vh.classroomNumber.setText(String.valueOf(schedule.getGrupo()));
        }

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString= charSequence.toString();

                if(textToDisplay.equals("classroom")){
                    if(charString.isEmpty())
                        schedulesListFiltered= schedulesList;
                    else{
                        ArrayList<Schedule> filteredList= new ArrayList<>();
                        for(Schedule schedule : schedulesList){

                            if(schedule.getClassroom().toLowerCase().contains(charString.toLowerCase())
                                    || schedule.getClassroom().toLowerCase().contains(charSequence))
                                filteredList.add(schedule);
                        }
                        schedulesListFiltered= filteredList;
                    }

                    FilterResults results= new FilterResults();
                    results.values= schedulesListFiltered;
                    return results;
                }else{
                    if(charString.isEmpty())
                        schedulesListFiltered= schedulesList;
                    else{
                        ArrayList<Schedule> filteredList= new ArrayList<>();
                        for(Schedule schedule : schedulesList){

                            if(schedule.getGrupo().toLowerCase().contains(charString.toLowerCase())
                                    || schedule.getGrupo().toLowerCase().contains(charSequence))
                                filteredList.add(schedule);
                        }
                        schedulesListFiltered= filteredList;
                    }

                    FilterResults results= new FilterResults();
                    results.values= schedulesListFiltered;
                    return results;
                }
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                schedulesListFiltered= (ArrayList<Schedule>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder{
        TextView classroomNumber;
        TextView classroomName;
    }
}
