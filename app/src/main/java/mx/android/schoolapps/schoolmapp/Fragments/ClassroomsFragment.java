package mx.android.schoolapps.schoolmapp.Fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import mx.android.schoolapps.schoolmapp.Activities.BuildingsActivity;
import mx.android.schoolapps.schoolmapp.Activities.ClassroomsActivity;
import mx.android.schoolapps.schoolmapp.Activities.EmptyClassroomsActivity;
import mx.android.schoolapps.schoolmapp.Activities.GroupScheduleActivity;
import mx.android.schoolapps.schoolmapp.Activities.GroupsActivity;
import mx.android.schoolapps.schoolmapp.Database.DownloadScheduleInfo;
import mx.android.schoolapps.schoolmapp.Models.Schedule;
import mx.android.shcoolapps.schoolmap.R;

public class ClassroomsFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private ProgressDialog mProgressDialog;
    private DownloadScheduleInfo downloadSchedule;

    public ClassroomsFragment() {
    }

    public static ClassroomsFragment newInstance(String param1, String param2) {
        ClassroomsFragment fragment = new ClassroomsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_classrooms, container, false);

        ImageView iconBuildings= view.findViewById(R.id.imageViewClassroomsBuildingsIcon);
        ImageView iconClassrooms= view.findViewById(R.id.imageViewClassroomsClassroomsIcon);
        ImageView iconGroups= view.findViewById(R.id.imageViewClassroomsGroupsIcon);
        ImageView iconEmptyClassrooms= view.findViewById(R.id.imageViewClassroomsEmptyClassroomsIcon);

        iconBuildings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getContext(), BuildingsActivity.class);
                startActivity(intent);
            }
        });

        iconClassrooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getContext(), ClassroomsActivity.class);
                startActivity(intent);
            }
        });

        iconGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getContext(), GroupsActivity.class);
                startActivity(intent);
            }
        });

        iconEmptyClassrooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getContext(), EmptyClassroomsActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.updateButton:

                downloadSchedule= new DownloadScheduleInfo(getContext());
                downloadSchedule.delegate = new DownloadScheduleInfo.DownloadScheduleInterface() {
                    @Override
                    public void onScheduleDownloaded(ArrayList<Schedule> schedulesList) {
                    }
                };
                downloadSchedule.execute();

                mProgressDialog = new ProgressDialog(getContext());
                mProgressDialog.setMessage("Conectando con el servidor...");
                mProgressDialog.setIndeterminate(false);
                mProgressDialog.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mProgressDialog.dismiss();

                        if(!downloadSchedule.gotDownloaded()){
                            AlertDialog.Builder failedConnection= new AlertDialog.Builder(getContext());
                            failedConnection.setMessage("Error al conectar con el servidor, intenta de nuevo más tarde.");

                            failedConnection.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });

                            failedConnection.show();
                        }else{
                            Toast.makeText(getContext(), "Base de Datos Actualizada", Toast.LENGTH_SHORT).show();
                            System.out.println("Got downloaded (?) ");
                        }
                    }
                }, 15000);

                break;
        }
        return true;
    }
}
