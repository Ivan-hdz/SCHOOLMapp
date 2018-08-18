package mx.android.schoolapps.schoolmapp.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import mx.android.schoolapps.schoolmapp.Models.StudentCounselor;
import mx.android.shcoolapps.schoolmap.R;

public class StudentCounselorActivity extends AppCompatActivity {

    private ImageView studentCounselorPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_counselor);

        Toolbar toolbar= findViewById(R.id.StudentCounselorInfoToolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Bundle extras= getIntent().getExtras();
        final StudentCounselor studentCounselor= extras.getParcelable("selectedStudentCounselor");

        studentCounselorPic= findViewById(R.id.imageViewStudentCounselorPic);
        TextView studentCounselorName= findViewById(R.id.studentCounselorName);
        TextView studentCounselorComissions= findViewById(R.id.studentCounselorComission);
        final Button studentCounselorContactInfo= findViewById(R.id.studentCounselorContactInfo);

        setStudentCounselorPic(studentCounselor.getStudentPic());
        studentCounselorName.setText(studentCounselor.getName());
        studentCounselorContactInfo.setText(studentCounselor.getContactInfo().toLowerCase());
        //Boton para abrir
        studentCounselorContactInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Uri uri = Uri.parse(studentCounselor.getContactInfo());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
            }
        });
        if(studentCounselor.getComission().isEmpty())
            studentCounselorComissions.setText(String.valueOf("There are no defined comissions"));
        else
            studentCounselorComissions.setText(studentCounselor.getComission());
    }

    private void setStudentCounselorPic(int imageSource){
        Glide.with(this).load(imageSource).into(studentCounselorPic);
    }
}
