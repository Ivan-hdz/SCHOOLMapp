package mx.android.schoolapps.schoolmapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by shiro on 29/03/18.
 */

public class Schedule implements Parcelable {

    private String nombreProf;
    private String grupo;
    private String nombreAsignatura;
    private String classroom;
    private String claveLab;
    private String lunes;
    private String martes;
    private String miercoles;
    private String jueves;
    private String viernes;

    public Schedule(String nombreProf, String grupo, String nombreAsignatura, String classroom,
                    String claveLab, String lunes, String martes, String miercoles, String jueves, String viernes){
        this.nombreProf= nombreProf;
        this.grupo= grupo;
        this.nombreAsignatura= nombreAsignatura;
        this.classroom= classroom;
        this.claveLab= claveLab;
        this.lunes= lunes;
        this.martes= martes;
        this.miercoles= miercoles;
        this.jueves= jueves;
        this.viernes= viernes;
    }

    public String getNombreProf() {
        return nombreProf;
    }

    public String getGrupo() {
        return grupo;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public String getClassroom() {
        return classroom;
    }

    public String getClaveLab() {
        return claveLab;
    }

    public String getLunes() {
        return lunes;
    }

    public String getMartes() {
        return martes;
    }

    public String getMiercoles() {
        return miercoles;
    }

    public String getJueves() {
        return jueves;
    }

    public String getViernes() {
        return viernes;
    }

    protected Schedule(Parcel in) {
        nombreProf = in.readString();
        grupo = in.readString();
        nombreAsignatura = in.readString();
        classroom = in.readString();
        claveLab = in.readString();
        lunes = in.readString();
        martes = in.readString();
        miercoles = in.readString();
        jueves = in.readString();
        viernes = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombreProf);
        dest.writeString(grupo);
        dest.writeString(nombreAsignatura);
        dest.writeString(classroom);
        dest.writeString(claveLab);
        dest.writeString(lunes);
        dest.writeString(martes);
        dest.writeString(miercoles);
        dest.writeString(jueves);
        dest.writeString(viernes);
    }

    @SuppressWarnings("unused")
    public static final Creator<Schedule> CREATOR = new Creator<Schedule>() {
        @Override
        public Schedule createFromParcel(Parcel in) {
            return new Schedule(in);
        }

        @Override
        public Schedule[] newArray(int size) {
            return new Schedule[size];
        }
    };
}
