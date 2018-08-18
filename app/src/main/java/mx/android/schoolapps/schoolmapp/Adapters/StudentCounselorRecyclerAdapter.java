package mx.android.schoolapps.schoolmapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import mx.android.schoolapps.schoolmapp.Models.StudentCounselor;
import mx.android.shcoolapps.schoolmap.R;

/**
 * Created by shiro on 18/02/18.
 */

public class StudentCounselorRecyclerAdapter extends RecyclerView.Adapter<StudentCounselorRecyclerAdapter.ViewHolder> {

    private ArrayList<StudentCounselor> studentsList;
    private Context context;
    private int layout;
    private OnStudentCounselorClickListener onStudentCounselorClickListener;

    public interface OnStudentCounselorClickListener{
        void onStudentCounselorClick(StudentCounselor studentCounselor);
    }

    public void setOnStudentCounselorClickListener(OnStudentCounselorClickListener onStudentCounselorClickListener){
        this.onStudentCounselorClickListener= onStudentCounselorClickListener;
    }

    public StudentCounselorRecyclerAdapter(ArrayList<StudentCounselor> studentsList, Context context, int layout){
        this.studentsList= studentsList;
        this.context= context;
        this.layout= layout;
    }

    @Override
    public StudentCounselorRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentCounselorRecyclerAdapter.ViewHolder holder, int position) {
        final StudentCounselor studentCounselor= studentsList.get(position);

        holder.studentCounselorName.setText(studentCounselor.getName());
        Glide.with(context)
                .load(studentCounselor.getStudentPic())
                .into(holder.studentCounselorPic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStudentCounselorClickListener.onStudentCounselorClick(studentCounselor);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView studentCounselorName;
        ImageView studentCounselorPic;

        public ViewHolder(View itemView) {
            super(itemView);

            studentCounselorName= itemView.findViewById(R.id.StudentCounselorNameAdapter);
            studentCounselorPic= itemView.findViewById(R.id.imageViewStudentCounselorPicAdapter);
        }
    }
}
