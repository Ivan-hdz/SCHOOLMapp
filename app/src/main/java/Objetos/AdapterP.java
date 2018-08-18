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

public class AdapterP extends RecyclerView.Adapter<AdapterP.ComidasviewHolder> implements Filterable{

    List<FBArticulo> articulos;
    List<FBArticulo> articulosFiltered;
    Context context;

    public AdapterP(Context context, List<FBArticulo> articulos) {
        this.articulos = articulos;
        this.articulosFiltered= articulos;
        this.context= context;
    }

    @Override
    public ComidasviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(context).inflate(R.layout.row_recyclerp,parent,false);

        return new ComidasviewHolder(view);
    }

    @Override
    public void onBindViewHolder(ComidasviewHolder holder, int position) {
        FBArticulo articulo = articulosFiltered.get(position);

        holder.textViewnombrep.setText(articulo.getNombre());
        holder.textViewdescripcionp.setText(articulo.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return articulosFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString= charSequence.toString().toLowerCase();

                if(charString.isEmpty())
                    articulosFiltered= articulos;
                else{
                    List<FBArticulo> filteredList= new ArrayList<>();
                    for(FBArticulo articulo : articulos){

                        if(articulo.getNombre().toLowerCase().contains(charString)
                                || articulo.getNombre().replace("á","a").contains(charString)
                                || articulo.getNombre().replace("é","e").contains(charString)
                                || articulo.getNombre().replace("í","i").contains(charString)
                                || articulo.getNombre().replace("ó","o").contains(charString)
                                || articulo.getNombre().replace("ú","u").contains(charString))
                            filteredList.add(articulo);
                    }
                    articulosFiltered= filteredList;
                }

                FilterResults results= new FilterResults();
                results.values= articulosFiltered;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                articulosFiltered= (ArrayList<FBArticulo>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ComidasviewHolder extends RecyclerView.ViewHolder
    {
        TextView textViewnombrep,textViewdescripcionp;

        public ComidasviewHolder(View itemView) {
            super(itemView);
            textViewnombrep=itemView.findViewById(R.id.textview_nombrep);
            textViewdescripcionp=itemView.findViewById(R.id.textview_descripcionp);
        }
    }
}
