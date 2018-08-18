package Objetos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import mx.android.shcoolapps.schoolmap.R;

public class AdapterC extends RecyclerView.Adapter<AdapterC.ComidasviewHolder> implements Filterable{

    List<FBArticulo> consejero;
    List<FBArticulo> consejeroFiltered;
    Context context;

    public AdapterC(Context context, List<FBArticulo> personal) {
        this.context= context;
        this.consejero = personal;
        this.consejeroFiltered= personal;
    }

    @Override
    public ComidasviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(context).inflate(R.layout.row_recyclercons,parent,false);

        return new ComidasviewHolder(view);
    }

    @Override
    public void onBindViewHolder(ComidasviewHolder holder, int position) {
        final FBArticulo consejero = consejeroFiltered.get(position);

        holder.textViewnombrec.setText(consejero.getNombre());
        holder.textViewdescripcionc.setText(consejero.getDescripcion());
        holder.buttonContactoc.setText(consejero.getContacto());

        Glide.with(this.context).load(consejero.getImagen()).into(holder.imageViewc);

        holder.buttonContactoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent=new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",consejero.getContacto(),null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Enviar correo - SCHOOLMapp");
                try
                {
                    v.getContext().startActivity(Intent.createChooser(emailIntent,"Enviar correo - SCHOOLMapp"));

                }catch (android.content.ActivityNotFoundException ex)

                {
                    Toast.makeText(v.getContext(),"Error al enviar correo",Toast.LENGTH_SHORT);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return consejeroFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString= charSequence.toString();

                if(charString.isEmpty())
                    consejeroFiltered= consejero;
                else{
                    List<FBArticulo> filteredList= new ArrayList<>();
                    for(FBArticulo consejero : consejero){

                        if(consejero.getNombre().toLowerCase().contains(charString.toLowerCase())
                                || consejero.getNombre().toLowerCase().contains(charSequence))
                            filteredList.add(consejero);
                    }
                    consejeroFiltered= filteredList;
                }

                FilterResults results= new FilterResults();
                results.values= consejeroFiltered;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                consejeroFiltered= (ArrayList<FBArticulo>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ComidasviewHolder extends RecyclerView.ViewHolder
    {
        TextView textViewnombrec,textViewdescripcionc;
        Button buttonContactoc;
        ImageView imageViewc;
        public ComidasviewHolder(View itemView) {
            super(itemView);
            textViewnombrec=itemView.findViewById(R.id.textview_nombrec);
            textViewdescripcionc=itemView.findViewById(R.id.textview_descripcionc);

            buttonContactoc=itemView.findViewById(R.id.buttonContactoc);
            imageViewc=itemView.findViewById(R.id.imageViewc);

        }
    }
}
