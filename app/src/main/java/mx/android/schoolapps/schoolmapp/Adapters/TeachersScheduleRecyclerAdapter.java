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

public class TeachersScheduleRecyclerAdapter extends RecyclerView.Adapter<TeachersScheduleRecyclerAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Schedule> teachersList;

    public TeachersScheduleRecyclerAdapter(Context context, ArrayList<Schedule> teachersList){
        this.context= context;
        this.teachersList= teachersList;
    }

    @Override
    public TeachersScheduleRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.teachers_schedule, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TeachersScheduleRecyclerAdapter.ViewHolder holder, int position) {
        final Schedule schedule= teachersList.get(position);

        holder.group.setText(schedule.getGrupo());
        holder.classroom.setText(schedule.getClassroom());
        holder.subject.setText(schedule.getNombreAsignatura());
        holder.monday.setText(schedule.getLunes());
        holder.tuesday.setText(schedule.getMartes());
        holder.wednesday.setText(schedule.getMiercoles());
        holder.thursday.setText(schedule.getJueves());
        holder.friday.setText(schedule.getViernes());
    }

    @Override
    public int getItemCount() {
        return teachersList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView group;
        TextView classroom;
        TextView subject;
        TextView monday;
        TextView tuesday;
        TextView wednesday;
        TextView thursday;
        TextView friday;

        public ViewHolder(View itemView) {
            super(itemView);

            group= itemView.findViewById(R.id.textViewGroupTeachersSchedule);
            classroom= itemView.findViewById(R.id.textViewClassroomTeachersSchedule);
            subject= itemView.findViewById(R.id.textViewSubjectTeachersSchedule);
            monday= itemView.findViewById(R.id.textViewTeachersScheduleMonday);
            tuesday= itemView.findViewById(R.id.textViewTeachersScheduleTuesday);
            wednesday= itemView.findViewById(R.id.textViewTeachersScheduleWednesday);
            thursday= itemView.findViewById(R.id.textViewTeachersScheduleThursday);
            friday= itemView.findViewById(R.id.textViewTeachersScheduleFriday);
        }
    }
}
