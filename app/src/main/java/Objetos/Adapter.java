package Objetos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mx.android.shcoolapps.schoolmap.R;

public class Adapter extends RecyclerView.Adapter<Adapter.ComidasviewHolder> implements Filterable{

    List<FBArticulo> comidas;
    List<FBArticulo> comidasFiltered;
    Context context;

    public Adapter(Context context, List<FBArticulo> comidas) {
        this.comidas = comidas;
        this.comidasFiltered= comidas;
        this.context= context;
    }

    @Override
    public ComidasviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(context).inflate(R.layout.row_recycler,parent,false);

        return new ComidasviewHolder(view);
    }

    @Override
    public void onBindViewHolder(ComidasviewHolder holder, int position) {
        FBArticulo comida = comidasFiltered.get(position);
        holder.textViewnombre.setText(comida.getNombre());
        holder.textViewdescripcion.setText(comida.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return comidasFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString= charSequence.toString().toLowerCase();

                if(charString.isEmpty())
                    comidasFiltered= comidas;
                else{
                    List<FBArticulo> filteredList= new ArrayList<>();
                    for(FBArticulo comida : comidas){

                        if(comida.getNombre().toLowerCase().contains(charString)
                                || comida.getNombre().replace("á","a").contains(charString)
                                || comida.getNombre().replace("é","e").contains(charString)
                                || comida.getNombre().replace("í","i").contains(charString)
                                || comida.getNombre().replace("ó","o").contains(charString)
                                || comida.getNombre().replace("ú","u").contains(charString))
                            filteredList.add(comida);
                    }
                    comidasFiltered= filteredList;
                }

                FilterResults results= new FilterResults();
                results.values= comidasFiltered;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                comidasFiltered= (ArrayList<FBArticulo>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public static class ComidasviewHolder extends RecyclerView.ViewHolder
    {
        TextView textViewnombre,textViewdescripcion;
        public ComidasviewHolder(View itemView) {
            super(itemView);
            textViewnombre=itemView.findViewById(R.id.textview_nombre);
            textViewdescripcion=itemView.findViewById(R.id.textview_descripcion);
        }
    }
}
