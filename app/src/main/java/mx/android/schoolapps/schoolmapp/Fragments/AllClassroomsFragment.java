package mx.android.schoolapps.schoolmapp.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mx.android.schoolapps.schoolmapp.Activities.ClassroomFirstBuilding;
import mx.android.schoolapps.schoolmapp.Activities.ClassroomMidBuilding;
import mx.android.schoolapps.schoolmapp.Activities.ClassroomSecondBuilding;
import mx.android.shcoolapps.schoolmap.R;

public class AllClassroomsFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    public AllClassroomsFragment() {
        // Required empty public constructor
    }

    public static AllClassroomsFragment newInstance(String param1, String param2) {
        AllClassroomsFragment fragment = new AllClassroomsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_classrooms, container, false);

        TextView textViewFirstBuilding = view.findViewById(R.id.textViewFirstFloor);
        TextView textViewMidBuilding = view.findViewById(R.id.textViewSecondFloor);
        TextView textViewSecondBuilding = view.findViewById(R.id.textViewThirdFloor);

        textViewFirstBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ClassroomFirstBuilding.class);
                startActivity(intent);
            }
        });

        textViewMidBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ClassroomMidBuilding.class);
                startActivity(intent);
            }
        });

        textViewSecondBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ClassroomSecondBuilding.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
