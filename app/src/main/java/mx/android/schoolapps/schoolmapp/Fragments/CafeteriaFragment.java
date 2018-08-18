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
import mx.android.schoolapps.schoolmapp.Adapters.CafeteriaPageAdapter;
import mx.android.schoolapps.schoolmapp.Database.CafeteriaEntryContract;
import mx.android.schoolapps.schoolmapp.Database.CafeteriaHallContract;
import mx.android.schoolapps.schoolmapp.Database.ScheduleDBHelper;
import mx.android.shcoolapps.schoolmap.R;
import mx.android.schoolapps.schoolmapp.Utils;

public class CafeteriaFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private ArrayList<FBArticulo> comidasEntrada= new ArrayList<>();
    private ArrayList<FBArticulo> comidasPasillo= new ArrayList<>();
    private ScheduleDBHelper scheduleDBHelper;
    private SQLiteDatabase sqLiteDatabase;

    public CafeteriaFragment() {
    }

    public static CafeteriaFragment newInstance(String param1, String param2) {
        CafeteriaFragment fragment = new CafeteriaFragment();
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
        View view=  inflater.inflate(R.layout.fragment_cafeteria, container, false);

        TabLayout tabLayout= view.findViewById(R.id.tabLayoutCafeteria);
        tabLayout.addTab(tabLayout.newTab().setText("Cafeteria Pasillo"));
        tabLayout.addTab(tabLayout.newTab().setText("Cafetería Entrada"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager= view.findViewById(R.id.viewPagerCafeteria);
        CafeteriaPageAdapter cafeteriaPageAdapter= new CafeteriaPageAdapter(getFragmentManager(),tabLayout.getTabCount());

        viewPager.setAdapter(cafeteriaPageAdapter);
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

        long count= DatabaseUtils.queryNumEntries(sqLiteDatabase, CafeteriaEntryContract.CafeteriaEntryColumns.TABLE_NAME);
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
                            Toast.makeText(getContext(), "Cafeteria Actualizada, recargar página para efectuar cambios",
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

        sqLiteDatabase.delete(CafeteriaEntryContract.CafeteriaEntryColumns.TABLE_NAME, null, null);
        sqLiteDatabase.delete(CafeteriaHallContract.CafeteriaHallColumns.TABLE_NAME, null, null);

        database.getReference(FirebaseReferences.ENTRADA_REFERENCE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                comidasEntrada.removeAll(comidasEntrada);
                for (DataSnapshot snapshot: dataSnapshot.getChildren()
                        ) {
                    FBArticulo comida=snapshot.getValue(FBArticulo.class);
                    comidasEntrada.add(comida);
                }

                saveCafeteriaEntryInfoonDB(comidasEntrada);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        database.getReference(FirebaseReferences.PASILLO_REFERENCE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                comidasPasillo.removeAll(comidasPasillo);
                for (DataSnapshot snapshot: dataSnapshot.getChildren()
                        ) {
                    FBArticulo comida=snapshot.getValue(FBArticulo.class);
                    comidasPasillo.add(comida);
                }

                saveCafeteriaHallInfoonDB(comidasPasillo);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void saveCafeteriaEntryInfoonDB(ArrayList<FBArticulo> comidaList) {
        for(FBArticulo comida : comidaList){
            ContentValues contentValues= new ContentValues();
            contentValues.put(CafeteriaEntryContract.CafeteriaEntryColumns.NOMBRE_COMIDA, comida.getNombre());
            contentValues.put(CafeteriaEntryContract.CafeteriaEntryColumns.PRECIO, comida.getDescripcion());

            sqLiteDatabase.insert(CafeteriaEntryContract.CafeteriaEntryColumns.TABLE_NAME,null,contentValues);
        }
    }

    private void saveCafeteriaHallInfoonDB(ArrayList<FBArticulo> comidaList) {
        for(FBArticulo comida : comidaList){
            ContentValues contentValues= new ContentValues();
            contentValues.put(CafeteriaHallContract.CafeteriaHallColumns.NOMBRE_COMIDA, comida.getNombre());
            contentValues.put(CafeteriaHallContract.CafeteriaHallColumns.PRECIO, comida.getDescripcion());

            sqLiteDatabase.insert(CafeteriaHallContract.CafeteriaHallColumns.TABLE_NAME,null,contentValues);
        }
    }
}
