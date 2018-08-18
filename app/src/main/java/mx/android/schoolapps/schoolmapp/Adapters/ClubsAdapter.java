package mx.android.schoolapps.schoolmapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import mx.android.shcoolapps.schoolmap.R;
import mx.android.schoolapps.schoolmapp.Models.Club;

/**
 * Created by Shiro on 16/10/2017.
 */

public class ClubsAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Club> clubs;

    public ClubsAdapter(Context context, int layout, List<Club> clubs){
        this.context= context;
        this.layout= layout;
        this.clubs= clubs;

    }

    @Override
    public int getCount() {
        return clubs.size();
    }

    @Override
    public Club getItem(int position) {
        return clubs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh;

        if(convertView == null){
            convertView= LayoutInflater.from(context).inflate(layout,null);

            vh= new ViewHolder();

            vh.textViewClubName= convertView.findViewById(R.id.textViewClubName);
            vh.textViewClubProfessor= convertView.findViewById(R.id.textViewClubProfessor);
            vh.textViewClubClassroom= convertView.findViewById(R.id.textViewClubClassroom);

            convertView.setTag(vh);
        }
        else
            vh= (ViewHolder) convertView.getTag();

        vh.textViewClubName.setText(String.valueOf(clubs.get(position).getName()));
        vh.textViewClubProfessor.setText(String.valueOf("Encargado: " + clubs.get(position).getProfessor()));
        vh.textViewClubClassroom.setText(String.valueOf("Ubicación: " + clubs.get(position).getClassroom()));

        return convertView;
    }

    public class ViewHolder{
        private TextView textViewClubName;
        private TextView textViewClubProfessor;
        private TextView textViewClubClassroom;
    }
}
