package mx.android.schoolapps.schoolmapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import mx.android.schoolapps.schoolmapp.Models.Schedule;
import mx.android.shcoolapps.schoolmap.R;

public class ScheduleDetailActivity extends AppCompatActivity {

    private TextView schedulePeriodoEscolar;
    private TextView scheduleNombreProfesor;
    private TextView scheduleGrupo;
    private TextView scheduleNombreAsignatura;
    private TextView scheduleClassroom;
    private TextView scheduleClaveLab;
    private TextView scheduleLunes;
    private TextView scheduleMartes;
    private TextView scheduleMiercoles;
    private TextView scheduleJueves;
    private TextView scheduleViernes;
    private TextView scheduleNombreAcademia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_detail_activity);

        schedulePeriodoEscolar= findViewById(R.id.textViewDetailPeriodoEscolar);
        scheduleNombreProfesor= findViewById(R.id.textViewDetailNombreProfesor);
        scheduleGrupo= findViewById(R.id.textViewDetailGrupo);
        scheduleNombreAsignatura= findViewById(R.id.textViewDetailNombreAsignatura);
        scheduleClassroom= findViewById(R.id.textViewDetailClassroom);
        scheduleClaveLab= findViewById(R.id.textViewDetailClaveLab);
        scheduleLunes= findViewById(R.id.textViewDetailLunes);
        scheduleMartes= findViewById(R.id.textViewDetailMartes);
        scheduleMiercoles= findViewById(R.id.textViewDetailMiercoles);
        scheduleJueves= findViewById(R.id.textViewDetailJueves);
        scheduleViernes= findViewById(R.id.textViewDetailViernes);
        scheduleNombreAcademia= findViewById(R.id.textViewDetailNombreAcademia);

        Bundle extras= getIntent().getExtras();
        Schedule schedule= extras.getParcelable("SelectedSchedule");

        if(schedule != null){
            scheduleNombreProfesor.setText("Nombre Profesor: " + schedule.getNombreProf());
            scheduleGrupo.setText("Grupo: " + schedule.getGrupo());
            scheduleNombreAsignatura.setText("Nombre Asignatura: " + schedule.getNombreAsignatura());
            scheduleClassroom.setText("Classroom: " + schedule.getClassroom());
            scheduleClaveLab.setText("Clave Lab. : " + schedule.getClaveLab());
            scheduleLunes.setText("Lunes: " + schedule.getLunes());
            scheduleMartes.setText("Martes: " + schedule.getMartes());
            scheduleMiercoles.setText("Miercoles: " + schedule.getMiercoles());
            scheduleJueves.setText("Jueves: " + schedule.getJueves());
            scheduleViernes.setText("Viernes: " + schedule.getViernes());
        }
    }

    public String getDateFormat(long datetime){
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd/MMMM/yyyy - hh:mm:ss", Locale.getDefault());
        Date date= new Date(datetime);

        return simpleDateFormat.format(date);
    }
}
