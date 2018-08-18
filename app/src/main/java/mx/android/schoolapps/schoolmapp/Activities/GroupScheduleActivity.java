package mx.android.schoolapps.schoolmapp.Activities;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Binder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import mx.android.schoolapps.schoolmapp.Adapters.GroupRecyclerAdapter;
import mx.android.schoolapps.schoolmapp.Database.ScheduleContract;
import mx.android.schoolapps.schoolmapp.Database.ScheduleDBHelper;
import mx.android.schoolapps.schoolmapp.Models.Schedule;
import mx.android.shcoolapps.schoolmap.R;

public class GroupScheduleActivity extends AppCompatActivity {

    private ScheduleDBHelper scheduleDBHelper= new ScheduleDBHelper(this);
    private ArrayList<Schedule> filteredScheduleList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        Toolbar toolbar= findViewById(R.id.toolbarGroupScheduleActivity);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Bundle extras= getIntent().getExtras();
        Schedule schedule= extras.getParcelable("SelectedSchedule");

        toolbar.setTitle(getString(R.string.classroom) + ": " + schedule.getClassroom() +" - " + schedule.getGrupo());
        filteredScheduleList= getGroupFromDB(schedule.getGrupo());

        RecyclerView recyclerView= findViewById(R.id.recyclerViewGroupFirstBuilding);
        GroupRecyclerAdapter adapter= new GroupRecyclerAdapter(this, filteredScheduleList);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    public ArrayList<Schedule> getGroupFromDB(String searchGroup){
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

        return scheduleList;
    }
}
