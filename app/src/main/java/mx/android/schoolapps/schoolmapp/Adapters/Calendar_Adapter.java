package mx.android.schoolapps.schoolmapp.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;

import java.util.List;

import mx.android.schoolapps.schoolmapp.Models.Classroom;
import mx.android.shcoolapps.schoolmap.R;

/**
 * Created by Shiro on 31/10/2017.
 */

public class Calendar_Adapter extends BaseAdapter {

    private Context context;
    private int layout;
    private PDFView pdfView;

    public Calendar_Adapter(Context context, int layout, PDFView pdfView) {
        this.context = context;
        this.layout = layout;
        this.pdfView=pdfView;

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView == null){
            convertView= LayoutInflater.from(this.context).inflate(this.layout,null);

            vh= new Calendar_Adapter.ViewHolder();
            vh.pdfView= convertView.findViewById(R.id.pdfView);
            convertView.setTag(vh);
        }
        else{
            vh= (Calendar_Adapter.ViewHolder) convertView.getTag();
        }



        return convertView;
    }


    public class ViewHolder{
        PDFView pdfView;
    }
}
