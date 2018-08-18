package mx.android.schoolapps.schoolmapp.Fragments;


import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class EmptyClassroomsFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private ClassroomAdapter adapter;
    private GridView scheduleListView;

    public EmptyClassroomsFragment() {
    }

    public static EmptyClassroomsFragment newInstance(String param1, String param2) {
        EmptyClassroomsFragment fragment = new EmptyClassroomsFragment();
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
        View view= inflater.inflate(R.layout.fragment_empty_classrooms, container, false);

        scheduleListView= view.findViewById(R.id.gridViewEmptyClassrooms);
        getSchedulesFromDB();

        registerForContextMenu(scheduleListView);
        return view;
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
        ScheduleDBHelper scheduleDBHelper= new ScheduleDBHelper(getContext());
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
        ScheduleDBHelper scheduleDBHelper= new ScheduleDBHelper(getContext());
        SQLiteDatabase sqLiteDatabase= scheduleDBHelper.getReadableDatabase();

        long count= DatabaseUtils.queryNumEntries(sqLiteDatabase, ScheduleContract.ScheduleColumns.TABLE_NAME);
        System.out.println("There are " + count + " elements in the DB");

        // Filter results WHERE "title" = 'My Title'
        String selection= ScheduleContract.ScheduleColumns.CLASSROOM + " = ?";
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

        adapter= new ClassroomAdapter(getContext(), R.layout.classroom_adapter_gridview, schedulesList, "classroom");
        scheduleListView.setAdapter(adapter);
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
