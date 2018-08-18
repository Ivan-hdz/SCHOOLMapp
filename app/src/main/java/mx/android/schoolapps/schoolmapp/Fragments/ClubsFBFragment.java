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
import Objetos.AdapterClub;
import Objetos.FBArticulo;
import Objetos.FirebaseReferences;
import mx.android.shcoolapps.schoolmap.R;

public class ClubsFBFragment extends Fragment {
    private AdapterClub adapterClub;
    private List<FBArticulo> clubes;

    public ClubsFBFragment() {
    }

    public static ClubsFBFragment newInstance(String param1, String param2) {
        ClubsFBFragment fragment = new ClubsFBFragment();
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
        View view= inflater.inflate(R.layout.fragment_clubnew, container, false);

        RecyclerView recyclerView= view.findViewById(R.id.recyclerViewClubes);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        clubes=new ArrayList<>();

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        adapterClub=new AdapterClub(getContext(), clubes);
        recyclerView.setAdapter(adapterClub);

        database.getReference(FirebaseReferences.CLUBES_REFERENCE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                clubes.removeAll(clubes);
                for (DataSnapshot snapshot: dataSnapshot.getChildren()
                        ) {
                    FBArticulo articulo=snapshot.getValue(FBArticulo.class);
                    clubes.add(articulo);


                }

                adapterClub.notifyDataSetChanged();
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
