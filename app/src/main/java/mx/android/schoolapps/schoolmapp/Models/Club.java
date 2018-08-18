package mx.android.schoolapps.schoolmapp.Models;

/**
 * Created by Shiro on 16/10/2017.
 */

public class Club {

    private String name;
    private String professor;
    private String classroom;
    private String schedule;
    private String contactInfo;
    private String gmailMail;

    public Club(String name, String professor, String classroom, String schedule, String contactInfo, String gmailMail){
        this.name= name;
        this.professor= professor;
        this.classroom= classroom;
        this.schedule= schedule;
        this.contactInfo= contactInfo;
        this.gmailMail= gmailMail;

    }

    public String getName() {
        return name;
    }

    public String getProfessor() {
        return professor;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getClassroom() {
        return classroom;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getGmailInfo(){
        return gmailMail;
    }
}
