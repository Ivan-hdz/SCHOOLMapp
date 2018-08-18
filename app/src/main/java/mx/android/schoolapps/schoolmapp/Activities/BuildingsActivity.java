package mx.android.schoolapps.schoolmapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import mx.android.shcoolapps.schoolmap.R;

public class BuildingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buildings);

        Toolbar toolbar= findViewById(R.id.toolbarBuildingsActivity);
        toolbar.setTitle("Edificios");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        TextView textViewFirstBuilding = findViewById(R.id.textViewFirstFloor);
        TextView textViewMidBuilding = findViewById(R.id.textViewSecondFloor);
        TextView textViewSecondBuilding = findViewById(R.id.textViewThirdFloor);

        textViewFirstBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuildingsActivity.this, ClassroomFirstBuilding.class);
                startActivity(intent);
            }
        });

        textViewMidBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuildingsActivity.this, ClassroomMidBuilding.class);
                startActivity(intent);
            }
        });

        textViewSecondBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuildingsActivity.this, ClassroomSecondBuilding.class);
                startActivity(intent);
            }
        });
    }
}
