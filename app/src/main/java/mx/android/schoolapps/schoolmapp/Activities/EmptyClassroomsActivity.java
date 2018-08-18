package mx.android.schoolapps.schoolmapp.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import mx.android.schoolapps.schoolmapp.Adapters.ClassroomAdapter;
import mx.android.schoolapps.schoolmapp.Database.ScheduleContract;
import mx.android.schoolapps.schoolmapp.Database.ScheduleDBHelper;
import mx.android.schoolapps.schoolmapp.Models.Schedule;
import mx.android.shcoolapps.schoolmap.R;

public class EmptyClassroomsActivity extends AppCompatActivity {

    private ClassroomAdapter adapter;
    private GridView scheduleListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        Toolbar toolbar= findViewById(R.id.toolbarEmptyClassroomsActivity);
        toolbar.setTitle("Salones Libres");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        scheduleListView= findViewById(R.id.gridViewEmptyClassroomsActivity);
        getSchedulesFromDB();

        registerForContextMenu(scheduleListView);
    }

    public static String getCurrentTime_Hour(){
        long time= System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("HH", Locale.getDefault());
        Date date= new Date(time);

        return simpleDateFormat.format(date);
    }
    public static String getCurrentTime_Minutes(){
        long time= System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("mm", Locale.getDefault());
        Date date= new Date(time);

        return simpleDateFormat.format(date);
    }

    public void getSchedulesFromDB(){
        ScheduleDBHelper scheduleDBHelper= new ScheduleDBHelper(this);
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

    private void fillSchedulesList(ArrayList<Schedule> schedulesList){

        schedulesList= filter(schedulesList);

        adapter= new ClassroomAdapter(this, R.layout.classroom_adapter_gridview, schedulesList, "classroom");
        scheduleListView.setAdapter(adapter);

        scheduleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Schedule selectedSchedule= adapter.getItem(position);

                Intent intent= new Intent(EmptyClassroomsActivity.this, ClassroomScheduleActivity.class);
                intent.putExtra("SelectedSchedule", selectedSchedule);
                startActivity(intent);
            }
        });
    }

    public ArrayList<Schedule> filter(final ArrayList<Schedule> schedulesList){
        ArrayList<Schedule> newSchedulesList= new ArrayList<>();
        ArrayList<String> usedClassrooms= new ArrayList<>();

        Calendar calendar= Calendar.getInstance(Locale.getDefault());
        int day= calendar.get(Calendar.DAY_OF_WEEK);

        System.out.println("Day of week= " + day);

        int hour= Integer.parseInt(getCurrentTime_Hour());
        int minutes= Integer.parseInt(getCurrentTime_Minutes());

        switch (day){
            case 2: //Monday
                for(int i= 0; i< schedulesList.size(); i++){
                    if(hour < 7){
                        for(int j= 0; j< schedulesList.size(); j++){
                            if(newSchedulesList.size() > 0){
                                if(!newSchedulesList.get(newSchedulesList.size() - 1).getClassroom().
                                        equals(schedulesList.get(j).getClassroom())){
                                    newSchedulesList.add(schedulesList.get(j));
                                }
                            }else
                                newSchedulesList.add(schedulesList.get(j));
                        }
                        return newSchedulesList;
                    }else if(hour<=8 && minutes<=29){
                        if(schedulesList.get(i).getLunes().startsWith("7")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=9 && minutes<=59){
                        if(schedulesList.get(i).getLunes().startsWith("8")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=11 && minutes<=59){
                        if(schedulesList.get(i).getLunes().startsWith("10")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=13 && minutes<=29){
                        if(schedulesList.get(i).getLunes().startsWith("12")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=14 && minutes<=59){
                        if(schedulesList.get(i).getLunes().startsWith("13")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=16 && minutes<=29){
                        if(schedulesList.get(i).getLunes().startsWith("15")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=17 && minutes<=59){
                        if(schedulesList.get(i).getLunes().startsWith("16")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=19 && minutes<=59){
                        if(schedulesList.get(i).getLunes().startsWith("18")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=21 && minutes<=59){
                        if(schedulesList.get(i).getLunes().startsWith("20")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else{
                        for(int j= 0; j< schedulesList.size(); j++){
                            if(newSchedulesList.size() > 0){
                                if(!newSchedulesList.get(newSchedulesList.size() - 1).getClassroom().
                                        equals(schedulesList.get(j).getClassroom())){
                                    newSchedulesList.add(schedulesList.get(j));
                                }
                            }else
                                newSchedulesList.add(schedulesList.get(j));
                        }
                        return newSchedulesList;
                    }
                }
                break;
            case 3: //Tuesday
                for(int i= 0; i< schedulesList.size(); i++){
                    if(hour < 7){
                        for(int j= 0; j< schedulesList.size(); j++){
                            if(newSchedulesList.size() > 0){
                                if(!newSchedulesList.get(newSchedulesList.size() - 1).getClassroom().
                                        equals(schedulesList.get(j).getClassroom())){
                                    newSchedulesList.add(schedulesList.get(j));
                                }
                            }else
                                newSchedulesList.add(schedulesList.get(j));
                        }
                        return newSchedulesList;
                    }else if(hour<=8 && minutes<=29 && hour >= 7){
                        if(schedulesList.get(i).getMartes().startsWith("7")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=9 && minutes<=59){
                        if(schedulesList.get(i).getMartes().startsWith("8")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=11 && minutes<=59){
                        if(schedulesList.get(i).getMartes().startsWith("10")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=13 && minutes<=29){
                        if(schedulesList.get(i).getMartes().startsWith("12")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=14 && minutes<=59){
                        if(schedulesList.get(i).getMartes().startsWith("13")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=16 && minutes<=29){
                        if(schedulesList.get(i).getMartes().startsWith("15")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=17 && minutes<=59){
                        if(schedulesList.get(i).getMartes().startsWith("16")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=19 && minutes<=59){
                        if(schedulesList.get(i).getMartes().startsWith("18")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=21 && minutes<=59){
                        if(schedulesList.get(i).getMartes().startsWith("20")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else{
                        for(int j= 0; j< schedulesList.size(); j++){
                            if(newSchedulesList.size() > 0){
                                if(!newSchedulesList.get(newSchedulesList.size() - 1).getClassroom().
                                        equals(schedulesList.get(j).getClassroom())){
                                    newSchedulesList.add(schedulesList.get(j));
                                }
                            }else
                                newSchedulesList.add(schedulesList.get(j));
                        }
                        return newSchedulesList;
                    }
                }
                break;
            case 4: //Wednesday
                for(int i= 0; i< schedulesList.size(); i++){
                    if(hour < 7){
                        for(int j= 0; j< schedulesList.size(); j++){
                            if(newSchedulesList.size() > 0){
                                if(!newSchedulesList.get(newSchedulesList.size() - 1).getClassroom().
                                        equals(schedulesList.get(j).getClassroom())){
                                    newSchedulesList.add(schedulesList.get(j));
                                }
                            }else
                                newSchedulesList.add(schedulesList.get(j));
                        }
                        return newSchedulesList;
                    }else if(hour<=8 && minutes<=29 && hour >= 7){
                        if(schedulesList.get(i).getMiercoles().startsWith("7")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=9 && minutes<=59){
                        if(schedulesList.get(i).getMiercoles().startsWith("8")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=11 && minutes<=59){
                        if(schedulesList.get(i).getMiercoles().startsWith("10")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=13 && minutes<=29){
                        if(schedulesList.get(i).getMiercoles().startsWith("12")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=14 && minutes<=59){
                        if(schedulesList.get(i).getMiercoles().startsWith("13")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=16 && minutes<=29){
                        if(schedulesList.get(i).getMiercoles().startsWith("15")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=17 && minutes<=59){
                        if(schedulesList.get(i).getMiercoles().startsWith("16")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=19 && minutes<=59){
                        if(schedulesList.get(i).getMiercoles().startsWith("18")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=21 && minutes<=59){
                        if(schedulesList.get(i).getMiercoles().startsWith("20")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else{
                        for(int j= 0; j< schedulesList.size(); j++){
                            if(newSchedulesList.size() > 0){
                                if(!newSchedulesList.get(newSchedulesList.size() - 1).getClassroom().
                                        equals(schedulesList.get(j).getClassroom())){
                                    newSchedulesList.add(schedulesList.get(j));
                                }
                            }else
                                newSchedulesList.add(schedulesList.get(j));
                        }
                        return newSchedulesList;
                    }
                }
                break;
            case 5: //Thursday
                for(int i= 0; i< schedulesList.size(); i++){
                    if(hour < 7){
                        for(int j= 0; j< schedulesList.size(); j++){
                            if(newSchedulesList.size() > 0){
                                if(!newSchedulesList.get(newSchedulesList.size() - 1).getClassroom().
                                        equals(schedulesList.get(j).getClassroom())){
                                    newSchedulesList.add(schedulesList.get(j));
                                }
                            }else
                                newSchedulesList.add(schedulesList.get(j));
                        }
                        return newSchedulesList;
                    }else if(hour<=8 && minutes<=29 && hour >= 7){
                        if(schedulesList.get(i).getJueves().startsWith("7")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=9 && minutes<=59){
                        if(schedulesList.get(i).getJueves().startsWith("8")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=11 && minutes<=59){
                        if(schedulesList.get(i).getJueves().startsWith("10")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=13 && minutes<=29){
                        if(schedulesList.get(i).getJueves().startsWith("12")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=14 && minutes<=59){
                        if(schedulesList.get(i).getJueves().startsWith("13")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=16 && minutes<=29){
                        if(schedulesList.get(i).getJueves().startsWith("15")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=17 && minutes<=59){
                        if(schedulesList.get(i).getJueves().startsWith("16")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=19 && minutes<=59){
                        if(schedulesList.get(i).getJueves().startsWith("18")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=21 && minutes<=59){
                        if(schedulesList.get(i).getJueves().startsWith("20")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else{
                        for(int j= 0; j< schedulesList.size(); j++){
                            if(newSchedulesList.size() > 0){
                                if(!newSchedulesList.get(newSchedulesList.size() - 1).getClassroom().
                                        equals(schedulesList.get(j).getClassroom())){
                                    newSchedulesList.add(schedulesList.get(j));
                                }
                            }else
                                newSchedulesList.add(schedulesList.get(j));
                        }
                        return newSchedulesList;
                    }
                }
                break;
            case 6: //Friday
                for(int i= 0; i< schedulesList.size(); i++){
                    if(hour < 7){
                        for(int j= 0; j< schedulesList.size(); j++){
                            if(newSchedulesList.size() > 0){
                                if(!newSchedulesList.get(newSchedulesList.size() - 1).getClassroom().
                                        equals(schedulesList.get(j).getClassroom())){
                                    newSchedulesList.add(schedulesList.get(j));
                                }
                            }else
                                newSchedulesList.add(schedulesList.get(j));
                        }
                        return newSchedulesList;
                    }else if(hour<=8 && minutes<=29 && hour >= 7){
                        if(schedulesList.get(i).getViernes().startsWith("7")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=9 && minutes<=59){
                        if(schedulesList.get(i).getViernes().startsWith("8")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=11 && minutes<=59){
                        if(schedulesList.get(i).getViernes().startsWith("10")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=13 && minutes<=29){
                        if(schedulesList.get(i).getViernes().startsWith("12")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=14 && minutes<=59){
                        if(schedulesList.get(i).getViernes().startsWith("13")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=16 && minutes<=29){
                        if(schedulesList.get(i).getViernes().startsWith("15")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=17 && minutes<=59){
                        if(schedulesList.get(i).getViernes().startsWith("16")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=19 && minutes<=59){
                        if(schedulesList.get(i).getViernes().startsWith("18")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else if(hour<=21 && minutes<=59){
                        if(schedulesList.get(i).getViernes().startsWith("20")){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }
                    else{
                        for(int j= 0; j< schedulesList.size(); j++){
                            if(newSchedulesList.size() > 0){
                                if(!newSchedulesList.get(newSchedulesList.size() - 1).getClassroom().
                                        equals(schedulesList.get(j).getClassroom())){
                                    newSchedulesList.add(schedulesList.get(j));
                                }
                            }else
                                newSchedulesList.add(schedulesList.get(j));
                        }
                        return newSchedulesList;
                    }
                }
                break;
            default:
                for(int i= 0; i< schedulesList.size(); i++){
                    if(newSchedulesList.size() > 0){
                        if(!newSchedulesList.get(newSchedulesList.size() - 1).getClassroom().
                                equals(schedulesList.get(i).getClassroom())){
                            newSchedulesList.add(schedulesList.get(i));
                        }
                    }else
                        newSchedulesList.add(schedulesList.get(i));
                }
                return newSchedulesList;
        }

        ArrayList<Schedule> aux= new ArrayList<>();
        ArrayList<Schedule> newAux= new ArrayList<>();

        //List used classrooms
        for(int i= 0; i< newSchedulesList.size(); i++){
            if(usedClassrooms.size() > 0){
                for(int j= 0; j< usedClassrooms.size(); j++){
                    if(usedClassrooms.get(usedClassrooms.size()-1).equals(newSchedulesList.get(i).getClassroom()))
                        break;
                    else
                        usedClassrooms.add(newSchedulesList.get(i).getClassroom());
                }
            }else
                usedClassrooms.add(newSchedulesList.get(i).getClassroom());
        }

        int temp;
        //Filter useless classrooms
        for(int i= 0; i< schedulesList.size(); i++){
            temp= 0;
            for(int j= 0; j< usedClassrooms.size(); j++){
                if(j == usedClassrooms.size() - 1)
                    temp= 1;
                if(schedulesList.get(i).getClassroom().equals(usedClassrooms.get(j)))
                    break;
            }
            if(temp == 1){
                aux.add(schedulesList.get(i));
            }
        }

        //Remove similar Classrooms
        for(int i= 0; i< aux.size(); i++){
            if(newAux.size() > 0){
                if(!aux.get(i).getClassroom().equals(newAux.get(newAux.size() - 1).getClassroom()))
                    newAux.add(aux.get(i));
            }else
                newAux.add(aux.get(i));
        }

        return newAux;
    }
}
