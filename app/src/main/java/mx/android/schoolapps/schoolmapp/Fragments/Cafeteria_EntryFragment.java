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

import Objetos.Adapter;
import Objetos.Comida;
import Objetos.FBArticulo;
import mx.android.schoolapps.schoolmapp.Database.CafeteriaEntryContract;
import mx.android.schoolapps.schoolmapp.Database.ScheduleDBHelper;
import mx.android.shcoolapps.schoolmap.R;

public class Cafeteria_EntryFragment extends Fragment {

    private static ArrayList<FBArticulo> comidas;
    private static Adapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshFood;
    private TextInputEditText editText;

    public Cafeteria_EntryFragment() {
    }

    public static Cafeteria_EntryFragment newInstance(String param1, String param2) {
        Cafeteria_EntryFragment fragment = new Cafeteria_EntryFragment();
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

        View view= inflater.inflate(R.layout.fragment_cafeteria__entry, container, false);

        swipeRefreshFood= view.findViewById(R.id.swipeRefreshCafeteriaEntry);
        recyclerView= view.findViewById(R.id.recyclerViewCafeteriaEntry);
        editText= view.findViewById(R.id.editTextCafeteriaFragmentEntrySearch);

        implementSearchFunction();

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        comidas=new ArrayList<>();

        adapter=new Adapter(getContext(), comidas);
        recyclerView.setAdapter(adapter);

        swipeRefreshFood.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getComidasEntryFromDB();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshFood.setRefreshing(false);
                    }
                },2500);
            }
        });

        getComidasEntryFromDB();

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

    public void getComidasEntryFromDB(){
        ScheduleDBHelper scheduleDBHelper= new ScheduleDBHelper(getContext());
        SQLiteDatabase sqLiteDatabase= scheduleDBHelper.getReadableDatabase();

        String sortOrder= CafeteriaEntryContract.CafeteriaEntryColumns.NOMBRE_COMIDA+ " ASC";

        Cursor cursor= sqLiteDatabase.query(CafeteriaEntryContract.CafeteriaEntryColumns.TABLE_NAME,null,
                null,null,null,null,sortOrder,null);

        ArrayList<FBArticulo> comidasList= new ArrayList<>();

        while(cursor.moveToNext()){
            String nombre= cursor.getString(CafeteriaEntryContract.CafeteriaEntryColumns.NOMBRE_COMIDA_COLUMN_INDEX);
            String precio= cursor.getString(CafeteriaEntryContract.CafeteriaEntryColumns.PRECIO_COLUMN_INDEX);

            comidasList.add(new FBArticulo(nombre, precio, null,null,null,null,null,null,null,null,null,null));
        }
        cursor.close();

        fillSchedulesList(comidasList);
    }

    private void fillSchedulesList(final ArrayList<FBArticulo> comidasList){
        adapter= new Adapter(getContext(), comidasList);
        recyclerView.setAdapter(adapter);

    }

}
