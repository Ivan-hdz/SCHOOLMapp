package mx.android.schoolapps.schoolmapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

import mx.android.schoolapps.schoolmapp.Models.Schedule;
import mx.android.shcoolapps.schoolmap.R;

/**
 * Created by shiro on 23/03/18.
 */

public class TeachersRecyclerAdapter extends RecyclerView.Adapter<TeachersRecyclerAdapter.ViewHolder>
        implements Filterable{

    private Context context;
    private ArrayList<Schedule> teachersList;
    private ArrayList<Schedule> teachersListFiltered;
    private onTeacherClickListener onTeacherClickListener;

    public interface onTeacherClickListener{
        void OnItemClickListener(Schedule schedule);
    }

    public void setOnTeacherClickListener(onTeacherClickListener onTeacherClickListener){
        this.onTeacherClickListener= onTeacherClickListener;
    }

    public TeachersRecyclerAdapter(Context context, ArrayList<Schedule> teachersList){
        this.context= context;
        this.teachersList= teachersList;
        this.teachersListFiltered= teachersList;
    }

    @Override
    public TeachersRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.teachers_schedule_adapter, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TeachersRecyclerAdapter.ViewHolder holder, int position) {
        final Schedule schedule= teachersListFiltered.get(position);

        holder.name.setText(schedule.getNombreProf());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTeacherClickListener.OnItemClickListener(schedule);
            }
        });
    }

    @Override
    public int getItemCount() {
        return teachersListFiltered.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.textViewTeachersScheduleActivityName);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString= charSequence.toString().toLowerCase();

                if(charString.isEmpty())
                    teachersListFiltered= teachersList;
                else{
                    ArrayList<Schedule> filteredList= new ArrayList<>();
                    for(Schedule schedule : teachersList){

                        if(schedule.getNombreProf().toLowerCase().contains(charString)
                                || schedule.getNombreProf().replace("á","a").contains(charString)
                                || schedule.getNombreProf().replace("é","e").contains(charString)
                                || schedule.getNombreProf().replace("í","i").contains(charString)
                                || schedule.getNombreProf().replace("ó","o").contains(charString)
                                || schedule.getNombreProf().replace("ú","u").contains(charString)
                                || schedule.getNombreAsignatura().toLowerCase().contains(charString)
                                || schedule.getNombreAsignatura().replace("á","a").contains(charString)
                                || schedule.getNombreAsignatura().replace("é","e").contains(charString)
                                || schedule.getNombreAsignatura().replace("í","i").contains(charString)
                                || schedule.getNombreAsignatura().replace("ó","o").contains(charString)
                                || schedule.getNombreAsignatura().replace("ú","u").contains(charString))
                            filteredList.add(schedule);
                    }
                    teachersListFiltered= filteredList;
                }

                FilterResults results= new FilterResults();
                results.values= teachersListFiltered;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                teachersListFiltered= (ArrayList<Schedule>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
