package mx.android.schoolapps.schoolmapp.Models;

/**
 * Created by Shiro on 31/10/2017.
 */

public class Classroom {

    private int id;
    private String morningSchedule;
    private String eveningSchedule;
    private String description;

    public Classroom(int id, String morningSchedule, String eveningSchedule, String description) {
        this.id = id;
        this.morningSchedule= morningSchedule;
        this.eveningSchedule= eveningSchedule;
        this.description= description;
    }

    public int getId() {
        return id;
    }

    public String getMorningSchedule() {
        return morningSchedule;
    }

    public String getEveningSchedule() {
        return eveningSchedule;
    }

    public String getDescription() {
        return description;
    }
}
