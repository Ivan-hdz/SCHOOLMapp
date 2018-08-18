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

public class AdapterO extends RecyclerView.Adapter<AdapterO.ComidasviewHolder> implements Filterable{

    List<FBArticulo> personal;
    List<FBArticulo> personalFiltered;
    Context context;

    public AdapterO(Context context, List<FBArticulo> personal) {
        this.context= context;
        this.personal = personal;
        this.personalFiltered= personal;
    }

    @Override
    public ComidasviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(context).inflate(R.layout.row_recyclcero,parent,false);

        return new ComidasviewHolder(view);
    }

    @Override
    public void onBindViewHolder(ComidasviewHolder holder, int position) {
        final FBArticulo personal = personalFiltered.get(position);

        holder.textViewnombreo.setText(personal.getNombre());
        holder.textViewdescripciono.setText(personal.getDescripcion());
        holder.textViewhorarioo.setText(personal.getHorario());
        holder.textViewubicaciono.setText(personal.getUbicacion());
        holder.textViewtelefonoo.setText(personal.getTelefono());
        holder.buttonContactoo.setText(personal.getContacto());

        Glide.with(this.context).load(personal.getImagen()).into(holder.imageViewo);

        holder.buttonContactoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent=new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",personal.getContacto(),null));
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
        return personalFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString= charSequence.toString();

                if(charString.isEmpty())
                    personalFiltered= personal;
                else{
                    List<FBArticulo> filteredList= new ArrayList<>();
                    for(FBArticulo personal : personal){

                        if(personal.getNombre().toLowerCase().contains(charString.toLowerCase())
                                || personal.getNombre().toLowerCase().contains(charSequence))
                            filteredList.add(personal);
                    }
                    personalFiltered= filteredList;
                }

                FilterResults results= new FilterResults();
                results.values= personalFiltered;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                personalFiltered= (ArrayList<FBArticulo>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ComidasviewHolder extends RecyclerView.ViewHolder
    {
        TextView textViewnombreo,textViewdescripciono,textViewhorarioo,textViewubicaciono,textViewtelefonoo;
        Button buttonContactoo;
        ImageView imageViewo;
        public ComidasviewHolder(View itemView) {
            super(itemView);
            textViewnombreo=itemView.findViewById(R.id.textview_nombreo);
            textViewdescripciono=itemView.findViewById(R.id.textview_descripciono);
            textViewhorarioo=itemView.findViewById(R.id.textview_horarioo);
            textViewubicaciono=itemView.findViewById(R.id.textview_ubicaciono);
            textViewtelefonoo=itemView.findViewById(R.id.textview_telefonoo);
            buttonContactoo=itemView.findViewById(R.id.buttonContactoo);
            imageViewo=itemView.findViewById(R.id.imageViewo);

        }
    }
}
