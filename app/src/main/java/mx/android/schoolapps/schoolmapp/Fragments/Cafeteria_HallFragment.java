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

import Objetos.AdapterB;
import Objetos.FBArticulo;
import mx.android.schoolapps.schoolmapp.Database.CafeteriaHallContract;
import mx.android.schoolapps.schoolmapp.Database.ScheduleDBHelper;
import mx.android.shcoolapps.schoolmap.R;

public class Cafeteria_HallFragment extends Fragment {
    private static ArrayList<FBArticulo> comidas;
    private static AdapterB adapterB;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshFood;
    private TextInputEditText editText;

    public Cafeteria_HallFragment() {
    }

    public static Cafeteria_HallFragment newInstance(String param1, String param2) {
        Cafeteria_HallFragment fragment = new Cafeteria_HallFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_cafeteria__hall, container, false);

        swipeRefreshFood= view.findViewById(R.id.swipeRefreshCafeteriaHall);
        recyclerView= view.findViewById(R.id.recyclerViewCafeteriaHall);
        editText= view.findViewById(R.id.editTextCafeteriaFragmentHallSearch);

        implementSearchFunction();

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        comidas=new ArrayList<>();

        adapterB=new AdapterB(getContext(), comidas);
        recyclerView.setAdapter(adapterB);

        swipeRefreshFood.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getComidasHallFromDB();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshFood.setRefreshing(false);
                    }
                },2500);
            }
        });

        getComidasHallFromDB();

        return view;
    }

    private void implementSearchFunction(){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence string, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence string, int start, int before, int count) {
                adapterB.getFilter().filter(string);
            }

            @Override
            public void afterTextChanged(Editable string) {

            }
        });
    }

    public void getComidasHallFromDB(){
        ScheduleDBHelper scheduleDBHelper= new ScheduleDBHelper(getContext());
        SQLiteDatabase sqLiteDatabase= scheduleDBHelper.getReadableDatabase();

        String sortOrder= CafeteriaHallContract.CafeteriaHallColumns.NOMBRE_COMIDA+ " ASC";

        Cursor cursor= sqLiteDatabase.query(CafeteriaHallContract.CafeteriaHallColumns.TABLE_NAME,null,
                null,null,null,null,sortOrder,null);

        ArrayList<FBArticulo> comidasList= new ArrayList<>();

        while(cursor.moveToNext()){
            String nombre= cursor.getString(CafeteriaHallContract.CafeteriaHallColumns.NOMBRE_COMIDA_COLUMN_INDEX);
            String precio= cursor.getString(CafeteriaHallContract.CafeteriaHallColumns.PRECIO_COLUMN_INDEX);

            comidasList.add(new FBArticulo(nombre, precio, null,null,null,null,null,null,null,null,null,null));
        }
        cursor.close();

        fillSchedulesList(comidasList);
    }

    private void fillSchedulesList(final ArrayList<FBArticulo> comidasList){
        adapterB= new AdapterB(getContext(), comidasList);
        recyclerView.setAdapter(adapterB);
    }

}
