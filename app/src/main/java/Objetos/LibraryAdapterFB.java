package Objetos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import mx.android.shcoolapps.schoolmap.R;

public class LibraryAdapterFB extends RecyclerView.Adapter<LibraryAdapterFB.ComidasviewHolder> implements Filterable{

    List<FBArticulo> libros;
    List<FBArticulo> librosFiltered;
    Context context;

    public LibraryAdapterFB(Context context, List<FBArticulo> comidas) {
        this.context= context;
        this.libros = comidas;
        this.librosFiltered= comidas;
    }

    @Override
    public ComidasviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(context).inflate(R.layout.row_recyclerlibrary,parent,false);

        return new ComidasviewHolder(view);
    }

    @Override
    public void onBindViewHolder(ComidasviewHolder holder, int position) {
        final FBArticulo comida = librosFiltered.get(position);
        holder.textViewnombrel.setText(comida.getNombre());
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
                TextView t3=(TextView)dialog.findViewById(R.id.textViewBookQuantity);
                TextView t4=(TextView)dialog.findViewById(R.id.textViewBookDescription);
                TextView t5=(TextView)dialog.findViewById(R.id.textViewBookPrinting);
                TextView t6=(TextView)dialog.findViewById(R.id.textViewBookLanguage);
                //Poner los textos correspondientes
                t1.setText(comida.getAutor());
                t2.setText(comida.getNombre());
                t3.setText("Cantidad: "+comida.getCantidad().toString());
                t4.setText("Descr. Física: "+comida.getDescripcion());
                t5.setText("Pie de imprenta: "+comida.getImpresion());
                t6.setText("Idioma: "+comida.getIdioma());
                //Cargar la imagen
                Picasso.with(dialog.getContext()).load(comida.getImagen()).fit().into(image);
                dialog.show();

            }
        });

        holder.textViewdescripcionl.setText(comida.getAutor());
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
                String charString= charSequence.toString();

                if(charString.isEmpty())
                    librosFiltered= libros;
                else{
                    List<FBArticulo> filteredList= new ArrayList<>();
                    for(FBArticulo libro : libros){

                        if(libro.getNombre().toLowerCase().contains(charString.toLowerCase())
                                || libro.getNombre().toLowerCase().contains(charSequence))
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
                librosFiltered= (ArrayList<FBArticulo>) filterResults.values;
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
