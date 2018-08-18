package Objetos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mx.android.shcoolapps.schoolmap.R;

public class AdapterQ extends RecyclerView.Adapter<AdapterQ.ComidasviewHolder>{

    private List<FBArticulo> copias;
    private Context context;

    public AdapterQ(Context context, List<FBArticulo> copias) {
        this.copias = copias;
        this.context= context;
    }

    @Override
    public ComidasviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(context).inflate(R.layout.row_recyclerq,parent,false);

        return new ComidasviewHolder(view);
    }

    @Override
    public void onBindViewHolder(ComidasviewHolder holder, int position) {
        FBArticulo copia = copias.get(position);
        holder.textViewnombreq.setText(copia.getNombre());
        holder.textViewdescripcionq.setText(copia.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return copias.size();
    }

    public static class ComidasviewHolder extends RecyclerView.ViewHolder
    {
        TextView textViewnombreq,textViewdescripcionq;
        public ComidasviewHolder(View itemView) {
            super(itemView);
            textViewnombreq=itemView.findViewById(R.id.textview_nombreq);
            textViewdescripcionq=itemView.findViewById(R.id.textview_descripcionq);
        }
    }
}
