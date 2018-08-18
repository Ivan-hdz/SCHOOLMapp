package mx.android.schoolapps.schoolmapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shiro on 29/10/2017.
 */

public class StudentCounselor implements Parcelable{

    private String name;
    private String comission;
    private int studentPic;
    private String contactInfo;

    public StudentCounselor(String name, String comission, int studentPic, String contactInfo) {
        this.name = name;
        this.comission = comission;
        this.studentPic = studentPic;
        this.contactInfo = contactInfo;
    }

    public String getName() {
        return name;
    }

    public String getComission() {
        return comission;
    }

    public int getStudentPic() {
        return studentPic;
    }

    public String getContactInfo() {
        return contactInfo;
    }


    protected StudentCounselor(Parcel in) {
        name = in.readString();
        comission = in.readString();
        studentPic = in.readInt();
        contactInfo = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(comission);
        dest.writeInt(studentPic);
        dest.writeString(contactInfo);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<StudentCounselor> CREATOR = new Parcelable.Creator<StudentCounselor>() {
        @Override
        public StudentCounselor createFromParcel(Parcel in) {
            return new StudentCounselor(in);
        }

        @Override
        public StudentCounselor[] newArray(int size) {
            return new StudentCounselor[size];
        }
    };
}