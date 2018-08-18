package mx.android.schoolapps.schoolmapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mx.android.schoolapps.schoolmapp.Models.Schedule;
import mx.android.shcoolapps.schoolmap.R;

public class GroupRecyclerAdapter extends RecyclerView.Adapter<GroupRecyclerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Schedule> schedulesList;

    public GroupRecyclerAdapter(Context context, ArrayList<Schedule> schedulesList){
        this.context= context;
        this.schedulesList= schedulesList;
    }

    @NonNull
    @Override
    public GroupRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.subjects_in_group, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupRecyclerAdapter.ViewHolder holder, int position) {
        Schedule schedule= schedulesList.get(position);

        holder.group.setText(schedule.getGrupo());
        holder.subject.setText(schedule.getNombreAsignatura());
        holder.teacher.setText(schedule.getNombreProf());
        holder.monday.setText(schedule.getLunes());
        holder.tuesday.setText(schedule.getMartes());
        holder.wednesday.setText(schedule.getMiercoles());
        holder.thursday.setText(schedule.getJueves());
        holder.friday.setText(schedule.getViernes());
    }

    @Override
    public int getItemCount() {
        return schedulesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView group;
        TextView subject;
        TextView teacher;
        TextView monday;
        TextView tuesday;
        TextView wednesday;
        TextView thursday;
        TextView friday;

        public ViewHolder(View itemView) {
            super(itemView);

            group= itemView.findViewById(R.id.textViewGroupGroup);
            subject= itemView.findViewById(R.id.textViewGroupSubject);
            teacher= itemView.findViewById(R.id.textViewGroupTeacher);
            monday= itemView.findViewById(R.id.textViewGroupMonday);
            tuesday= itemView.findViewById(R.id.textViewGroupTuesday);
            wednesday= itemView.findViewById(R.id.textViewGroupWednesday);
            thursday= itemView.findViewById(R.id.textViewGroupThursday);
            friday= itemView.findViewById(R.id.textViewGroupFriday);
        }
    }
}
