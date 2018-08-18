package Objetos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.AndroidException;
import android.util.Log;
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
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import mx.android.shcoolapps.schoolmap.R;

public class AdapterClub extends RecyclerView.Adapter<AdapterClub.ComidasviewHolder> implements Filterable{

    List<FBArticulo> club;
    List<FBArticulo> clubFiltered;
    Context context;

    public AdapterClub(Context context, List<FBArticulo> personal) {
        this.context= context;
        this.club = personal;
        this.clubFiltered= personal;
    }

    @Override
    public ComidasviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(context).inflate(R.layout.row_recyclerclubs,parent,false);

        return new ComidasviewHolder(view);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onBindViewHolder(ComidasviewHolder holder, int position) {
        final FBArticulo club = clubFiltered.get(position);

        holder.textViewnombreclub.setText(club.getNombre());
        holder.textViewhorarioclub.setText(club.getHorario());
        holder.textViewubicacionclub.setText(club.getUbicacion());
        holder.textViewcontactoClub.setText(club.getWeb());
        if(club.getWeb().contains("facebook.com"))
        {
            holder.imageViewphone.setImageResource(R.drawable.facebook);
        }
        else
        {
            holder.imageViewphone.setImageResource(R.drawable.web);
        }



            holder.textViewcontactoClub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        Uri uri=Uri.parse(club.getWeb());

                        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                        try {
                            v.getContext().startActivity(intent);
                        }catch (android.content.ActivityNotFoundException ex)

                        {
                            Toast.makeText(v.getContext(),"Error al abrir página.",Toast.LENGTH_SHORT);
                        }


                }
            });


        //URL de facebook

        //Fin de URL Facebook


        holder.textViewdescripcionclub.setText(club.getDescripcion());
        holder.buttonWebclub.setText(club.getContacto());

        Glide.with(this.context).load(club.getImagen()).into(holder.imageViewclub);
        if(club.getContacto().contains("@"))
        {
            holder.imageViewCorreoTelefono.setImageResource(R.drawable.mail);
        }
        else if(club.getContacto().equals("--"))
        {
            holder.imageViewCorreoTelefono.setImageResource(R.drawable.mail);
        }
        else if(!esTelefono(club.getContacto()))
        {
            holder.imageViewCorreoTelefono.setImageResource(R.drawable.phoneclassic);
        }



            holder.buttonWebclub.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    if(club.getContacto().contains("@"))
                    {

                        Intent emailIntent=new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",club.getContacto(),null));
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Enviar correo - SCHOOLMapp");
                        try
                        {
                            v.getContext().startActivity(Intent.createChooser(emailIntent,"Enviar correo - SCHOOLMapp"));

                        }catch (android.content.ActivityNotFoundException ex)

                        {
                            Toast.makeText(v.getContext(),"Error al enviar correo",Toast.LENGTH_SHORT);
                        }
                    }
                    else if(club.getContacto().equals("--"))
                    {
                     try{   Toast.makeText(v.getContext(),"Forma de contacto no encontrada.",Toast.LENGTH_SHORT);
                     }
                     catch (android.content.ActivityNotFoundException ex){}
                    }
                    else if(!esTelefono(club.getContacto()))
                    {
                        Intent i=new Intent(Intent.ACTION_CALL);
                        String phone="tel:"+club.getContacto().toString();
                        i.setData(Uri.parse(phone));
                        try{
                            v.getContext().startActivity(i);
                        }catch(android.content.ActivityNotFoundException ex)

                        {
                            Toast.makeText(v.getContext(),"Error al llamar.",Toast.LENGTH_SHORT);
                        }
                    }


                }
            });




    }
    //Metodo para saber si es un telefono
    public static boolean esTelefono(String cadena)
    {
        boolean resultado;
        try
        {
            Integer.parseInt(cadena);
            resultado=true;
        }
        catch (NumberFormatException excepcion)
        {
            resultado=false;
        }
        return resultado;
    }

    @Override
    public int getItemCount() {
        return clubFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString= charSequence.toString();

                if(charString.isEmpty())
                    clubFiltered= club;
                else{
                    List<FBArticulo> filteredList= new ArrayList<>();
                    for(FBArticulo club : club){

                        if(club.getNombre().toLowerCase().contains(charString.toLowerCase())
                                || club.getNombre().toLowerCase().contains(charSequence))
                            filteredList.add(club);
                    }
                    clubFiltered= filteredList;
                }

                FilterResults results= new FilterResults();
                results.values= clubFiltered;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                clubFiltered= (ArrayList<FBArticulo>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class ComidasviewHolder extends RecyclerView.ViewHolder
    {
        TextView textViewnombreclub,textViewdescripcionclub,textViewhorarioclub,textViewubicacionclub,textViewcontactoClub;
        Button buttonWebclub;
        ImageView imageViewclub,imageViewphone,imageViewCorreoTelefono;
        public ComidasviewHolder(View itemView) {
            super(itemView);
            textViewnombreclub=itemView.findViewById(R.id.textview_nombreclub);
            textViewdescripcionclub=itemView.findViewById(R.id.textview_descripcionclub);
            textViewhorarioclub=itemView.findViewById(R.id.textview_horarioclub);
            textViewubicacionclub=itemView.findViewById(R.id.textview_ubicacionclub);
            textViewcontactoClub=itemView.findViewById(R.id.textview_contactoclub);
            buttonWebclub=itemView.findViewById(R.id.buttonWebclub);
            imageViewclub=itemView.findViewById(R.id.imageViewclub);
            imageViewphone= itemView.findViewById(R.id.imageViewphone);
            imageViewCorreoTelefono=itemView.findViewById(R.id.imageViewCorreoTelefono);
        }
    }
}
