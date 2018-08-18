package mx.android.schoolapps.schoolmapp.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mx.android.schoolapps.schoolmapp.Activities.ClubsMapActivity;
import mx.android.schoolapps.schoolmapp.Adapters.ClubsAdapter;
import mx.android.schoolapps.schoolmapp.Models.Club;
import mx.android.shcoolapps.schoolmap.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ClubsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClubsFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private List<Club> clubsList;
    private ListView clubsListView;
    private ClubsAdapter clubsAdapter;

    public ClubsFragment() {
        // Required empty public constructor
    }

    public static ClubsFragment newInstance(String param1, String param2) {
        ClubsFragment fragment = new ClubsFragment();
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
        View view= inflater.inflate(R.layout.fragment_clubs, container, false);

        clubsList= getClubs();

        FloatingActionButton fab= view.findViewById(R.id.fabMapClubs);
        clubsListView= view.findViewById(R.id.listViewClub);
        clubsAdapter= new ClubsAdapter(getContext(), R.layout.clubs_adapter_listview, clubsList);

        clubsListView.setAdapter(clubsAdapter);

        clubsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Club club= clubsAdapter.getItem(position);

                AlertDialog.Builder days= new AlertDialog.Builder(getContext());
                days.setTitle(club.getName());
                days.setMessage("HORARIO\n" + club.getSchedule() + "\n\nCONTACTO\n" + club.getContactInfo());

                days.setPositiveButton("Mandar Solicitud", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(club.getGmailInfo().isEmpty())
                            Toast.makeText(getContext(), "No cuenta con correo para ser contactado",
                                    Toast.LENGTH_SHORT).show();
                        else{
                            Intent sendMailTo= new Intent(Intent.ACTION_SENDTO,
                                    Uri.fromParts("mailto",club.getGmailInfo(),null));
                            sendMailTo.putExtra(Intent.EXTRA_SUBJECT,"Solicitud para unirme al club");
                            sendMailTo.putExtra(Intent.EXTRA_TEXT,getDefaultMailText());

                            startActivity(Intent.createChooser(sendMailTo,"Elige GMail para contactar con el Club"));
                        }
                    }
                });
                days.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                days.show();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getContext(), ClubsMapActivity.class);

                startActivity(intent);
            }
        });

        return view;
    }

    public String getDefaultMailText(){
        return "Buen día, el club me llamo mucho la atención, dónde podría conseguir más información" +
                " o cómo podría unirme a él?";
    }

    public List<Club> getClubs()
    {
        return new ArrayList<Club>()
        {
            {
                add(new Club("Algoritmia",
                        "M. en C. Edgardo Adrián Franco Martínez",
                        "Sin Asignar",
                        "Sin Asignar",
                        "Sin Asignar",
                        ""));
                add(new Club("Matemáticas",
                        "M. en C. Darwin Gutiérrez Mejía",
                        "Laboratorio de Física (Sala 04)",
                        "Martes: 13:30-15:00\n" +
                                "Viernes: 13:30-15:00",
                        "Sin Asignar",
                        ""));
                add(new Club("Desarrollo de Videojuegos",
                        "M. en C. David Araujo Díaz y Juan Manuel Navarro Ramírez",
                        "Salón 2110 y Laboratorio de Programación 1 (1107)",
                        "Miércoles (Salón 2110): 13:30-15:00\n" +
                                "Jueves (Salón 1107): 13:30-15:00\n" +
                                "Viernes (Salón 1107): 12:00-15:00",
                        "Sin Asignar",
                        ""));
                add(new Club("Bio-Robótica",
                        "M. en C. Gabriela López Ruiz, Pacheco Bautista Gerardo y Alameda Poblano Alexis",
                        "Laboratorios de Sistemas 3 y 4",
                        "Matutino (Lab. 4): 10:00-12:00\nVespertino(Lab. 3): 16:30-18:00",
                        "Facebook: Club Bio-Robótica ESCOM\n" +
                                "Gmail: bio.robotica.escom@gmail.com\n" +
                                "Página Web: http://www.comunidad.escom.ipn.mx/biorobotica/",
                        "bio.robotica.escom@gmail.com"));
                add(new Club("Seguridad y Hackers Éticos",
                        "M. en C. Gilberto Sá:nchez Quintanilla",
                        "Sin Asignar",
                        "Sin Asignar",
                        "Sin Asignar",
                        ""));
                add(new Club("Minirobótica",
                        "M. en C. Edgardo Adrián Franco Martínez",
                        "Sin Asignar",
                        "Sin Asignar",
                        "Sin Asignar",
                        ""));
                add(new Club("Acapella","Diana Leticia Duran Ortiz y Roberto Sánchez  Veloz",
                        "Jardinera junto a barras, detrás del edificio 1",
                        "Por Asignar",
                        "Whatsapp: 55-15-33-73-02\n" +
                                "Facebook: ACAPELLA ESCOM",
                        ""));
                add(new Club("SUCEM (Arte Marcial Mexicano)","Joel Brandon Jiménez Hernández ",
                        "Área de Barras de ESCOM",
                        "Por Asignar",
                        "Whatsapp: 55-41-76-80-08\n" +
                                "Facebook: SUCEM ESCOM\n" +
                                "Página Web: www.sucem.com.mx",
                        ""));
                add(new Club("Taekwondo","Jorge Antonio Soriano Alvarez",
                        "Domo cerca de las áreas verdes,detrás del edificio 1",
                        "Por Asignar",
                        "Whatsapp: 55-31-56-62-64\n" +
                                "Facebook: CLUB DE TAE-KWON-DO ESCOM",
                        ""));
                add(new Club("Cultura e Idioma Japonés",
                        "M. en C. Rafael Norman Saucedo Delgado y Jorge Armando Solis Solis",
                        "Sin salón fijo, se usan salones 2109 y 2110",
                        "Por Asignar",
                        "Teléfono: 55-68-18-71-18\n" +
                                "Facebook: ClubJaponesESCOM\n" +
                                "Twitter: @JaponesESCOM",
                        ""));
                add(new Club("Titanes Tocho Bandera ","José Rafael Canchola Angeles",
                        "Campo frente a la cafetería",
                        "Por Asignar",
                        "Teléfono: 55 3936 1975\n" +
                                "Facebook: Flag Escom",
                        ""));
                add(new Club("Improvisación",
                        "Diana Alejandra Huerta Alvarez",
                        "Áreas verdes detrás de edificio 1",
                        "Por Asignar",
                        "Whatsapp: 55-43-73-91-49\n" +
                                "Facebook: Club de impro ESCOM ",
                        ""));
            }
        };
    }

}
