package mx.android.schoolapps.schoolmapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Objetos.AdapterO;
import Objetos.FBArticulo;
import Objetos.FirebaseReferences;
import mx.android.shcoolapps.schoolmap.R;

public class About_InfoFragment extends Fragment {
    private AdapterO adapterO;
    private List<FBArticulo> personales;

    public About_InfoFragment() {
    }

    public static About_InfoFragment newInstance(String param1, String param2) {
        About_InfoFragment fragment = new About_InfoFragment();
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
        View view= inflater.inflate(R.layout.fragment_about__info, container, false);

        RecyclerView recyclerView= view.findViewById(R.id.recyclerViewInfoESCOM);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        personales=new ArrayList<>();

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        adapterO=new AdapterO(getContext(), personales);
        recyclerView.setAdapter(adapterO);

        database.getReference(FirebaseReferences.PERSONAL_REFERENCE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                personales.removeAll(personales);
                for (DataSnapshot snapshot: dataSnapshot.getChildren()
                        ) {
                    FBArticulo articulo=snapshot.getValue(FBArticulo.class);
                    personales.add(articulo);


                }

                adapterO.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.searchButton).setVisible(false);
    }
}
