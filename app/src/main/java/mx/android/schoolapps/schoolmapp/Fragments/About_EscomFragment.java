package mx.android.schoolapps.schoolmapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mx.android.shcoolapps.schoolmap.R;

public class About_EscomFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    public About_EscomFragment() {
    }

    public static About_EscomFragment newInstance(String param1, String param2) {
        About_EscomFragment fragment = new About_EscomFragment();
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
        View view= inflater.inflate(R.layout.fragment_about__escom, container, false);

        TextView contentMissionEscom= view.findViewById(R.id.contentMissionAboutEscom);
        TextView contentVisionEscom= view.findViewById(R.id.contentVisionAboutEscom);
        TextView contentHistoryEscom= view.findViewById(R.id.contentHistoryAboutEscom);
        TextView contentShieldEscom= view.findViewById(R.id.contentShieldAboutEscom);

        contentMissionEscom.setText(getTextMission());
        contentVisionEscom.setText(getTextVision());
        contentHistoryEscom.setText(getTextHistory());
        contentShieldEscom.setText(getTextShield());


        return view;
    }

    public String getTextMission(){
        return "Formar profesionales líderes en saberes de ingeniería, tecnología y ciencias, de la " +
                "computación, con una visión globalizada; así como contribuir con investigación y " +
                "desarrollo tecnológico para el crecimiento del país.";
    }

    public String getTextVision(){
        return "Ser la Unidad Académica, líder en la formación de profesionales en ingeniería, tecnología " +
                "y ciencias, de la computación, con base en un proceso educativo integral, incluyente y eficiente, " +
                "que responda a su compromiso social.";
    }

    public String getTextHistory(){
        return "El arribo de las computadoras digitales a México en los años cincuenta, además de introducir una " +
                "herramienta para el procesamiento más rápido de datos, propició el inicio del desarrollo de formación " +
                "de los recursos humanos para la explotación de éste recurso tecnológico.";
    }

    public String getTextShield(){
        return "En el año de 1994 dos estudiantes de licenciatura en Diseño Gráfico de la UAM-Azcapotzalco, apegados a " +
                "los lineamientos, generaron logotipos e imágenes, resultando ganador el presentado por la C. Guadalupe " +
                "Gómez Sánchez, durante el Simposium Tecno-Industria ESCOM-95.\n" +
                "\n" +
                "En la séptima reunión ordinaria del XVI CTCE de la ESCOM, celebrada el 30 de marzo de 2011, ante la " +
                "presencia del Ing. Apolinar Francisco Cruz Lázaro, director de la escuela, se aprobó por mayoría la " +
                "inclusión en el logotipo representativo del plantel la leyenda \"Instituto Politécnico Nacional\", " +
                "sin modificar ninguno de los otros elementos.";
    }
}
