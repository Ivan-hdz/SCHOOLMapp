package mx.android.schoolapps.schoolmapp.Fragments;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import Objetos.AdapterQ;
import Objetos.FBArticulo;
import mx.android.schoolapps.schoolmapp.Database.ScheduleDBHelper;
import mx.android.schoolapps.schoolmapp.Database.StationaryFirstFloorContract;
import mx.android.shcoolapps.schoolmap.R;

public class Stationary_FirstFloorFragment extends Fragment {
    private static AdapterQ adapterQ;
    private ArrayList<FBArticulo> copias;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshArticles;

    public Stationary_FirstFloorFragment() {
    }

    public static Stationary_FirstFloorFragment newInstance(String param1, String param2) {
        Stationary_FirstFloorFragment fragment = new Stationary_FirstFloorFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_stationary__first_floor, container, false);

        swipeRefreshArticles= view.findViewById(R.id.swipeRefreshStationaryFirstFloor);
        recyclerView= view.findViewById(R.id.recyclerViewStationaryFirstFloor);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        copias=new ArrayList<>();

        //adapterQ=new AdapterQ(getContext(), copias);
        //recyclerView.setAdapter(adapterQ);

        swipeRefreshArticles.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getStationaryFirstFloorFromDB();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshArticles.setRefreshing(false);
                    }
                },2500);
            }
        });

        getStationaryFirstFloorFromDB();

        return view;
    }

    public void getStationaryFirstFloorFromDB(){
        ScheduleDBHelper scheduleDBHelper= new ScheduleDBHelper(getContext());
        SQLiteDatabase sqLiteDatabase= scheduleDBHelper.getReadableDatabase();

        String sortOrder= StationaryFirstFloorContract.StationaryFirstFloorColumns.NOMBRE_ARTICULO+ " ASC";

        Cursor cursor= sqLiteDatabase.query(StationaryFirstFloorContract.StationaryFirstFloorColumns.TABLE_NAME,null,
                null,null,null,null,sortOrder,null);

        ArrayList<FBArticulo> comidasList= new ArrayList<>();

        while(cursor.moveToNext()){
            String nombre= cursor.getString(StationaryFirstFloorContract.StationaryFirstFloorColumns.NOMBRE_ARTICULO_COLUMN_INDEX);
            String precio= cursor.getString(StationaryFirstFloorContract.StationaryFirstFloorColumns.DESCRIPCION_COLUMN_INDEX);

            comidasList.add(new FBArticulo(nombre, precio, null,null,null,null,null,null,null,null,null,null));
        }
        cursor.close();

        fillSchedulesList(comidasList);
    }

    private void fillSchedulesList(final ArrayList<FBArticulo> comidasList){
        /*adapterQ= new AdapterQ(getContext(), comidasList);
        recyclerView.setAdapter(adapterQ);*/
        adapterQ=new AdapterQ(getContext(), comidasList);
        recyclerView.setAdapter(adapterQ);

    }

}
