package mx.android.schoolapps.schoolmapp.Fragments;


import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Objetos.FBArticulo;
import Objetos.FirebaseReferences;
import mx.android.schoolapps.schoolmapp.Adapters.StationaryPageAdapter;
import mx.android.schoolapps.schoolmapp.Database.ScheduleDBHelper;
import mx.android.schoolapps.schoolmapp.Database.StationaryFirstFloorContract;
import mx.android.schoolapps.schoolmapp.Database.StationaryGroundFloorContract;
import mx.android.shcoolapps.schoolmap.R;
import mx.android.schoolapps.schoolmapp.Utils;

public class StationaryFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private ArrayList<FBArticulo> copias= new ArrayList<>();
    private ArrayList<FBArticulo> articulos= new ArrayList<>();
    private ScheduleDBHelper scheduleDBHelper;
    private SQLiteDatabase sqLiteDatabase;

    public StationaryFragment() {
    }

    public static StationaryFragment newInstance(String param1, String param2) {
        StationaryFragment fragment = new StationaryFragment();
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

        scheduleDBHelper= new ScheduleDBHelper(getContext());
        sqLiteDatabase= scheduleDBHelper.getWritableDatabase();

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_stationary, container, false);

        TabLayout tabLayout= view.findViewById(R.id.tabLayoutStationary);
        tabLayout.addTab(tabLayout.newTab().setText("Planta Baja"));
        tabLayout.addTab(tabLayout.newTab().setText("Primer Piso"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager= view.findViewById(R.id.viewPagerStationary);
        StationaryPageAdapter stationaryPageAdapter= new StationaryPageAdapter(getFragmentManager(),tabLayout.getTabCount());

        viewPager.setAdapter(stationaryPageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position= tab.getPosition();
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        long count= DatabaseUtils.queryNumEntries(sqLiteDatabase,
                StationaryFirstFloorContract.StationaryFirstFloorColumns.TABLE_NAME);

        if(count <= 0){
            downloadInfo();
        }

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.updateButton:

                if(!Utils.isNetworkAvailable(getContext())){
                    AlertDialog.Builder failedConnection= new AlertDialog.Builder(getContext());
                    failedConnection.setMessage("Error al conectar con el servidor, verifica tu conexión a internet o " +
                            "intenta de nuevo más tarde.");

                    failedConnection.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    failedConnection.show();
                }else{
                    downloadInfo();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), "Papelería Actualizada, recargar página para efectuar cambios",
                                    Toast.LENGTH_LONG).show();
                        }
                    },1000);
                }

                break;
        }
        return true;
    }

    public void downloadInfo(){
        FirebaseDatabase database=FirebaseDatabase.getInstance();

        ScheduleDBHelper scheduleDBHelper= new ScheduleDBHelper(getContext());
        SQLiteDatabase sqLiteDatabase= scheduleDBHelper.getWritableDatabase();

        sqLiteDatabase.delete(StationaryFirstFloorContract.StationaryFirstFloorColumns.TABLE_NAME, null, null);
        sqLiteDatabase.delete(StationaryGroundFloorContract.StationaryGroundFloorColumns.TABLE_NAME, null, null);

        database.getReference(FirebaseReferences.COPIAS_REFERENCE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                copias.removeAll(copias);
                for (DataSnapshot snapshot: dataSnapshot.getChildren()
                        ) {
                    FBArticulo comida=snapshot.getValue(FBArticulo.class);
                    copias.add(comida);
                }
                saveStationaryFirstFloorInfoonDB(copias);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        database.getReference(FirebaseReferences.PAPELERIA_REFERENCE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                articulos.removeAll(articulos);
                for (DataSnapshot snapshot: dataSnapshot.getChildren()
                        ) {
                    FBArticulo comida=snapshot.getValue(FBArticulo.class);
                    articulos.add(comida);
                }

                saveStationaryGroundFloorInfoonDB(articulos);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void saveStationaryFirstFloorInfoonDB(ArrayList<FBArticulo> articulosList) {
        //long count= DatabaseUtils.queryNumEntries(sqLiteDatabase, StationaryFirstFloorContract.StationaryFirstFloorColumns.TABLE_NAME);

        for(FBArticulo articulo : articulosList){
            ContentValues contentValues= new ContentValues();
            contentValues.put(StationaryFirstFloorContract.StationaryFirstFloorColumns.NOMBRE_ARTICULO, articulo.getNombre());
            contentValues.put(StationaryFirstFloorContract.StationaryFirstFloorColumns.DESCRIPCION, articulo.getDescripcion());

            sqLiteDatabase.insert(StationaryFirstFloorContract.StationaryFirstFloorColumns.TABLE_NAME,null,contentValues);
        }
    }

    private void saveStationaryGroundFloorInfoonDB(ArrayList<FBArticulo> articulosList) {
        for(FBArticulo articulo : articulosList){
            ContentValues contentValues= new ContentValues();
            contentValues.put(StationaryGroundFloorContract.StationaryGroundFloorColumns.NOMBRE_ARTICULO, articulo.getNombre());
            contentValues.put(StationaryGroundFloorContract.StationaryGroundFloorColumns.DESCRIPCION, articulo.getDescripcion());

            sqLiteDatabase.insert(StationaryGroundFloorContract.StationaryGroundFloorColumns.TABLE_NAME,null,contentValues);
        }
    }
}
