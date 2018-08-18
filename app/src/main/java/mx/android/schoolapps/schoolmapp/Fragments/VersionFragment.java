package mx.android.schoolapps.schoolmapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import mx.android.schoolapps.schoolmapp.Activities.MainActivity;
import mx.android.shcoolapps.schoolmap.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link VersionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VersionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VersionFragment() {
        // Required empty public constructor
    }

    public static VersionFragment newInstance(String param1, String param2) {
        VersionFragment fragment = new VersionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_version, container, false);

        TextView names= view.findViewById(R.id.textViewVersionNames);
        TextView caracteristicas=view.findViewById(R.id.textViewCaracteristicas);
        Button botonSugerencias=view.findViewById(R.id.buttonSugerencias);
        botonSugerencias.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","correoschoolmapp@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Sugerencias - SCHOOLMapp");


                try {
                    startActivity(Intent.createChooser(emailIntent,  "Sugerencia ESCOM"));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(v.getContext(),"Error al enviar correo", Toast.LENGTH_SHORT).show();
                }
            }
        });
        caracteristicas.setText(getCaracteristicas());
        names.setText(getNames());
        return view;
    }
    public String getCaracteristicas()
    {
        return"Revisa los horarios de los salones e información relevante sobre la escuela, " +
                "salones libres, consulta tu SAES, " +
                "precios de cafeterías, " +
                "papelería y mucho más...";
    }

    public String getNames(){
        return "• Espinosa de los Monteros Lechuga Jaime Daniel\n" +
                "• Esquivel Valdez Alberto\n" +
                "• Hernández Salinas Octavio Iván";
    }
}
