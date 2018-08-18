package mx.android.schoolapps.schoolmapp;

import android.app.Application;
import android.os.SystemClock;

public class SchoolmappApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        SystemClock.sleep(3000);
    }
}
