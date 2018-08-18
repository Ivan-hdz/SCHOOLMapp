package mx.android.schoolapps.schoolmapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import mx.android.schoolapps.schoolmapp.Models.ClassroomCentralBuilding;
import mx.android.shcoolapps.schoolmap.R;

/**
 * Created by Shiro on 05/12/2017.
 */

public class ClassroomCentralBuildingAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<ClassroomCentralBuilding> classroomList;

    public ClassroomCentralBuildingAdapter(Context context, int layout, List<ClassroomCentralBuilding> classroomList) {
        this.context = context;
        this.layout = layout;
        this.classroomList = classroomList;
    }

    @Override
    public int getCount() {
        return classroomList.size();
    }

    @Override
    public ClassroomCentralBuilding getItem(int position) {
        return classroomList.get(position);
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

            convertView.setTag(vh);
        }
        else
            vh= (ViewHolder) convertView.getTag();

        ClassroomCentralBuilding classroom= classroomList.get(position);

        vh.classroomNumber.setText(classroom.getId());

        return convertView;
    }

    public class ViewHolder{
        TextView classroomNumber;
    }
}
