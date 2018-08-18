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

import Objetos.AdapterC;
import Objetos.FBArticulo;
import Objetos.FirebaseReferences;
import mx.android.shcoolapps.schoolmap.R;

public class CounselorsInfoFragment extends Fragment {
    private AdapterC adapterC;
    private List<FBArticulo> consejeros;

    public CounselorsInfoFragment() {
    }

    public static CounselorsInfoFragment newInstance(String param1, String param2) {
        CounselorsInfoFragment fragment = new CounselorsInfoFragment();
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
        View view= inflater.inflate(R.layout.fragment_counselors_info, container, false);

        RecyclerView recyclerView= view.findViewById(R.id.recyclerViewCounselors);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        consejeros=new ArrayList<>();

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        adapterC=new AdapterC(getContext(), consejeros);
        recyclerView.setAdapter(adapterC);

        database.getReference(FirebaseReferences.CONSEJEROS_REFERENCE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                consejeros.removeAll(consejeros);
                for (DataSnapshot snapshot: dataSnapshot.getChildren()
                        ) {
                    FBArticulo articulo=snapshot.getValue(FBArticulo.class);
                    consejeros.add(articulo);


                }

                adapterC.notifyDataSetChanged();
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
