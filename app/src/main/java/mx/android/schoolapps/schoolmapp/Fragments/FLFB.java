package mx.android.schoolapps.schoolmapp.Fragments;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Objetos.FirebaseReferences;
import Objetos.LAFB;
import Objetos.Librox;
import mx.android.shcoolapps.schoolmap.R;

public class FLFB extends Fragment {
    private static LAFB libraryAdapterFB;
    private List<Librox> libros;
    private TextInputEditText editText;

    public FLFB() {
    }

    public static FLFB newInstance(String param1, String param2) {
        FLFB fragment = new FLFB();
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
        editText= view.findViewById(R.id.editTextLibraryFragmentSearch);

        implementSearchFunction();

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        libros=new ArrayList<>();

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        libraryAdapterFB=new LAFB(getContext(), libros);
        recyclerView.setAdapter(libraryAdapterFB);

        database.getReference(FirebaseReferences.LIBROS_REFERENCE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                libros.removeAll(libros);
                for (DataSnapshot snapshot: dataSnapshot.getChildren()
                        ) {
                    Librox articulo=snapshot.getValue(Librox.class);
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

    private void implementSearchFunction(){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence string, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence string, int start, int before, int count) {
                libraryAdapterFB.getFilter().filter(string);
            }

            @Override
            public void afterTextChanged(Editable string) {

            }
        });
    }
}
