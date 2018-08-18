package mx.android.schoolapps.schoolmapp.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import mx.android.schoolapps.schoolmapp.Adapters.ClassroomAdapter;
import mx.android.schoolapps.schoolmapp.Adapters.ScheduleListAdapter;
import mx.android.schoolapps.schoolmapp.Database.ScheduleContract;
import mx.android.schoolapps.schoolmapp.Database.ScheduleDBHelper;
import mx.android.schoolapps.schoolmapp.Models.Classroom;
import mx.android.schoolapps.schoolmapp.Models.Schedule;
import mx.android.shcoolapps.schoolmap.R;

public class ClassroomFirstBuilding extends AppCompatActivity {

    private ClassroomAdapter classroomAdapter;
    private List<Classroom> classroomList;
    private String classroomSchedule= "";
    private ProgressDialog mProgressDialog;
    private int floor= 0;

    private ScheduleListAdapter scheduleListAdapter;
    private ScheduleDBHelper scheduleDBHelper= new ScheduleDBHelper(this);
    private GridView scheduleListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom_first_building);

        final Toolbar toolbar = findViewById(R.id.toolbarClassroomFirstFloor);
        toolbar.setTitle("Salones - Primer Edificio");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        scheduleListView= findViewById(R.id.gridViewClassroomsFirstBuilding);
        getSchedulesFromDB();

        registerForContextMenu(scheduleListView);

        /*
        final FloatingActionButton upArrow= findViewById(R.id.fabFirstBuildingUpArrow);
        final FloatingActionButton downArrow= findViewById(R.id.fabFirstBuildingDownArrow);

        upArrow.setAlpha(.8f);
        downArrow.setAlpha(.8f);

        classroomList = getClassrooms_0Floor();

        final Button switchTurn = findViewById(R.id.buttonSwitchClassroomFirstFloor);
        final GridView classroomsGridView = findViewById(R.id.gridViewClassroomsFirstFloor);
        classroomAdapter = new ClassroomAdapter(this,
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
                    classroomAdapter= new ClassroomAdapter(getApplicationContext(),
                            R.layout.classroom_adapter_gridview, classroomList);
                    classroomsGridView.setAdapter(classroomAdapter);

                    downArrow.setVisibility(View.VISIBLE);
                    floor+= 1;
                }else if (floor==1){
                    toolbar.setTitle("Salones - Segundo Piso");
                    classroomList= getClassrooms_2Floor();
                    classroomAdapter= new ClassroomAdapter(getApplicationContext(),
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
                    classroomAdapter= new ClassroomAdapter(getApplicationContext(),
                            R.layout.classroom_adapter_gridview, classroomList);
                    classroomsGridView.setAdapter(classroomAdapter);

                    floor-= 1;
                    downArrow.setVisibility(View.INVISIBLE);
                }else{
                    toolbar.setTitle("Salones - Primer Piso");
                    classroomList= getClassrooms_1Floor();
                    classroomAdapter= new ClassroomAdapter(getApplicationContext(),
                            R.layout.classroom_adapter_gridview, classroomList);
                    classroomsGridView.setAdapter(classroomAdapter);

                    floor-= 1;
                    upArrow.setVisibility(View.VISIBLE);
                }
            }
        });

        switchTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchTurn.getText().equals("Matutino")) {
                    switchTurn.setText(String.valueOf("Vespertino"));
                } else {
                    switchTurn.setText(String.valueOf("Matutino"));
                }
            }
        });

        classroomsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                String hasDescription = classroomAdapter.getItem(position).getDescription();
                String hasSchedule = classroomAdapter.getItem(position).getMorningSchedule();

                if (hasDescription.isEmpty() && hasSchedule.isEmpty()) {
                    AlertDialog.Builder nothingAlert = new AlertDialog.Builder(ClassroomFirstBuilding.this);
                    nothingAlert.setTitle(String.valueOf("Salón: " + classroomAdapter.getItem(position).getId()));
                    nothingAlert.setMessage("Horarios aún sin Asignar");

                    nothingAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    nothingAlert.show();

                } else if (hasDescription.isEmpty()) {
                    final AlertDialog.Builder ImageDialog = new AlertDialog.Builder(ClassroomFirstBuilding.this);
                    ImageDialog.setTitle(String.valueOf("Salón: " + classroomAdapter.getItem(position).getId()));

                    ImageDialog.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                        }
                    });

                    final ZoomageView showZoomImage = new ZoomageView(ClassroomFirstBuilding.this);

                    if (switchTurn.getText().equals("Matutino"))
                        classroomSchedule = classroomAdapter.getItem(position).getMorningSchedule();
                    else
                        classroomSchedule = classroomAdapter.getItem(position).getEveningSchedule();

                    mProgressDialog = new ProgressDialog(ClassroomFirstBuilding.this);
                    mProgressDialog.setTitle("Por favor espera");
                    mProgressDialog.setMessage("Descargando...");
                    mProgressDialog.setCancelable(false);
                    mProgressDialog.setIndeterminate(false);
                    mProgressDialog.show();

                    Picasso.with(ClassroomFirstBuilding.this)
                            .load(classroomSchedule)
                            .into(showZoomImage,new com.squareup.picasso.Callback() {
                                @Override
                                public void onSuccess() {
                                    mProgressDialog.dismiss();
                                    ImageDialog.setView(showZoomImage);
                                    ImageDialog.show();
                                }

                                @Override
                                public void onError() {
                                    mProgressDialog.dismiss();

                                    if (!Utils.isNetworkAvailable(getApplicationContext()))
                                        Picasso.with(getApplicationContext())
                                                .load(classroomSchedule)
                                                .networkPolicy(NetworkPolicy.OFFLINE)
                                                .into(showZoomImage);

                                    ImageDialog.setTitle("Error al descargar");
                                    ImageDialog.setMessage("Asegúrate de tener conexión a Internet.");
                                    ImageDialog.show();
                                }
                            });

                } else {
                    AlertDialog.Builder descriptionAlert = new AlertDialog.Builder(ClassroomFirstBuilding.this);
                    descriptionAlert.setTitle(String.valueOf("Salón: " + classroomAdapter.getItem(position).getId()));

                    descriptionAlert.setMessage(hasDescription);

                    descriptionAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    descriptionAlert.show();
                }
            }
        });
        */
    }

    public void getSchedulesFromDB(){
        SQLiteDatabase sqLiteDatabase= scheduleDBHelper.getReadableDatabase();

        String sortOrder= ScheduleContract.ScheduleColumns.CLASSROOM + " ASC";

        Cursor cursor= sqLiteDatabase.query(ScheduleContract.ScheduleColumns.TABLE_NAME,null,
                null,null,null,null,sortOrder,null);

        ArrayList<Schedule> scheduleList= new ArrayList<>();

        while(cursor.moveToNext()){
            String nombreProfesor= cursor.getString(ScheduleContract.ScheduleColumns.NOMBRE_PROFESOR_COLUMN_INDEX);
            String grupo= cursor.getString(ScheduleContract.ScheduleColumns.GRUPO_COLUMN_INDEX);
            String nombreAsignatura= cursor.getString(ScheduleContract.ScheduleColumns.NOMBRE_ASIGNATURA_COLUMN_INDEX);
            String classroom= cursor.getString(ScheduleContract.ScheduleColumns.CLASSROOM_COLUMN_INDEX);
            String claveLab= cursor.getString(ScheduleContract.ScheduleColumns.CLAVE_LAB_COLUMN_INDEX);
            String lunes= cursor.getString(ScheduleContract.ScheduleColumns.LUNES_COLUMN_INDEX);
            String martes= cursor.getString(ScheduleContract.ScheduleColumns.MARTES_COLUMN_INDEX);
            String miercoles= cursor.getString(ScheduleContract.ScheduleColumns.MIERCOLES_COLUMN_INDEX);
            String jueves= cursor.getString(ScheduleContract.ScheduleColumns.JUEVES_COLUMN_INDEX);
            String viernes= cursor.getString(ScheduleContract.ScheduleColumns.VIERNES_COLUMN_INDEX);

            scheduleList.add(new Schedule(nombreProfesor, grupo, nombreAsignatura, classroom, claveLab,
                    lunes, martes, miercoles, jueves, viernes));
        }
        cursor.close();

        fillSchedulesList(scheduleList);
    }

    public void getGroupFromDB(String searchGroup){
        SQLiteDatabase sqLiteDatabase= scheduleDBHelper.getReadableDatabase();

        long count= DatabaseUtils.queryNumEntries(sqLiteDatabase, ScheduleContract.ScheduleColumns.TABLE_NAME);
        System.out.println("There are " + count + " elements in the DB");

        // Filter results WHERE "title" = 'My Title'
        String selection= ScheduleContract.ScheduleColumns.GRUPO + " = ?";
        String selectionArgs[]= {searchGroup};

        // How you want the results sorted in the resulting Cursor
        String sortOrder= ScheduleContract.ScheduleColumns.NOMBRE_PROFESOR + " ASC";

        Cursor cursor= sqLiteDatabase.query(
                ScheduleContract.ScheduleColumns.TABLE_NAME, null, selection, selectionArgs, null,
                null, sortOrder);

        ArrayList<Schedule> scheduleList= new ArrayList<>();

        while(cursor.moveToNext()){
            String nombreProfesor= cursor.getString(ScheduleContract.ScheduleColumns.NOMBRE_PROFESOR_COLUMN_INDEX);
            String grupo= cursor.getString(ScheduleContract.ScheduleColumns.GRUPO_COLUMN_INDEX);
            String nombreAsignatura= cursor.getString(ScheduleContract.ScheduleColumns.NOMBRE_ASIGNATURA_COLUMN_INDEX);
            String classroom= cursor.getString(ScheduleContract.ScheduleColumns.CLASSROOM_COLUMN_INDEX);
            String claveLab= cursor.getString(ScheduleContract.ScheduleColumns.CLAVE_LAB_COLUMN_INDEX);
            String lunes= cursor.getString(ScheduleContract.ScheduleColumns.LUNES_COLUMN_INDEX);
            String martes= cursor.getString(ScheduleContract.ScheduleColumns.MARTES_COLUMN_INDEX);
            String miercoles= cursor.getString(ScheduleContract.ScheduleColumns.MIERCOLES_COLUMN_INDEX);
            String jueves= cursor.getString(ScheduleContract.ScheduleColumns.JUEVES_COLUMN_INDEX);
            String viernes= cursor.getString(ScheduleContract.ScheduleColumns.VIERNES_COLUMN_INDEX);

            scheduleList.add(new Schedule(nombreProfesor, grupo, nombreAsignatura, classroom, claveLab,
                    lunes, martes, miercoles, jueves, viernes));
        }
        cursor.close();

        fillSchedulesList(scheduleList);
    }

    private void fillSchedulesList(ArrayList<Schedule> schedulesList){

        schedulesList= filter(schedulesList);

        scheduleListAdapter= new ScheduleListAdapter(this, R.layout.schedule_view, schedulesList);
        scheduleListView.setAdapter(scheduleListAdapter);

        scheduleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Schedule selectedSchedule= scheduleListAdapter.getItem(position);

                Intent intent= new Intent(ClassroomFirstBuilding.this, GroupScheduleActivity.class);
                intent.putExtra("SelectedSchedule", selectedSchedule);

                startActivity(intent);
            }
        });
    }

    public ArrayList<Schedule> filter(final ArrayList<Schedule> schedulesList){
        ArrayList<Schedule> newSchedulesList= new ArrayList<>();

        for(int i= 0; i< schedulesList.size(); i++){
            if(schedulesList.get(i).getClassroom().startsWith("1")){
                if(newSchedulesList.size() > 0){
                    if(!schedulesList.get(i).getGrupo().equals(newSchedulesList.get(newSchedulesList.size() - 1).getGrupo()))
                        newSchedulesList.add(schedulesList.get(i));
                }else
                    newSchedulesList.add(schedulesList.get(i));
            }
        }
        return newSchedulesList;
    }

    private ArrayList<Classroom> getClassrooms_0Floor(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1001,
                        "",
                        "",
                        "Subdirección Administrativa"));
                add(new Classroom(1002,
                        "",
                        "",
                        "-Servicio Médico\n-Servicio Dental\n-Orientación Educativa\n-COSECOVI"));
                add(new Classroom(1003,
                        "",
                        "",
                        "Control de Asistencia"));
                add(new Classroom(1004,
                        "",
                        "",
                        "-Departamento de Recursos Financieros\n-Departamento de Recursos Materiales y Servicios\n" +
                                "-Departamento de Capital Humano"));
                add(new Classroom(1005,
                        "",
                        "",
                        "Centro de Lenguas Extranjeras (CELEX)"));
                add(new Classroom(1006,
                        "",
                        "",
                        "Salón del CELEX"));
                add(new Classroom(1007,
                        "",
                        "",
                        "Salón del CELEX"));
                add(new Classroom(1008,
                        "",
                        "",
                        "Unidad Tecnológica Educativa y Campus Virtual"));
                add(new Classroom(1009,
                        "",
                        "",
                        "Sala de Profesores"));
                add(new Classroom(1010,
                        "",
                        "",
                        "Recursos Materiales"));
                add(new Classroom(1011,
                        "https://tvmreq-bn1305.files.1drv.com/y4m4gO1QAFyaTAVcOGh-4uHkSwqR3HyWxMKKJG4" +
                                "tO98UcrniQ4U8NiBZAODjapBRLUh5nJP15MoGVCjnfyUyhfwpNBOielmR9SV4WxMS8AizGIcW7QSj54tcZlD" +
                                "XSSEcS7skGobhACEwzSFwM8DOWA48fF8T5R2XSkyAKa0wDgDCeyDctCiNYtUAvrth6avSydI9zEoLivjEr6dAu" +
                                "oPHlkp4AY8T1AXWFSJ5bEhl_KquWQ/1011M.png?psid=1",
                        "https://tqmreq-bn1305.files.1drv.com/y4mVEMUjxlP8eNEjI_bMwteGSJAZxKxdRt9GhbDl" +
                                "C5HQCZkww4q2e7rQ_OusUAU3KCRJ-vqiXQLBXbaNWEZ8ud4Ry_2lzPkxDA1Z4mfJMcodLlLY3j86oO_nobvjE" +
                                "nRCePwruoW5aXDwncvJ2IarabY7FKhHze_quDMjU9-IdU2slQrJyF8LGRRaNvRSfKg8EqSGnUP2W9IVWWkp87" +
                                "W8c1pMnySAvPBhp99UX-gR-RqBPY/1011V.png?psid=1",
                        ""));
                add(new Classroom(1012,
                        "https://upmreq-bn1305.files.1drv.com/y4moXBqVTGz3dv9pXE1W7cMtlFgrh1DdxcEsvTTSz" +
                                "6CvViBO4_xHurRs3ZESyoCyjbApxxLJqBUcgw6x0TjqDTORnSZ2PReh4CeWYjzpOA9EbqSXyBI_WRTKV2gEKU" +
                                "snT6GsxVHrXWafDMXo9f4q8kap4sdYs9Go0Rul5IFUUcUlfQeUVFYZ2aortxChb-N-qSN7ajnF_jU8-bwS8_t" +
                                "Z49ZIe1RLh5JYJtCMBp1NRgVnyw/1012M.png?psid=1",
                        "https://ufmreq-bn1305.files.1drv.com/y4mSxGnCcBQNGcyZX6Pnp7VLltQ6uyivWLiWsJBS" +
                                "T1qHX5Y6cLEI-iWahJljyW4EymgamZDKo1v7wU6p9R3M5_DXEXS4RDcJ9FcRR-UjOFR5aOzdYiJPdYZ61wi8" +
                                "Js1JRfln8lLgDfdpxnFO9Kyr6GX-mtC_CDJGtYVU29o-eHH_8ggTmCuPgTEKJ8Zt6N9whpkNkYgPGsXUuXje" +
                                "PuQ52744ZaMYypkE9CxykKjGZ5t_vM/1012V.png?psid=1",
                        ""));
                add(new Classroom(1013,
                        "https://uvmreq-bn1305.files.1drv.com/y4mNZa4gxJ9v0ACJb9qJ7Xe5jXr0BCcDwIx2YFqG" +
                                "IebxY21BsrlNPWddfvCZWA53aZrpXYYPeUCbeK_tsG-ty0aYRGv7bmDjZV1Tpa2X7uJHuZs-kv75xBQQoHbn" +
                                "f3J_8Dc82rZ2SyzTf05au_7Tg0tkGlY5jW5XjpwoWX6EgdVG6yO9n1kk2tgZ7Iv7A3qEtI28L3aaF0YBUJ8L" +
                                "TN4aTQigUcnxov6ataj7cYzppm6aHk/1013M.png?psid=1",
                        "https://uqmreq-bn1305.files.1drv.com/y4mctGlpcVp7stDbqA-pYnTMubrJzpFw7ites4j9" +
                                "0TQ6jztYgP5uo2NJbJKABk_TUQQ99h4IDzMy-lu0x6dt_RkwMTIYlwkCOHgBIf-FQtMSFe1dbzDp4tQ9RUk4" +
                                "H9-7So2d9wsuqD8Xe7LTrTucwyIaSiOh6QsofLaZP8hgNKJmvCtVuxDW0dWWXNIML_NOaJMaD_ossMPHNLZ" +
                                "04jHnXTCjiqXCccDTUiWUnBkEBq0jQc/1013V.png?psid=1",
                        ""));
                add(new Classroom(1014,
                        "https://vpmreq-bn1305.files.1drv.com/y4mBo-5i1uyk1uOmFl8puUhN-3at1_i-Ejc0mOc5" +
                                "ZFXuppTJMfe9eZ1L7ZPCTKQQ57NMoA24O9MnF10YqAlJpRXIOguWWVcMQQIN5aLt99IDi7C84N05snu-SucVL" +
                                "-MMJHxtycsL0zgV4GFXBriEa5Li02xj1TmQ3GuLxzj4dPqPinELQgB9fT6O8fpobtpC2lOpIzEG8gYCqI2fp_" +
                                "BrdIpxooNtVvC4o7dk29uZXs1rK8/1014M.png?psid=1",
                        "https://tpnasw-bn1305.files.1drv.com/y4m64eKGuHxPhQm7uHFihr35hRfF0Afv___q-2ouW" +
                                "dVdgKpwnSzD1C8_BWE5WWGWCLJW7STLDIXDfFpzRO5DoZbjm4rcuT9MNny-SSrAa329SFdHF-Ak03dchDPelE" +
                                "mY_YxBgq2MQ14SqSyDllohZ_nVLtecE-Zjcvabs7Giw59SgOPK05kS-uJsE5NxFOXNiVrPs7AWfdRsgwNFjyq" +
                                "Vs1yrJct9b_mohZ_SjRBgXLIwkk/1014V.png?psid=1",
                        ""));
            }
        };
    }
    private ArrayList<Classroom> getClassrooms_1Floor(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1101,
                        "",
                        "",
                        "Unidad de Informática"));
                add(new Classroom(1102,
                        "",
                        "",
                        "Sin Asignar"));
                add(new Classroom(1103,
                        "",
                        "",
                        "Sala de Impresiones"));
                add(new Classroom(1104,
                        "",
                        "",
                        "Laboratorio de Redes"));
                add(new Classroom(1105,
                        "",
                        "",
                        "Laboratorio de Sistemas 3"));
                add(new Classroom(1106,
                        "",
                        "",
                        "Laboratorio de Sistemas 4"));
                add(new Classroom(1107,
                        "",
                        "",
                        "Laboratorio de Programación 1"));
                add(new Classroom(1108,
                        "",
                        "",
                        "Dirección"));
                add(new Classroom(1109,
                        "",
                        "",
                        "Sala del Consejo Técnico Consultivo Escolar"));
                add(new Classroom(1110,
                        "https://sqlbka-bn1305.files.1drv.com/y4mgeKSJamkPEP8yTTZl-m" +
                                "Fpmg9zp4J2Fray4qEhrCiyGxivm-OIZSo8IKun4qVJXF0p_RE0mWekDYw_dVujqt39" +
                                "95G2HSZ3vvVJHFjCAAHhicfZe58-l3l-TndkgE-5FP7nokE04HiNRB0pD_d_hT5HWd" +
                                "ofoOQMIZflTl2m1ikFnrGC2bgnEwhjxXdmYglKIS5l4GPS6v-qH6Z23DPR4oc3NFBGU" +
                                "5ozf3GGB2qvMY0yiM/1110M.png?psid=1",
                        "https://tflbka-bn1305.files.1drv.com/y4mSZb7o-v4Z-pSAok1kgh2r" +
                                "cDovKOLFsJUqOq1iVDgbScNoTxGJxBW4EOgOQHELx0SMQYlSchyE_J-UpvEGi1oeZklS" +
                                "692HY1NXHHjxDNlq2_DP634kdoY9K490_TaZwX2yhFnesM-OUbX7elQe4KuRmGZlGRon3" +
                                "rrb9V_aZciF7Ta8oKXR7bN0gYEBy7_WLMpWu-FHtMvi_qf6wTSMIm0Bv4jSAxuc5C9Mcex" +
                                "0ojRE_U/1110V.png?psid=1",
                        ""));
                add(new Classroom(1111,
                        "https://tplbka-bn1305.files.1drv.com/y4maMovYa01A572idHTnfiRHMp" +
                                "LaJQ8nPs_dvqKgJBiDVHCdOgRKQ8j7eB2UHIySLSwl7jS9ehUy_iDbSIOzmDnnBefCN9JN" +
                                "ZBe6xs5oE_E6l021BAbcwXMhkjcnW21u9wGqv7AGGKk-yKMy4HRGOylFOLkFIlXYI7ja1I" +
                                "otXsTcMX6AH7D_exvCl_DN9upovf-eFo7_5x2Q1EsmUh5FyJFvoaDFCbxV3yLVxmTZIi-L" +
                                "Bk/1111M.png?psid=1",
                        "https://tvlbka-bn1305.files.1drv.com/y4mnBtXpeGfk8JldWVZ2ArSIK8l_" +
                                "cb-yY-XJLTAbfw8PRjP_EA4lQeDEFNCyGI4AzfapRKuoCY4RCnQ9tzyTz5BnwUw3P50Hn2qf" +
                                "mDvpj8zySpgSliZqGooA6pxfJvAIjUQy5h8TVRtL0lxlLrUNtQtCVEmMr9y7sTza-wfQO1vr" +
                                "t05wcrlUv_fzWl6o58IUkAO8vuwTP90ErfcZRVfVoBFq0YoIUtFfNjTAeB6h82L7VI/1111V" +
                                ".png?psid=1",
                        ""));
                add(new Classroom(1112,
                        "https://tqlbka-bn1305.files.1drv.com/y4mk8VMJ_LcJxJZqqahQ83b7Abph" +
                                "PK9RuPFMuNRjLhv4SG-CfuAjFbwODw8wo7g_bnuSazdqXLRPJxijhaVHLR06VL1L9VNooJLCl" +
                                "7lUYPML6twJDT3S0gG8GtmaK1H_k1hcLd11lALCQRF82KrMiLd83KRkKAI_7-VKZtDwHwPCvm" +
                                "ltfr-aCSocy9LFlnPWfyRJr5h4sWhVIK9PHAbQaSVU8sxzQEDLo94TNY-kXMB8ac/1112M.pn" +
                                "g?psid=1",
                        "https://uplbka-bn1305.files.1drv.com/y4m2W9zeOeWswPgMtcvNauwlK8VWcQ" +
                                "GKZoMdAThuHpSMmVE3RrNPs7TPZqmXksxX1m8YXTrVlO2lxeRWVYVU8VxvY47Mr11IHm60Swtg" +
                                "TM7w7FO3nHDoBP0tlROhnqXNGl-FLVo1RarasuDqBzT5-OpzL1MenzXqbPcNyR0EiEzXwdGjTg" +
                                "gfRaSorNNVcGM8cmpa4zYpw4IVJXHKNOHjtE_CBVar8glTXs0pnnw_-RStT4/1112V.png?psid=1",
                        ""));
                add(new Classroom(1113,
                        "https://uflbka-bn1305.files.1drv.com/y4mDVNr2de2hjiHbCb7zVcqkGQs1wV7" +
                                "Y9G1PgLINgEvQHQ2qn4kncqCIwHxENMbaXsyv9C9enh6dF45lyCAEMlFKOGmCs1odUL-YxrJkjxy" +
                                "uGfnGXmM-aFFKxKFRsKYxNBP_8qvJ-es1S9ZBU350oDXpTU4Uk1ueXmPCYVdk3tpIxrUhzaWGQBC" +
                                "Z4v3zmjFhq2Jty8KxsjYl22aTO_mfxpLPOhQS2oMrr2btzwrLMzkfYM/1113M.png?psid=1",
                        "https://uvlbka-bn1305.files.1drv.com/y4mrTxW9w4rBDeAJ92mBIPMhPlNxaQZM" +
                                "B22gKaBtUvYksV36yB8agLylgeEzQVboQhLO_ORBvkMopIEAdMaRANL0d5_MlrEvQoOoZQxX_vbQ" +
                                "shfHkad-IG_FZmRNOepOPLUXdgHNPOzA4xfbgO96QqAWM8iuZsrGMPBCm6z1BAjSGoSZg4-lxaTGc" +
                                "M_aGvuOLs6d0nYyNDBMhBP4SxqIulf6SkED7P7onJtuhQCxN1TzjI/1113V.png?psid=1",
                        ""));
                add(new Classroom(1114,
                        "",
                        "",
                        "Laboratorio de Proyectos de Investigación"));
            }
        };
    }
    private ArrayList<Classroom> getClassrooms_2Floor(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1201,
                        "",
                        "",
                        ""));
                add(new Classroom(1202,
                        "https://vpmkyg-bn1305.files.1drv.com/y4mHWYiAQsw0r6GOj-RPdyp" +
                                "NkG1MlTws_oOZX5u7cfPSmibNQs8Ci1AbeeOiyBEuwiKYF_-tMHNnDKin-9DhUN6D_Q-" +
                                "0I7BmpbzpVgWQx4-6NcwVdZ-e10U9RByT_2nnzK_vPTYdyAI_O5rusQsmWpxKHGuQ9yo" +
                                "b37TwiasAEVenIDxEj6j0lnQEMeyVW3sM6dhz3fmtpAoS9oKB7Kr4sMV9c_iq0J3CAHg" +
                                "-rPX9SlYWZg/1202M.PNG?psid=1",
                        "https://vpmkyg-bn1305.files.1drv.com/y4mN5_K-nB9_wEpbTuUctjk8" +
                                "eLlItffiwpFq4lJ6nHesvQwy7SyXQzYO5NJmNF24qCdpMrduTlgx8Z3vVvQHMEdmJcJf1" +
                                "ut70OFrdR1RGOzO3_vBdvzjneruJ1FfZDturpYABzKnWRulGYfyYscum-wi6PbAy_vdgc" +
                                "mAt-iVdQyV59MqZ4tSlzgNyp0aigEsKmYXuRXi0cRwXdIq9JwQ2CC7VJSNFO1I5AcTkIn" +
                                "MfuqqcE/1202V.PNG?psid=1",
                        ""));
                add(new Classroom(1203,
                        "https://vpmkyg-bn1305.files.1drv.com/y4mc0XsSB9EO34Zz4K7SGrZ460" +
                                "pAI6dKh210LxnV-BoeORycP5t5pNKbLXjkcoSD8rsg16pOsXplWtjyYOGaNW86R6FD9HD7" +
                                "40Tcx13vz79qUzAwncBWVgpIO6yorCES24waUed59GAZX4vU2ch6e0I3Y73ka3rdfSWfrT" +
                                "_FdfgXU1JCRT64bSsWKcViSXbd2aa9PZmoTg7uoVwXs4e_-XScYFbN3cCcMuY9q8z_0cbe" +
                                "kU/1203M.PNG?psid=1",
                        "https://vpmkyg-bn1305.files.1drv.com/y4m4JJY6I0F5FmA12vlGy0t7lkrI" +
                                "6HKs3QzEkismEz9sGr3jvEGpAXfsjHFyJiDc1MGqJvTKa0kcFJq3_Eaj9uufLMDP9cyZteXq" +
                                "y2Yj3c1hWODFgZFWi4ebkjxMfX28sK2w7bzpu8oIuKsvg9oIAfm1zaFYkJJ0xofoXVItlkaz" +
                                "iVOOw0-VvgSJid_K9Lqg3xUAc_r_rNB7cZ1w4BVQG50fB7fG6-ddaFPLHVxbQS8CNs/1203V" +
                                ".PNG?psid=1",
                        ""));
                add(new Classroom(1204,
                        "https://vpmkyg-bn1305.files.1drv.com/y4mU0zOuJv0imzCeMqFJAmIKJxiw3" +
                                "M6iXzFnz-PgKnbWY6v2GGmdRP5UiUfu16lhWKHG7IddbTnMsskb2ZMitwpy95xRlxJjlsS2-5S" +
                                "QthpwaU7U9MJlhSIsJwyS7GUoJykk474SRsyimmLwhdFDsNCw9P6ep2dYCJilj4TqJvFLZwNql" +
                                "dU2avYxkTuAWoI4oO07afNDM2EfweeBXklAuwFmkVLpSSg6hdI2XOJYYmcQ98/1204M.PNG?ps" +
                                "id=1",
                        "https://vpmkyg-bn1305.files.1drv.com/y4mk7lKnrw7bLDE-PtmHBCcV27aSR" +
                                "t_0viwmcJqMJGF_OC1bFtWyTsR0oG3isjyaEpGdC-4ZDlLJ2XaQaTj3wb5eClfnVcVYdGw2yk" +
                                "Z_wD8LHGVrmBR5zqulRG3qX7WT6-DQI2u-64WrYkHxvQ0E7vA7cbeeBH9VM0hCm0bzdVebrx4" +
                                "xnEbdyi2OFVkfHrhMlorMUqQgnn3YVzqHSyrl80Z708awflCnTLmc389q1QSFE8/1204V.PNG" +
                                "?psid=1",
                        ""));
                add(new Classroom(1205,
                        "https://vpmkyg-bn1305.files.1drv.com/y4m3D1XoawmcWGu5lPj-FgFRsQdZZ" +
                                "N26YnTf8VfPiCbbaegjTB4143FrDSk8geqMTe6lQSjI_5zbkk76I2YbA0VFBDHHx--vWmcGvC5" +
                                "uv3W7Umj88F0zlPmMAT_bMCxh0qs3M6gj17gMysFL4iAw1Nx2YwCL0jZr9pkmttfYr8Q5FkXoA" +
                                "ZIOn-zk0qL1ybHB4FpR7wjFxpH9oBMkippLc3tEeoTy2fBB_fRsjNpaOzpeIs/1205M.PNG?ps" +
                                "id=1",
                        "https://vpmkyg-bn1305.files.1drv.com/y4m36Jo2zgCci_AsR6JOncVpcykUVoi" +
                                "BeDTz6XvO7KM7cYgyHy_jLal3RXYskFIh2jIo5e8NEHpjoaEbOjK_7QT3C7M08D6P3CvXaDBHbv" +
                                "3ctiKKEhXncroc9ExJ3_2h1OJA8Yj_Cy473afCvRVURArmYp44U3bvwh7I7vmTMBX2KNAUDPE9k" +
                                "w_P7WzejJI7dgTbXkV_ShfNcLTt3-Y79e51MfPRAx6HFxHtStOY0uQQa0/1205V.PNG?psid=1",
                        ""));
                add(new Classroom(1206,
                        "https://vpmkyg-bn1305.files.1drv.com/y4mU1g88p-3g5tmbhSa6EMT1fZ-y5DZ" +
                                "JI2jiuVX2OIeyU8tB4aVu1puQs1BszN_Qod7pxzQk-Ek4READT-obFWrTNwxM7rWU5ZJTea2oyg" +
                                "8p9gsSt9-q78DwBeBoREES6U7B4CASeyQ_IrjDN_vn_tVQYG2zFxgXEQ8k8BQebHgR3fT-tyhJt" +
                                "ujWMKXY00jVKtpoSx9j9hL61idXJH5d_zCFSnIwqpz-Y2wC_Udpl1QKFs/1206M.PNG?psid=1",
                        "https://vpmkyg-bn1305.files.1drv.com/y4mDpJNCFP2hYh1PIHyjxvXvDbNCnViM" +
                                "wfotJgQdtP6joXXzfvR_CVbj4vIM1nQx5GuPIJf1OXZY4BGB9CnjgBrPZbNVylbJS8bNMxtIgcMl" +
                                "FiJLiA0yETxf0VtfILwq10yJ4D59ZZi4aE6BpAuopEHKQfdgmHjB-k9TCxkrBYJiDhFKPw26YdWr" +
                                "vvewPlvHEUJa9RfsA_zuSpc_MIWuQ_XYQ0m-QkCPVuwGAPGlbG4maQ/1206V.PNG?psid=1",
                        ""));
                add(new Classroom(1207,
                        "https://vpmkyg-bn1305.files.1drv.com/y4ml-immyyuSKwHQum2HnOFjR86RJShek" +
                                "0-7qbKdk7MlEPv9wgZn3vFEuc4-wDDJarW7akhWsanJy4W7iuEKPwehLiBf64pBa1YaREyN8h8SFQ" +
                                "8s3aVRhapuvg90u6d4WHIuDHBygrYsacOve9pIcAQ_KXHuFVPelB1FXIx4OlEvn27fE4SAEc4F65b" +
                                "3hCIIhAguJeBee69ioVQ39_pLql-lxam9TXWzGVaU4NEAVs-djc/1207M.PNG?psid=1",
                        "https://vpmkyg-bn1305.files.1drv.com/y4mBTpNS75DWOU5cbA2NFyHjnR2Gv0_6jP" +
                                "clhP3ziiOO8Bz9uKq5h4bjMsvzPIL_iCQyCPkkJU5oshzz_no7SK3j-xtFMzZuDhuGX-0NYlVj-K2" +
                                "taEp0Lq3SVonwTzaYwiwfw1V1aZFdiNPpEo3qPSxiUzn0Kf2GxIAEsKaXk4Or2o3-mBWA66L--NY0" +
                                "wxHlYVWWxd_dwOcJbAAX8JYynBidRBwpbfSvTwUGiB6rgXOcM8/1207V.PNG?psid=1",
                        ""));
                add(new Classroom(1208,
                        "",
                        "",
                        "Departamento de Extensión y Apoyo Educativo"));
                add(new Classroom(1209,
                        "https://vpmkyg-bn1305.files.1drv.com/y4mNsw0yKXyYNSVbLsQzZ1wy" +
                                "6G60j5V4wmshO3_JRMTpbI4fTybXyDzrnuxL0hlvOtyg814H3lQC_Cys00sJh_zDKNyd4" +
                                "1zJDFv1w7p0AXgLVlx8oxjEQq7fhvqJCUdbBriDH1DimtcYKblQO8z0JyhzbszMsc2a-o" +
                                "TRMN9LqtUHZA0kNLNvIp1hCRZ2q_HX4p4uI4Uc4aQjcMQibrO3_lt5BQEq2XAcS_ctE69o" +
                                "OXq1vE/1209M.PNG?psid=1",
                        "https://vpmkyg-bn1305.files.1drv.com/y4mP2xFKir10VMebVHS7y4URoU" +
                                "-piaHLAjpWCqUVS8TPuKhUXOURnNP_MEAmRAmPQdbT0i0LH8GARKVAVYgCtdPWi9IJIJ_1" +
                                "9bqYEb8GUMBE3ObuKY4f4Vr0b6kZUrKCZyVl8hNUG2AOlx4xUZfMoU7y4HKb8FcqNq_idRV" +
                                "g66AfP7fGteftC9EMpj3nUfWgtpprPxA3mt5FvXkH9eRXms3xSv4vAvH-UlQVqvq01G2sog" +
                                "/1209V.PNG?psid=1",
                        ""));
                add(new Classroom(1210,
                        "https://vpmkyg-bn1305.files.1drv.com/y4mxtT4PCiNkAbVbjb56YrvGWjuC" +
                                "ICqW6eqoagSimYOuxhhVenvFkb0010XlrYqZQKELH0mcCpt680kg7QU6uPcTj0xGz8ZhMmpw" +
                                "k5T3mQ5EyPvXD8tkIiEY6TY5AaUbXT11ysregCSv0x8zBVIv8v9d4c2VAWh-avrOGXtjGN7j" +
                                "l1fSy_Mt9mAb6lyu63n7wsCcF97api8wcLn90ZzEUDV7HI508do2TJTMPlEqBZZimI/1210M" +
                                ".PNG?psid=1",
                        "https://vpmkyg-bn1305.files.1drv.com/y4mA5Sc1px-qUKQRdk_KEnUi8OKz" +
                                "3DYwAWkCRHnT-h1P8psR4T2vUcuc_o6IIteegzefLp-9uhaSaX4n1nL_AUNhhHhzP-9UwOqR" +
                                "36WAo-n1-CKBRKev29O4ORHJRNDgBbhcGVELhfo_Hmyy0RTGxCCPs7cxz6rvo9jq-UvOB5YQ" +
                                "jMJZFzM0rf5piEGPeg5RUU7nfWj5PBXd-5oHnBtmEy1E8X7gQQtK-EOPilF3fV--n8/1210V" +
                                ".PNG?psid=1",
                        ""));
                add(new Classroom(1211,
                        "https://vpmkyg-bn1305.files.1drv.com/y4meUdrESvRCbBTODYfzWRUtCNXsV" +
                                "jBuB_XkzXVlbE4o5C3lTb9-1MvuTn-WwJikjQJreArnoVqIm4UphCfG2TZ9GthPiECtjXqQQq" +
                                "hWxlsXe_eUxKhzx2Z6kCwYa2Yl9FGRSaBBmzWAgHedMcuCUX5kHMs4F1kTGw4pC0vzSS6hq4y" +
                                "VfShP0PNIsnrZjAG8zWtgtqVoyTOttFydLKxG8Y71vdzZxCSaPaJASr0SLa0bCI/1211M.PNG" +
                                "?psid=1",
                        "https://vpmkyg-bn1305.files.1drv.com/y4miyAeKYDEGXvo3MW2J8l6FzQPFH" +
                                "rnRI3bffIjyCzf2RoU6joFgaNMkIHZh0Esvq0CQsSyWq_fRd25WBPxBUwUg1MZ2Pt7Cn3F9kw" +
                                "2-UppB1TDKht7SgqYwZK-qx5IkTZQa9K2rso4Ujwd39NVBBUCUd5SS2KdKdURB05Tgq8Rlg6F" +
                                "a2NamTIczrJ_g8b0TbuG7NU__vBfRkt8BSaQ6WhOQPwaCwjs7MzvtxUD-UwZMTc/1211V.PN" +
                                "G?psid=1",
                        ""));
                add(new Classroom(1212,
                        "https://vpmkyg-bn1305.files.1drv.com/y4muxpq78aLGk4gssT3KUTAGOFL_" +
                                "JdwV2p17a-ugY2jV-fkMsokX59oM0rjJ8MkXHDcahPSbJUUWj5_G5X2Lu6LsewAzQSdkGAcVr" +
                                "2cy6n49hnoYBMSD2sCDZi5etCx5EpcTouy-F38PCHRuuMhMf8blSr428CVWty5MHzio-AuwQg" +
                                "9Fy2bTjgJLgc2Ffz3qz0cGEZcl8q9ly4uj5ZEW9vprbV8rTmhzTW527cY-sgp8I8/1212M.PN" +
                                "G?psid=1",
                        "https://vpmkyg-bn1305.files.1drv.com/y4mCGzGBYKsDthjInHggrY5GFAadAL" +
                                "lrGigSgdDaA0WNzLfi_fgOJwI5tZOF88j9VXFSxRHYMpCwsGn28BU_49jsN1XXDzXMIVB5m7Fh" +
                                "bmpG3w7sqhT2L9atbLYsmTecyBiwlnPJe8T0Frff2IjN7Us_FHN4KM9_U7I4BQ2kJ1aPu5H0vK" +
                                "0J5sw-bYkFhAf4-HvVb3FBGGSCPfsVpLE0Kes-H61QX1IAp7vgnSzrwUmW64/1212V.PNG?psi" +
                                "d=1",
                        ""));
                add(new Classroom(1213,
                        "https://vpmkyg-bn1305.files.1drv.com/y4mXJbLhWfPrcjMIQ3t_ezmq-Gw_s9" +
                                "ZW-QG0ltEGxh-tVSR2noGMuk7NdgNY1JnRCQH9ciiwZRO8jku8TguMxuBxs5KYyUgSjAGRlibyA" +
                                "5Kyr93Ikzj8rml3pb-CWHX7IsCndFcO249ee2vNGTfnRsUIQaPRBL2NdPondx60Yk7egP3Cnszh" +
                                "TjWIBJmsmoq5uQelMKyr6Obj8S0pJJ014eHLtI8VpVoTmDea2edIbOA4TM/1213M.PNG?psid=1",
                        "https://vpmkyg-bn1305.files.1drv.com/y4mwIvxcdV6HUEfO4sJ-bKbHVN2w-ljC" +
                                "qxaO4lUY1TVtCGaFU_44Tfsl3HYuOWHjG1Vlx_f81dae2gwcnxaF3QYVc35NYwMdO9uaVveF33QS" +
                                "7L3iifURuBtHRndQBxR8PUmquOBjwaRcJ-Vt8SuprAMXtJxvZ0awoZfgAcFOFvEBIyopc6rCI7W7" +
                                "QI3ivTHT9x9_YKb-Rr0nDXdwiKGWsc1RccsaUpjiDKq4OyzJVaQRTY/1213V.PNG?psid=1",
                        ""));
                add(new Classroom(1214,
                        "https://vpmkyg-bn1305.files.1drv.com/y4m3-WgbZb6hZhda6wzNQxdkHoyh3GH8" +
                                "QJX_G0ilrwusEtwgg13eZrA3AZPNDrmtxn_SleDElbKJoTn8JesDXhd5isDqaXRPdl4gAoECD0ut" +
                                "1ax4pf2oN6DiOSzvrZE0CZqjSQPvrwb2gtsZeEhfNpdC6UkKyHbajiOvw_nyHV9Sqn8JLjoAAW0l" +
                                "9lw4UYxo5fKT0AS20ot5ebvzhJrPvQF7cgGBnICtU5mVBYIjlNaNvU/1214M.PNG?psid=1",
                        "https://vpmkyg-bn1305.files.1drv.com/y4mJihCw_GWuke58J5aQsqhbpn0zsjED-" +
                                "dyKKucffzM0o3AYW5V4QMOYWQN38ZUB9FwmjWmEyW1pnspbGItmT0BWvh9hrWVcWPh9tcreDNMNgw" +
                                "whousnpsr0ytnVQ9nUaJgY76zTQuKe-JTwOwZy9XoPRHed2U-h-NA487QVqdvOFtRqrwiyKq6omRG" +
                                "NrCGXl1Em6GUyMwowy-nLdwk6p7wzRwA1sD6xaeQTwm6cWaz95c/1214V.PNG?psid=1",
                        ""));
            }
        };
    }
}
