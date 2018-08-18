package mx.android.schoolapps.schoolmapp.Fragments;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mx.android.schoolapps.schoolmapp.Activities.TeachersScheduleActivity;
import mx.android.schoolapps.schoolmapp.Adapters.TeachersRecyclerAdapter;
import mx.android.schoolapps.schoolmapp.Database.ScheduleContract;
import mx.android.schoolapps.schoolmapp.Database.ScheduleDBHelper;
import mx.android.schoolapps.schoolmapp.Models.Schedule;
import mx.android.shcoolapps.schoolmap.R;

public class TeachersFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private ArrayList<Schedule> schedules= new ArrayList<>();
    private TeachersRecyclerAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private TextInputEditText editText;

    public TeachersFragment() {
    }

    public static TeachersFragment newInstance(String param1, String param2) {
        TeachersFragment fragment = new TeachersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_teachers, container, false);

        editText= view.findViewById(R.id.editTextTeachersFragmentSearch);
        implementSearchFunction();

        recyclerView= view.findViewById(R.id.recyclerViewTeachersFragment);
        adapter= new TeachersRecyclerAdapter(getContext(), schedules);
        linearLayoutManager= new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        getTeachersFromDB();

        return view;
    }

    private void implementSearchFunction(){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence string, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence string, int start, int before, int count) {
                adapter.getFilter().filter(string);
            }

            @Override
            public void afterTextChanged(Editable string) {

            }
        });
    }

    public void getTeachersFromDB(){
        ScheduleDBHelper scheduleDBHelper= new ScheduleDBHelper(getContext());
        SQLiteDatabase sqLiteDatabase= scheduleDBHelper.getReadableDatabase();

        String sortOrder= ScheduleContract.ScheduleColumns.NOMBRE_PROFESOR + " ASC";

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

        adapter= new TeachersRecyclerAdapter(getContext(), schedulesList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter.setOnTeacherClickListener(new TeachersRecyclerAdapter.onTeacherClickListener() {
            @Override
            public void OnItemClickListener(Schedule schedule) {

                Intent intent= new Intent(getContext(), TeachersScheduleActivity.class);
                intent.putExtra("SelectedSchedule", schedule);
                startActivity(intent);
            }
        });
    }

    public ArrayList<Schedule> filter(final ArrayList<Schedule> schedulesList){
        ArrayList<Schedule> newSchedulesList= new ArrayList<>();

        for(int i= 0; i< schedulesList.size(); i++){
            if(newSchedulesList.size() > 0){
                if(!schedulesList.get(i).getNombreProf().equals(newSchedulesList.get(newSchedulesList.size() - 1).getNombreProf()))
                    newSchedulesList.add(schedulesList.get(i));
            }else
                newSchedulesList.add(schedulesList.get(i));
        }
        return newSchedulesList;
    }
}
