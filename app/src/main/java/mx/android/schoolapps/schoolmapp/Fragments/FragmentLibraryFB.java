package mx.android.schoolapps.schoolmapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Objetos.FBArticulo;
import Objetos.FirebaseReferences;
import Objetos.LibraryAdapterFB;
import mx.android.shcoolapps.schoolmap.R;

public class FragmentLibraryFB extends Fragment {
    private static LibraryAdapterFB libraryAdapterFB;
    private List<FBArticulo> libros;

    public FragmentLibraryFB() {
    }

    public static FragmentLibraryFB newInstance(String param1, String param2) {
        FragmentLibraryFB fragment = new FragmentLibraryFB();
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
        View view= inflater.inflate(R.layout.fragment_libraryfb, container, false);

        RecyclerView recyclerView= view.findViewById(R.id.recyclerViewLibraryfb);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        libros=new ArrayList<>();

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        libraryAdapterFB=new LibraryAdapterFB(getContext(), libros);
        recyclerView.setAdapter(libraryAdapterFB);

        database.getReference(FirebaseReferences.LIBROS_REFERENCE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                libros.removeAll(libros);
                for (DataSnapshot snapshot: dataSnapshot.getChildren()
                        ) {
                    FBArticulo articulo=snapshot.getValue(FBArticulo.class);
                    libros.add(articulo);


                }

                libraryAdapterFB.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

    public static void loadData(String query){
        libraryAdapterFB.getFilter().filter(query);
    }

}
