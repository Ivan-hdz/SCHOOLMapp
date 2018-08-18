package Objetos;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import mx.android.shcoolapps.schoolmap.R;

public class LAFB extends RecyclerView.Adapter<LAFB.ComidasviewHolder> implements Filterable{

    List<Librox> libros;
    List<Librox> librosFiltered;
    Context context;

    public LAFB(Context context, List<Librox> libros) {
        this.context= context;
        this.libros = libros;
        this.librosFiltered= libros;
    }

    @Override
    public ComidasviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(context).inflate(R.layout.row_recyclerlibrary,parent,false);

        return new ComidasviewHolder(view);
    }

    @Override
    public void onBindViewHolder(ComidasviewHolder holder, int position) {
        final Librox libro = librosFiltered.get(position);
        holder.textViewnombrel.setText(libro.getNombre());
        holder.textViewnombrel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog=new Dialog(context,R.style.Theme_AppCompat_Light_Dialog_Alert);
                dialog.requestWindowFeature(Window.FEATURE_SWIPE_TO_DISMISS);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.alert_library);
                //Objetos a cambiar
                ImageView image = (ImageView) dialog.findViewById(R.id.imageViewBookCover);
                TextView t1=(TextView)dialog.findViewById(R.id.textViewBookAuthor);
                TextView t2=(TextView)dialog.findViewById(R.id.textViewBookTitle);
                TextView t3=(TextView)dialog.findViewById(R.id.textViewBookCodigo);
                TextView t4=(TextView)dialog.findViewById(R.id.textViewBookColeccion);
                TextView t5=(TextView)dialog.findViewById(R.id.textViewBookClasificacion);
                TextView t6=(TextView)dialog.findViewById(R.id.textViewBookISBN);
                TextView t7=(TextView)dialog.findViewById(R.id.textViewBookEditorial);
                TextView t8=(TextView)dialog.findViewById(R.id.textViewBookMaterial);
                TextView t9=(TextView)dialog.findViewById(R.id.textViewBookIOYAM);
                //Poner los textos correspondientes
                t1.setText(libro.getAutor());
                t2.setText(libro.getNombre());
                t3.setText("Código de barras: "+libro.getCodigo());
                t4.setText("Colección: "+libro.getColeccion());
                t5.setText("Clasificación: "+libro.getClasificacion());
                t6.setText("ISBN:"+libro.getIsbn());
                t7.setText("Editorial:"+libro.getEditorial());
                t8.setText("Material:"+libro.getMaterial());
                t9.setText("Item Open Year and Month:"+libro.getIoyam());

                //Cargar la imagen
                if(libro.getImagen().equals("-"))
                {
                    Picasso.with(dialog.getContext()).load(R.drawable.nocover).fit().into(image);
                }
                else
                {
                    Picasso.with(dialog.getContext()).load(libro.getImagen()).fit().into(image);
                }

                dialog.show();

            }
        });

        holder.textViewdescripcionl.setText(libro.getAutor());
    }

    @Override
    public int getItemCount() {
        return librosFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString= charSequence.toString().toLowerCase();

                if(charString.isEmpty())
                    librosFiltered= libros;
                else{
                    List<Librox> filteredList= new ArrayList<>();
                    for(Librox libro : libros){

                        if(libro.getNombre().toLowerCase().contains(charString)
                                || libro.getNombre().replace("á","a").contains(charString)
                                || libro.getNombre().replace("é","e").contains(charString)
                                || libro.getNombre().replace("í","i").contains(charString)
                                || libro.getNombre().replace("ó","o").contains(charString)
                                || libro.getNombre().replace("ú","u").contains(charString)
                                || libro.getAutor().toLowerCase().contains(charString)
                                || libro.getAutor().replace("á","a").contains(charString)
                                || libro.getAutor().replace("é","e").contains(charString)
                                || libro.getAutor().replace("í","i").contains(charString)
                                || libro.getAutor().replace("ó","o").contains(charString)
                                || libro.getAutor().replace("ú","u").contains(charString))
                            filteredList.add(libro);
                    }
                    librosFiltered= filteredList;
                }

                FilterResults results= new FilterResults();
                results.values= librosFiltered;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                librosFiltered= (ArrayList<Librox>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ComidasviewHolder extends RecyclerView.ViewHolder
    {
        TextView textViewnombrel,textViewdescripcionl;

        ImageView imageView;
        public ComidasviewHolder(View itemView) {
            super(itemView);
            textViewnombrel=itemView.findViewById(R.id.textViewBookTitlex);
            textViewdescripcionl=itemView.findViewById(R.id.textViewBookAuthorx);
            imageView=itemView.findViewById(R.id.imageViewLibrary);

        }
    }
}
