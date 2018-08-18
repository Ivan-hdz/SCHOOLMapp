package mx.android.schoolapps.schoolmapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shiro on 30/03/18.
 */

public class ScheduleDBHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION= 1;
    private static final String DATABASE_NAME= "schedules.db";

    public ScheduleDBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Schedule_Database= "CREATE TABLE " +
                ScheduleContract.ScheduleColumns.TABLE_NAME + " (" +
                ScheduleContract.ScheduleColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ScheduleContract.ScheduleColumns.NOMBRE_PROFESOR + " TEXT NOT NULL," +
                ScheduleContract.ScheduleColumns.GRUPO + " TEXT NOT NULL," +
                ScheduleContract.ScheduleColumns.NOMBRE_ASIGNATURA + " TEXT NOT NULL," +
                ScheduleContract.ScheduleColumns.CLASSROOM + " TEXT NOT NULL," +
                ScheduleContract.ScheduleColumns.CLAVE_LAB + " TEXT NOT NULL," +
                ScheduleContract.ScheduleColumns.LUNES + " TEXT NOT NULL," +
                ScheduleContract.ScheduleColumns.MARTES + " TEXT NOT NULL," +
                ScheduleContract.ScheduleColumns.MIERCOLES + " TEXT NOT NULL," +
                ScheduleContract.ScheduleColumns.JUEVES + " TEXT NOT NULL," +
                ScheduleContract.ScheduleColumns.VIERNES + " TEXT NOT NULL" +
                ")";

        String Cafeteria_EntryDatabase= "CREATE TABLE " +
                CafeteriaEntryContract.CafeteriaEntryColumns.TABLE_NAME + " (" +
                CafeteriaEntryContract.CafeteriaEntryColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                CafeteriaEntryContract.CafeteriaEntryColumns.NOMBRE_COMIDA + " TEXT NOT NULL," +
                CafeteriaEntryContract.CafeteriaEntryColumns.PRECIO + " TEXT NOT NULL" +
                ")";

        String Cafeteria_HallDatabase= "CREATE TABLE " +
                CafeteriaHallContract.CafeteriaHallColumns.TABLE_NAME + " (" +
                CafeteriaHallContract.CafeteriaHallColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                CafeteriaHallContract.CafeteriaHallColumns.NOMBRE_COMIDA + " TEXT NOT NULL," +
                CafeteriaHallContract.CafeteriaHallColumns.PRECIO + " TEXT NOT NULL" +
                ")";

        String StationaryGroundFloor_Database= "CREATE TABLE " +
                StationaryGroundFloorContract.StationaryGroundFloorColumns.TABLE_NAME + " (" +
                StationaryGroundFloorContract.StationaryGroundFloorColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                StationaryGroundFloorContract.StationaryGroundFloorColumns.NOMBRE_ARTICULO + " TEXT NOT NULL," +
                StationaryGroundFloorContract.StationaryGroundFloorColumns.DESCRIPCION + " TEXT NOT NULL" +
                ")";

        String StationaryFirstFloor_Database= "CREATE TABLE " +
                StationaryFirstFloorContract.StationaryFirstFloorColumns.TABLE_NAME + " (" +
                StationaryFirstFloorContract.StationaryFirstFloorColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                StationaryFirstFloorContract.StationaryFirstFloorColumns.NOMBRE_ARTICULO + " TEXT NOT NULL," +
                StationaryFirstFloorContract.StationaryFirstFloorColumns.DESCRIPCION + " TEXT NOT NULL" +
                ")";

        db.execSQL(Schedule_Database);
        db.execSQL(Cafeteria_EntryDatabase);
        db.execSQL(Cafeteria_HallDatabase);
        db.execSQL(StationaryGroundFloor_Database);
        db.execSQL(StationaryFirstFloor_Database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ScheduleContract.ScheduleColumns.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CafeteriaEntryContract.CafeteriaEntryColumns.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + StationaryGroundFloorContract.StationaryGroundFloorColumns.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + StationaryFirstFloorContract.StationaryFirstFloorColumns.TABLE_NAME);
        onCreate(db);
    }
}
