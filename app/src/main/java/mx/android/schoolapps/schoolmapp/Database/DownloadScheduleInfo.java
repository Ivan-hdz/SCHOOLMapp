package mx.android.schoolapps.schoolmapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import mx.android.schoolapps.schoolmapp.Models.Schedule;
import mx.android.shcoolapps.schoolmap.R;

/**
 * Created by shiro on 29/03/18.
 */

public class DownloadScheduleInfo extends AsyncTask<Void,Void,ArrayList<Schedule>> {

    public DownloadScheduleInterface delegate;
    private Context context;
    public static boolean downloadState= false;

    public DownloadScheduleInfo (Context context){
        this.context= context;
    }

    public interface DownloadScheduleInterface{
        void onScheduleDownloaded(ArrayList<Schedule> schedulesList);
    }

    @Override
    protected ArrayList<Schedule> doInBackground(Void... params) {
        String scheduleData;
        ArrayList<Schedule> scheduleList= new ArrayList<>();
        String serverUrl = context.getString(R.string.server_url);
        try{
            scheduleData= downloadData(new URL(serverUrl));
            scheduleList= parseDatafromJason(scheduleData);
            saveScheduleonDB(scheduleList);
        }catch(IOException e){
            e.printStackTrace();
        }

        return scheduleList;
    }

    private void saveScheduleonDB(ArrayList<Schedule> scheduleList) {
        ScheduleDBHelper scheduleDBHelper= new ScheduleDBHelper(context);
        SQLiteDatabase sqLiteDatabase= scheduleDBHelper.getWritableDatabase();

        long count= DatabaseUtils.queryNumEntries(sqLiteDatabase, ScheduleContract.ScheduleColumns.TABLE_NAME);

        System.out.println("There are " + scheduleList.size() + " elements in the array and "
                        + count + " in the DB");

        for(Schedule schedule : scheduleList){
            ContentValues contentValues= new ContentValues();
            contentValues.put(ScheduleContract.ScheduleColumns.NOMBRE_PROFESOR, schedule.getNombreProf());
            contentValues.put(ScheduleContract.ScheduleColumns.GRUPO, schedule.getGrupo());
            contentValues.put(ScheduleContract.ScheduleColumns.NOMBRE_ASIGNATURA, schedule.getNombreAsignatura());
            contentValues.put(ScheduleContract.ScheduleColumns.CLASSROOM, schedule.getClassroom());
            contentValues.put(ScheduleContract.ScheduleColumns.CLAVE_LAB, schedule.getClaveLab());
            contentValues.put(ScheduleContract.ScheduleColumns.LUNES, schedule.getLunes());
            contentValues.put(ScheduleContract.ScheduleColumns.MARTES, schedule.getMartes());
            contentValues.put(ScheduleContract.ScheduleColumns.MIERCOLES, schedule.getMiercoles());
            contentValues.put(ScheduleContract.ScheduleColumns.JUEVES, schedule.getJueves());
            contentValues.put(ScheduleContract.ScheduleColumns.VIERNES, schedule.getViernes());

            sqLiteDatabase.insert(ScheduleContract.ScheduleColumns.TABLE_NAME,null,contentValues);
        }
    }

    @Override
    protected void onPostExecute(ArrayList<Schedule> scheduleList) {
        super.onPostExecute(scheduleList);

        delegate.onScheduleDownloaded(scheduleList);
    }

    private ArrayList<Schedule> parseDatafromJason(String scheduleData){

        ArrayList<Schedule> scheduleList= new ArrayList<>();

        try {
            JSONObject jsonObject= new JSONObject(scheduleData);
            JSONArray recordsetJsonArray= jsonObject.getJSONArray("recordset");

            for(int i=0; i< recordsetJsonArray.length(); i++){
                JSONObject recordsetJsonObject= recordsetJsonArray.getJSONObject(i);

                String nombreProf= recordsetJsonObject.getString("NombreProf");
                String grupo= recordsetJsonObject.getString("Grupo");
                String nombreAsignatura= recordsetJsonObject.getString("NombreAsig");
                String classroom= recordsetJsonObject.getString("Salon");
                String claveLab= recordsetJsonObject.getString("CveLab");
                String lunes= recordsetJsonObject.getString("Lunes");
                String martes= recordsetJsonObject.getString("Martes");
                String miercoles= recordsetJsonObject.getString("Miercoles");
                String jueves= recordsetJsonObject.getString("Jueves");
                String viernes= recordsetJsonObject.getString("Viernes");

                scheduleList.add(new Schedule(nombreProf, grupo, nombreAsignatura, classroom, claveLab,
                                lunes, martes, miercoles, jueves, viernes));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return scheduleList;
    }

    public String downloadData(URL url) throws IOException {
        String jsonResponse= "";
        HttpURLConnection urlConnection= null;
        InputStream inputStream= null;

        try{
            urlConnection= (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();

            inputStream= urlConnection.getInputStream();
            jsonResponse= readFromStream(inputStream);

            downloadState= true;

            ScheduleDBHelper scheduleDBHelper= new ScheduleDBHelper(context);
            SQLiteDatabase sqLiteDatabase= scheduleDBHelper.getWritableDatabase();

            sqLiteDatabase.delete(ScheduleContract.ScheduleColumns.TABLE_NAME, null, null);

        }catch(IOException e){
            System.out.println("NO FUNCIONÓ");
            e.printStackTrace();
        }finally{
            if(urlConnection != null)
                urlConnection.disconnect();
            if(inputStream != null)
                inputStream.close();
        }

        return jsonResponse;
    }

    public String readFromStream(InputStream inputStream) throws IOException{

        StringBuilder output= new StringBuilder();

        if(inputStream != null){
            InputStreamReader inputStreamReader= new InputStreamReader(inputStream, Charset.forName("UTF-8"));

            BufferedReader reader= new BufferedReader(inputStreamReader);
            String line= reader.readLine();

            while(line != null){
                output.append(line);
                line= reader.readLine();
            }
        }

        return output.toString();
    }

    public boolean gotDownloaded(){
        return downloadState;
    }
}
