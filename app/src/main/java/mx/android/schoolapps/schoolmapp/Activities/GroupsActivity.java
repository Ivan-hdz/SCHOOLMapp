package mx.android.schoolapps.schoolmapp.Activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import mx.android.schoolapps.schoolmapp.Adapters.ClassroomAdapter;
import mx.android.schoolapps.schoolmapp.Database.ScheduleContract;
import mx.android.schoolapps.schoolmapp.Database.ScheduleDBHelper;
import mx.android.schoolapps.schoolmapp.Models.Schedule;
import mx.android.shcoolapps.schoolmap.R;

public class GroupsActivity extends AppCompatActivity {

    private ClassroomAdapter adapter;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        Toolbar toolbar= findViewById(R.id.toolbarGroupsActivity);
        toolbar.setTitle("Grupos");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        gridView= findViewById(R.id.gridViewGroupsActivity);
        getSchedulesFromDB();
    }

    public void getSchedulesFromDB(){
        ScheduleDBHelper scheduleDBHelper= new ScheduleDBHelper(this);
        SQLiteDatabase sqLiteDatabase= scheduleDBHelper.getReadableDatabase();

        String sortOrder= ScheduleContract.ScheduleColumns.GRUPO + " ASC";

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

        adapter= new ClassroomAdapter(this, R.layout.classroom_adapter_gridview, schedulesList, "group");
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Schedule schedule= adapter.getItem(position);

                Intent intent= new Intent(GroupsActivity.this, GroupScheduleActivity.class);
                intent.putExtra("SelectedSchedule", schedule);
                startActivity(intent);
            }
        });
    }

    public ArrayList<Schedule> filter(final ArrayList<Schedule> schedulesList){
        ArrayList<Schedule> newSchedulesList= new ArrayList<>();

        for(int i= 0; i< schedulesList.size(); i++){
            if(newSchedulesList.size() > 0){
                if(!schedulesList.get(i).getGrupo().equals(newSchedulesList.get(newSchedulesList.size() - 1).getGrupo()))
                    newSchedulesList.add(schedulesList.get(i));
            }else
                newSchedulesList.add(schedulesList.get(i));
        }
        return newSchedulesList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_classroom_schedule, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.searchButton).getActionView();
        searchView.setIconified(true);

        SearchManager searchManager = (SearchManager)  getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                adapter.getFilter().filter(query);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.searchButton).setVisible(true);

        return true;
    }
}
