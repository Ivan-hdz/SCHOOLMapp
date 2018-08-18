package mx.android.schoolapps.schoolmapp.Activities;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import mx.android.schoolapps.schoolmapp.Adapters.ClassroomCentralBuildingAdapter;
import mx.android.schoolapps.schoolmapp.Models.ClassroomCentralBuilding;
import mx.android.shcoolapps.schoolmap.R;

public class ClassroomMidBuilding extends AppCompatActivity {

    private ClassroomCentralBuildingAdapter classroomAdapter;
    private List<ClassroomCentralBuilding> classroomList;
    private int floor= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom_mid_building);

        final Toolbar toolbar= findViewById(R.id.toolbarClassroomThirdFloor);
        toolbar.setTitle("Salones - Planta Baja");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        final FloatingActionButton upArrow= findViewById(R.id.fabMidBuildingUpArrow);
        final FloatingActionButton downArrow= findViewById(R.id.fabMidBuildingDownArrow);

        upArrow.setAlpha(.8f);
        downArrow.setAlpha(.8f);

        classroomList= getClassrooms_0Floor();

        final GridView classroomsGridView= findViewById(R.id.gridViewClassroomsThirdFloor);
        classroomAdapter= new ClassroomCentralBuildingAdapter(getApplicationContext(),
                R.layout.classroom_adapter_gridview, classroomList);

        classroomsGridView.setVerticalScrollBarEnabled(false);
        classroomsGridView.setAdapter(classroomAdapter);

        downArrow.setVisibility(View.INVISIBLE);

        upArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(floor==0){
                    toolbar.setTitle("Salones - Primer Piso");
                    classroomList= getClassrooms_1Floor();
                    classroomAdapter= new ClassroomCentralBuildingAdapter(getApplicationContext(),
                            R.layout.classroom_adapter_gridview, classroomList);
                    classroomsGridView.setAdapter(classroomAdapter);

                    downArrow.setVisibility(View.VISIBLE);
                    floor+= 1;
                }else if (floor==1){
                    toolbar.setTitle("Salones - Segundo Piso");
                    classroomList= getClassrooms_2Floor();
                    classroomAdapter= new ClassroomCentralBuildingAdapter(getApplicationContext(),
                            R.layout.classroom_adapter_gridview, classroomList);
                    classroomsGridView.setAdapter(classroomAdapter);

                    floor+= 1;
                    upArrow.setVisibility(View.INVISIBLE);
                }
            }
        });

        downArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (floor==1){
                    toolbar.setTitle("Salones - Planta Baja");
                    classroomList= getClassrooms_0Floor();
                    classroomAdapter= new ClassroomCentralBuildingAdapter(getApplicationContext(),
                            R.layout.classroom_adapter_gridview, classroomList);
                    classroomsGridView.setAdapter(classroomAdapter);

                    floor-= 1;
                    downArrow.setVisibility(View.INVISIBLE);
                }else{
                    toolbar.setTitle("Salones - Primer Piso");
                    classroomList= getClassrooms_1Floor();
                    classroomAdapter= new ClassroomCentralBuildingAdapter(getApplicationContext(),
                            R.layout.classroom_adapter_gridview, classroomList);
                    classroomsGridView.setAdapter(classroomAdapter);

                    floor-= 1;
                    upArrow.setVisibility(View.VISIBLE);
                }
            }
        });

        classroomsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder description= new AlertDialog.Builder(ClassroomMidBuilding.this);
                description.setTitle(classroomAdapter.getItem(position).getId());
                description.setMessage(classroomAdapter.getItem(position).getDescription());

                description.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                description.show();
            }
        });
    }

    private ArrayList<ClassroomCentralBuilding> getClassrooms_0Floor(){
        return new ArrayList<ClassroomCentralBuilding>(){
            {
                add(new ClassroomCentralBuilding("Mini-Robótica",
                        "",
                        "Club de Mini-robótica"));
                add(new ClassroomCentralBuilding("Lab. 1 de Circuitos",
                        "",
                        "Horario por Asignar"));
                add(new ClassroomCentralBuilding("Lab. 2 de Circuitos",
                        "",
                        "Horario por Asignar"));
                add(new ClassroomCentralBuilding("Biblioteca",
                        "",
                        "Biblioteca de la ESCOM"));
            }
        };
    }
    private ArrayList<ClassroomCentralBuilding> getClassrooms_1Floor(){
        return new ArrayList<ClassroomCentralBuilding>(){
            {
                add(new ClassroomCentralBuilding("Jefatura",
                        "",
                        "Por definir"));
                add(new ClassroomCentralBuilding("Subdirección Académica",
                        "",
                        "Por definir"));
                add(new ClassroomCentralBuilding("Sala 14",
                        "",
                        "Por definir"));
                add(new ClassroomCentralBuilding("Depto. Ing. Sist. Compt.",
                        "",
                        "Departamento de Ingeniería en Sistemas Computacionales\n" +
                                "(Sala de Profesores)"));
                add(new ClassroomCentralBuilding("Laboratorio 12",
                        "",
                        "Por definir"));
                add(new ClassroomCentralBuilding("Depto. Ciencias e Ing. Comp.",
                        "",
                        "Departamento de Ciencias e Ingeniería de la Computación"));
                add(new ClassroomCentralBuilding("Depto. de Ciencias Sociales",
                        "",
                        "Por definir"));
                add(new ClassroomCentralBuilding("Laboratorio de Digitales 1",
                        "",
                        "Por definir"));
                add(new ClassroomCentralBuilding("Laboratorio de Digitales 2",
                        "",
                        "Por definir"));
            }
        };
    }
    private ArrayList<ClassroomCentralBuilding> getClassrooms_2Floor(){
        return new ArrayList<ClassroomCentralBuilding>(){
            {
                add(new ClassroomCentralBuilding("ALEE",
                        "",
                        "Por definir"));
                add(new ClassroomCentralBuilding("Prefectura",
                        "",
                        "Se viene aquí por el cañón"));
                add(new ClassroomCentralBuilding("Laboratorio 24",
                        "",
                        "Sala de Profesores"));
                add(new ClassroomCentralBuilding("21N",
                        "",
                        "Salón para TT (Trabajo Terminal)"));
                add(new ClassroomCentralBuilding("21S",
                        "",
                        "Salón para TT (Trabajo Terminal)"));
                add(new ClassroomCentralBuilding("22N",
                        "",
                        "Salón para TT (Trabajo Terminal)"));
                add(new ClassroomCentralBuilding("22S",
                        "",
                        "Salón para TT (Trabajo Terminal)"));
                add(new ClassroomCentralBuilding("23N",
                        "",
                        "Salón para TT (Trabajo Terminal)"));
                add(new ClassroomCentralBuilding("23S",
                        "",
                        "Sala de Profesores"));
                add(new ClassroomCentralBuilding("25N",
                        "",
                        "Salón para TT (Trabajo Terminal)"));
                add(new ClassroomCentralBuilding("25S",
                        "",
                        "Salón para TT (Trabajo Terminal)"));
                add(new ClassroomCentralBuilding("26N",
                        "",
                        "Salón para TT (Trabajo Terminal)"));
                add(new ClassroomCentralBuilding("26S",
                        "",
                        "Sala de Profesores"));
            }
        };
    }
}
