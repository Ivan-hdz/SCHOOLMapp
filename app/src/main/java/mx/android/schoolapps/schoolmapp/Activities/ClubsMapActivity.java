package mx.android.schoolapps.schoolmapp.Activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Toast;

import com.jsibbold.zoomage.ZoomageView;

import mx.android.shcoolapps.schoolmap.R;

public class ClubsMapActivity extends AppCompatActivity {

    private int floor= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubs_map);

        final Toolbar toolbar= findViewById(R.id.clubsMapToolbar);
        toolbar.setTitle("Mapa - Planta Baja");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        final ZoomageView clubsMap= findViewById(R.id.imageViewClubsMap);

        final FloatingActionButton UpArrow= findViewById(R.id.fabClubsUpArrow);
        final FloatingActionButton DownArrow= findViewById(R.id.fabClubsDownArrow);

        UpArrow.setAlpha(.8f);
        DownArrow.setAlpha(.8f);

        DownArrow.setVisibility(View.INVISIBLE);

        UpArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(floor==0){
                    clubsMap.setImageResource(R.drawable.mapa_clubes_piso1);
                    DownArrow.setVisibility(View.VISIBLE);
                    floor+= 1;
                    toolbar.setTitle("Mapa - Primer Piso");
                }else if (floor==1){
                    clubsMap.setImageResource(R.drawable.mapa_clubes_piso2);
                    floor+= 1;
                    UpArrow.setVisibility(View.INVISIBLE);
                    toolbar.setTitle("Mapa - Segundo Piso");
                }
            }
        });

        DownArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (floor==1){
                    clubsMap.setImageResource(R.drawable.mapa_clubes_piso0);
                    floor-= 1;
                    DownArrow.setVisibility(View.INVISIBLE);
                    toolbar.setTitle("Mapa - Planta Baja");
                }else{
                    clubsMap.setImageResource(R.drawable.mapa_clubes_piso1);
                    floor-= 1;
                    UpArrow.setVisibility(View.VISIBLE);
                    toolbar.setTitle("Mapa - Primer Piso");
                }
            }
        });

    }
}
