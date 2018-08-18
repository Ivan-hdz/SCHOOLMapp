package mx.android.schoolapps.schoolmapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import mx.android.schoolapps.schoolmapp.Models.StudentCounselor;
import mx.android.shcoolapps.schoolmap.R;

/**
 * Created by Shiro on 29/10/2017.
 */

public class StudentsCounselorAdapter extends BaseAdapter {

    private ArrayList<StudentCounselor> studentsList;
    private Context context;
    private int layout;

    public StudentsCounselorAdapter(Context context, int layout, ArrayList<StudentCounselor> studentsList) {
        this.studentsList = studentsList;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return studentsList.size();
    }

    @Override
    public StudentCounselor getItem(int position) {
        return studentsList.get(position);
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
            vh.studentCounselorName= convertView.findViewById(R.id.StudentCounselorNameAdapter);
            vh.studentCounselorPic= convertView.findViewById(R.id.imageViewStudentCounselorPicAdapter);

            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        StudentCounselor studentCounselor= studentsList.get(position);

        vh.studentCounselorName.setText(studentCounselor.getName());
        Glide.with(context).load(studentCounselor.getStudentPic()).into(vh.studentCounselorPic);

        return convertView;
    }

    public class ViewHolder{
        public TextView studentCounselorName;
        public ImageView studentCounselorPic;
    }
}
