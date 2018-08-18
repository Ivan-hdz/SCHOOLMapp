package mx.android.schoolapps.schoolmapp.Fragments;

import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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

import Objetos.AdapterP;
import Objetos.FBArticulo;
import mx.android.schoolapps.schoolmapp.Database.ScheduleDBHelper;
import mx.android.schoolapps.schoolmapp.Database.StationaryGroundFloorContract;
import mx.android.shcoolapps.schoolmap.R;

public class Stationary_GroundFloorFragment extends Fragment {
    private static AdapterP adapterP;
    private ArrayList<FBArticulo> articulos;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshArticles;
    private TextInputEditText editText;

    public Stationary_GroundFloorFragment() {
    }

    public static Stationary_GroundFloorFragment newInstance(String param1, String param2) {
        Stationary_GroundFloorFragment fragment = new Stationary_GroundFloorFragment();
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
        View view= inflater.inflate(R.layout.fragment_stationary__ground_floor, container, false);

        swipeRefreshArticles= view.findViewById(R.id.swipeRefreshStationaryGroundFloor);
        recyclerView= view.findViewById(R.id.recyclerViewStationaryGroundFloor);
        editText= view.findViewById(R.id.editTextStationaryFragmentGroundSearch);

        implementSearchFunction();

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        articulos=new ArrayList<>();

        //adapterP=new AdapterP(getContext(), articulos);
        //recyclerView.setAdapter(adapterP);

        swipeRefreshArticles.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getStationaryGroundFloorFromDB();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshArticles.setRefreshing(false);
                    }
                },2500);
            }
        });

        getStationaryGroundFloorFromDB();
        return view;
    }

    private void implementSearchFunction(){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence string, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence string, int start, int before, int count) {
                adapterP.getFilter().filter(string);
            }

            @Override
            public void afterTextChanged(Editable string) {

            }
        });
    }

    public void getStationaryGroundFloorFromDB(){
        ScheduleDBHelper scheduleDBHelper= new ScheduleDBHelper(getContext());
        SQLiteDatabase sqLiteDatabase= scheduleDBHelper.getReadableDatabase();

        String sortOrder= StationaryGroundFloorContract.StationaryGroundFloorColumns.NOMBRE_ARTICULO+ " ASC";

        Cursor cursor= sqLiteDatabase.query(StationaryGroundFloorContract.StationaryGroundFloorColumns.TABLE_NAME,null,
                null,null,null,null,sortOrder,null);

        ArrayList<FBArticulo> comidasList= new ArrayList<>();

        while(cursor.moveToNext()){
            String nombre= cursor.getString(StationaryGroundFloorContract.StationaryGroundFloorColumns.NOMBRE_ARTICULO_COLUMN_INDEX);
            String precio= cursor.getString(StationaryGroundFloorContract.StationaryGroundFloorColumns.DESCRIPCION_COLUMN_INDEX);

            comidasList.add(new FBArticulo(nombre, precio, null,null,null,null,null,null,null,null,null,null));
        }
        cursor.close();

        fillSchedulesList(comidasList);
    }

    private void fillSchedulesList(final ArrayList<FBArticulo> comidasList){
        /*adapterP= new AdapterP(getContext(), comidasList);
        recyclerView.setAdapter(adapterP);*/

        adapterP=new AdapterP(getContext(), comidasList);
        recyclerView.setAdapter(adapterP);
    }

}
