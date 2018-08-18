package mx.android.schoolapps.schoolmapp.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import mx.android.schoolapps.schoolmapp.Adapters.ScheduleListAdapter;
import mx.android.schoolapps.schoolmapp.Database.ScheduleContract;
import mx.android.schoolapps.schoolmapp.Database.ScheduleDBHelper;
import mx.android.schoolapps.schoolmapp.Models.Classroom;
import mx.android.schoolapps.schoolmapp.Models.Schedule;
import mx.android.shcoolapps.schoolmap.R;

public class ClassroomSecondBuilding extends AppCompatActivity {

    private ScheduleListAdapter scheduleListAdapter;
    private ScheduleDBHelper scheduleDBHelper= new ScheduleDBHelper(this);
    private GridView scheduleListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom_second_building);

        final Toolbar toolbar= findViewById(R.id.toolbarClassroomSecondFloor);
        toolbar.setTitle("Salones - Segundo Edificio");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        scheduleListView= findViewById(R.id.gridViewClassroomsSecondBuilding);
        getSchedulesFromDB();

        registerForContextMenu(scheduleListView);
    }

    public void getSchedulesFromDB(){
        SQLiteDatabase sqLiteDatabase= scheduleDBHelper.getReadableDatabase();

        String sortOrder= ScheduleContract.ScheduleColumns.CLASSROOM + " ASC";

        Cursor cursor= sqLiteDatabase.query(ScheduleContract.ScheduleColumns.TABLE_NAME,null,
                null,null,null,null,sortOrder,null);

        ArrayList<Schedule> scheduleList= new ArrayList<>();

        while(cursor.moveToNext()){
            String nombreProfesor= cursor.getString(ScheduleContract.ScheduleColumns.NOMBRE_PROFESOR_COLUMN_INDEX);
            String grupo= cursor.getString(ScheduleContract.ScheduleColumns.GRUPO_COLUMN_INDEX);
            String nombreAsignatura= cursor.getString(ScheduleContract.ScheduleColumns.NOMBRE_ASIGNATURA_COLUMN_INDEX);
            String classroom= cursor.getString(ScheduleContract.ScheduleColumns.CLASSROOM_COLUMN_INDEX);
            String claveLab= cursor.getString(ScheduleContract.ScheduleColumns.CLAVE_LAB_COLUMN_INDEX);
            String lunes= cursor.getString(ScheduleContract.ScheduleColumns.LUNES_COLUMN_INDEX);
            String martes= cursor.getString(ScheduleContract.ScheduleColumns.MARTES_COLUMN_INDEX);
            String miercoles= cursor.getString(ScheduleContract.ScheduleColumns.MIERCOLES_COLUMN_INDEX);
            String jueves= cursor.getString(ScheduleContract.ScheduleColumns.JUEVES_COLUMN_INDEX);
            String viernes= cursor.getString(ScheduleContract.ScheduleColumns.VIERNES_COLUMN_INDEX);

            scheduleList.add(new Schedule(nombreProfesor, grupo, nombreAsignatura, classroom, claveLab,
                    lunes, martes, miercoles, jueves, viernes));
        }
        cursor.close();

        fillSchedulesList(scheduleList);
    }

    public void getGroupFromDB(String searchGroup){
        SQLiteDatabase sqLiteDatabase= scheduleDBHelper.getReadableDatabase();

        long count= DatabaseUtils.queryNumEntries(sqLiteDatabase, ScheduleContract.ScheduleColumns.TABLE_NAME);
        System.out.println("There are " + count + " elements in the DB");

        // Filter results WHERE "title" = 'My Title'
        String selection= ScheduleContract.ScheduleColumns.GRUPO + " = ?";
        String selectionArgs[]= {searchGroup};

        // How you want the results sorted in the resulting Cursor
        String sortOrder= ScheduleContract.ScheduleColumns.NOMBRE_PROFESOR + " ASC";

        Cursor cursor= sqLiteDatabase.query(
                ScheduleContract.ScheduleColumns.TABLE_NAME, null, selection, selectionArgs, null,
                null, sortOrder);

        ArrayList<Schedule> scheduleList= new ArrayList<>();

        while(cursor.moveToNext()){
            String nombreProfesor= cursor.getString(ScheduleContract.ScheduleColumns.NOMBRE_PROFESOR_COLUMN_INDEX);
            String grupo= cursor.getString(ScheduleContract.ScheduleColumns.GRUPO_COLUMN_INDEX);
            String nombreAsignatura= cursor.getString(ScheduleContract.ScheduleColumns.NOMBRE_ASIGNATURA_COLUMN_INDEX);
            String classroom= cursor.getString(ScheduleContract.ScheduleColumns.CLASSROOM_COLUMN_INDEX);
            String claveLab= cursor.getString(ScheduleContract.ScheduleColumns.CLAVE_LAB_COLUMN_INDEX);
            String lunes= cursor.getString(ScheduleContract.ScheduleColumns.LUNES_COLUMN_INDEX);
            String martes= cursor.getString(ScheduleContract.ScheduleColumns.MARTES_COLUMN_INDEX);
            String miercoles= cursor.getString(ScheduleContract.ScheduleColumns.MIERCOLES_COLUMN_INDEX);
            String jueves= cursor.getString(ScheduleContract.ScheduleColumns.JUEVES_COLUMN_INDEX);
            String viernes= cursor.getString(ScheduleContract.ScheduleColumns.VIERNES_COLUMN_INDEX);

            scheduleList.add(new Schedule(nombreProfesor, grupo, nombreAsignatura, classroom, claveLab,
                    lunes, martes, miercoles, jueves, viernes));
        }
        cursor.close();

        fillSchedulesList(scheduleList);
    }

    private void fillSchedulesList(ArrayList<Schedule> schedulesList){

        schedulesList= filter(schedulesList);

        scheduleListAdapter= new ScheduleListAdapter(this, R.layout.schedule_view, schedulesList);
        scheduleListView.setAdapter(scheduleListAdapter);

        scheduleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Schedule selectedSchedule= scheduleListAdapter.getItem(position);

                Intent intent= new Intent(ClassroomSecondBuilding.this, GroupScheduleActivity.class);
                intent.putExtra("SelectedSchedule", selectedSchedule);

                startActivity(intent);
            }
        });
    }

    public ArrayList<Schedule> filter(final ArrayList<Schedule> schedulesList){
        ArrayList<Schedule> newSchedulesList= new ArrayList<>();

        for(int i= 0; i< schedulesList.size(); i++){
            if(schedulesList.get(i).getClassroom().startsWith("2")){
                if(newSchedulesList.size() > 0){
                    if(!schedulesList.get(i).getGrupo().equals(newSchedulesList.get(newSchedulesList.size() - 1).getGrupo()))
                        newSchedulesList.add(schedulesList.get(i));
                }else
                    newSchedulesList.add(schedulesList.get(i));
            }
        }
        return newSchedulesList;
    }

    private ArrayList<Classroom> getClassrooms_0Floor(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(2001,
                        "",
                        "",
                        "Sin Asignar"));
                add(new Classroom(2002,
                        "",
                        "",
                        "Departamento de Gestión Escolar"));
                add(new Classroom(2003,
                        "",
                        "",
                        "Sala de Profesores"));
                add(new Classroom(2004,
                        "https://sqnasw-bn1305.files.1drv.com/y4mHwJNkrwZSGithIZHmMilZ3zz1zJnP6cu06" +
                                "4Cg3gw9ZcCsk7l2jb0a4tyYRrDJ_hoxw3dc06Ywjas8bzGE4e-okhrDgAW86w-_-er4XhDG1c-uxbJafY" +
                                "EfQCDrs3cLJwrB-GuZeeY6npUOg5a3-tqCyTQebzq020cyAfN2Xcn88VKIHDqab4SUONPGBOILtKpoETG" +
                                "56b6r1YO1X8qZHLPseSG61bBoSoZBav3zS4ZXKc/2004M.png?psid=1",
                        "https://tfnasw-bn1305.files.1drv.com/y4mJBBnu1MmVr21d5_qMMy96CVCBE439ffPPn" +
                                "ziG-UEsGaz0qcUGD1U8p9csskDAj3jCWeV_cNu7Yw7fREdMOlCCDJZWaI37U33u-hWN-IQG8m3BoeZS_V" +
                                "SFYzya27n6666PNYDr4jwR78kvFPBAPnJAwCerLjJz8YjonKPLFnGsVMygm-Gah5_Xv1dcYD8zs2pZzb" +
                                "dV4ecXl_8ld-JB8hepKYBNqi8SNEa36IqaCNHJpE/2004V.png?psid=1",
                        ""));
                add(new Classroom(2005,
                        "https://tvnasw-bn1305.files.1drv.com/y4mUDbtsLlz1Cv2rmItHnzqUSz2CK_Dq3A23f5" +
                                "jtXcjvtv5Gkt3yp4jWaRHTkYSUDhm_pEdhrdJHpOb-FA7VI0li9v2mygv9ECimdZwJUu1t_ZpEQLHbd2HT" +
                                "RoR4n72TZxWHevLUjPxRcGU6uO-3Yitx7i_EyqLHc_2HkhnWL6N-4geooSP2EgcKiDL38kxNMKarBqt189" +
                                "WwPX9Kjaek6225H6KEvC7oH9hSTlmwt7S8D8/2005M.png?psid=1",
                        "https://tqnasw-bn1305.files.1drv.com/y4m0n4L0vwSeSS50TeKyOGT-el27EXFXWfbiyb-" +
                                "czkAm_zMEro07CjHWtEnwG_0eTBwtQ2AYR5UbpieDF5FCKIpjxDlKYZYJX4MXWZaHsYrhnj9-8-HHwqYKI" +
                                "CqVQIUHxmoUa1pX7lbnjNH2oJ2NV3MciaCBX9zs0UhKtlzjz_LMNdBshRMoyjzrP15DgMWcspuFgFW8WIB" +
                                "g0sC7eixikE_A20vR0YeEBtulO3Tk0SEOD8/2005V.png?psid=1",
                        ""));
                add(new Classroom(2006,
                        "https://upnasw-bn1305.files.1drv.com/y4mslZkUeQ4F1XOEMRVknVfcwqB60CWUFDwllo" +
                                "mHDTLes66lYb8DJRvXB_csID85RufJnbTqEsWrNpddSCWy7QLYKksfGwmoSeI_191WvMIfi7rDPFnxuYL1" +
                                "jccpfVC1bZ4KhyKkH0iW0PaV2kt0MTv8QlmmZfHhKlSSUNZ05CvEfasXRnd7HNAOxxHTnyXlOfHQknj872" +
                                "rteSLUIQKEu1H9k_LPcvladcg1U_tbBgJpQg/2006M.png?psid=1",
                        "https://ufnasw-bn1305.files.1drv.com/y4mePL1pVuH_tVDHLQmkujVw4CuIdYgxljj84XX" +
                                "qk-JiDB-lmMf0JM0V2KrycM_XogRHCNwi1txTmWx7-aX2R7mCXl40wXI4fOcB5hpvfUFMvtm1x6Bk1lAS3" +
                                "OTK8-22HnUtU4rO_APy8JmSx1gzGYStCwAgsCbYRphBN6_bGHExO-H0TrAHyAHv2HkjcGA7Olt6f-hluXo" +
                                "-IV_POo7GmlOsNROFdmGJZVWzEtWUZYtrLk/2006V.png?psid=1",
                        ""));
                add(new Classroom(2007,
                        "https://uvnasw-bn1305.files.1drv.com/y4mOQvSwUrOCNWZrU2HSvFopU1nOCDleS17DIB" +
                                "cxgO_W4QYscodpEXpYwzuwNFBuDSdXT_fLckY_99OgBzcZPb-a1nwk-fy7aznvvOeLF25hPFtUlWZEj76GQ" +
                                "jREE4WsCT0rHbozBV7Eef4eCDmWbMy7x6sjfljY0HJrrAs7lkVLaXEe8UwjCSo-vZJRg80x6rw5zbBprXR" +
                                "TFbLYlFbp5vqMZL6Tsaf6czpo9xLepcmUVE/2007M.png?psid=1",
                        "https://uqnasw-bn1305.files.1drv.com/y4mYAdVMZIFnR4iT3E2SeThjG6NYrxuXg69pxBV" +
                                "sTDUmjfEZoJWEJFaGm2RV7nr2gsMgzNj19C9C7uBDSUbcLREYRR24A7IFw0C-g6AWqg9yPz9yHaIH2Y_qnk" +
                                "KKEkQyZVnckyid-aaFbdBEGyiWjBYS7AOOla0tPBh1zGMr5lI45buZj-Nw_beeFKGDp5S4oAJrBCsqem6T" +
                                "Jq_at7lypy192uzL9JjXqbal5J4WU1kXEk/2007V.png?psid=1",
                        ""));
                add(new Classroom(2008,
                        "",
                        "",
                        "Sala de Profesores de Posgrado"));
                add(new Classroom(2009,
                        "",
                        "",
                        "Sala de Profesores de Posgrado"));
                add(new Classroom(2010,
                        "",
                        "",
                        "Aula de Cómputo de Ingeniería en Sistemas Automotrices"));
                add(new Classroom(2011,
                        "",
                        "",
                        "-Laboratorio de posgrado\n-Laboratorio de Inteligencia Artificial"));
                add(new Classroom(2012,
                        "",
                        "",
                        "-Laboratorio de Ingeniería en Sistemas Automotrices\n" +
                                "-Laboratorio de Inteligencia Artificial"));
            }
        };
    }
    private ArrayList<Classroom> getClassrooms_1Floor(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(2101,
                        "",
                        "",
                        "Coordinación de Enlace y Gestión Ténica"));
                add(new Classroom(2102,
                        "",
                        "",
                        "Sin Asignar"));
                add(new Classroom(2103,
                        "",
                        "",
                        "Coordinación de Desarrollo Tecnológico"));
                add(new Classroom(2104,
                        "",
                        "",
                        "Laboratorio de Programación 2"));
                add(new Classroom(2105,
                        "",
                        "",
                        "Laboratorio de Sistemas 1"));
                add(new Classroom(2106,
                        "",
                        "",
                        "Laboratorio de Sistemas 2"));
                add(new Classroom(2107,
                        "",
                        "",
                        "Laboratorio de Cómputo"));
                add(new Classroom(2108,
                        "",
                        "",
                        "Sala de Profesores de Programación y Desarrollo de Sistemas"));
                add(new Classroom(2109,
                        "https://uqlbka-bn1305.files.1drv.com/y4m0kBHhMhjDziwSqksxzAZ" +
                                "PS8SBZfPGPWHVWeA29R_C7P_lugH6D2HeywNeSufvULyxWnvtxqVY2sN-UtCjCnRg0ZP" +
                                "fTY3KswN0WIeSABeAm4Td8yAm1yEL59MtBJlpOHLVF_DumAflU26b0wLOtQXYfu3SnB1" +
                                "bgeuCSkuobIA7IXkKj1GUcdvvPKD5R9zLHddsIO-yPH3z7HttZRVfuLThGeWjkul6-0W" +
                                "EM3eLVVK5lg/2109M.png?psid=1",
                        "https://vplbka-bn1305.files.1drv.com/y4muqfGNF2fHU3OsZAl0ouTz0X" +
                                "6nOc6FA_IiEiWU73zFeO24eXf3y-4DStI8WsrnQ3h3H2HKubRdwWx8po7kxumZeQwmWzbh" +
                                "N8hssW8tn40CKdigCKY3x3B0YfTAZL87c-HvtLl5lZY1A8F3updzlICGlmfsbC2ulm3Etb" +
                                "xLJon8fwQSyyo2Y1Nz6WsSkjscCyQTrL8G5NTlr3_wEIIrCIZJnA2lgsBJBaQzC6KPJZ_8" +
                                "ro/2109V.png?psid=1",
                        ""));
                add(new Classroom(2110,
                        "https://sqmkyg-bn1305.files.1drv.com/y4mJB5uU9ZmwcR5JP36japr4MB8" +
                                "rL5yzmTKUBjFGilF8YvpFjZxiwjy05f2JTjixJwMkezgKifXOp-SOflTYak1zdh9BIsTQqsB" +
                                "5b5gjkS938vDrTgmSA_E13sh3BrwAGoEw-1MVW3SHcpwKzcghtsbS0NvGKRQZm_jeaAJGHJO" +
                                "UnYZccjA1oWjUua6WvUgBy_H4lEvwq_042Q7pUZrG6pJZnMqRW2rydzh2Irx51CHNk4/2110" +
                                "M.png?psid=1",
                        "https://tpmkyg-bn1305.files.1drv.com/y4mFeK4oYHWGz8XXwEd1na_s2VtI" +
                                "4PSoWB3jqAPYCwLN9bm-xLeCKzxOy4P1P4Jrf6Ld0dK4gXcGYiGTKzB-PEgv_uq7Zy4Y5vtf" +
                                "jw8K56R5CHSFVp22-EiTJAYoL2zOd8dnu6gJgb7FFDKoX7B5LpKUWZq3XcPOhDFcXR-WXxPl" +
                                "lzWmVfNjdbYzNRU-P0ENPz7lUMPAA3X8tInGbY_JMUE4R-Ed01VKSq3FndbDsGNiGc/2110V" +
                                ".png?psid=1",
                        ""));
                add(new Classroom(2111,
                        "https://tqmkyg-bn1305.files.1drv.com/y4mIjtj7v2aq75zFDKIllR7LcwlU" +
                                "EH7oV25wGzYips6uq5-IFHBFlss2NzmyUNFdVZmOWYOxCAlT2w1f3WTYzj9gmnH2lF5ounDcZ" +
                                "_dP0j_PdtJdl-GIJUY8DwknJZI6s-9zUxXfjMVEif7uQnyojFLNJ-dX6u6bWx-6WBl2LsSbIp" +
                                "VThLtjpISYIGU7K2rkudHB_pj1X0wGVi9fj1MNOLz6Nvmdbkmc7fjbfP5Kne3Vzc/2111M.pn" +
                                "g?psid=1",
                        "https://tfmkyg-bn1305.files.1drv.com/y4mPE_lWXr6d54S-odQW749k6hS0eK" +
                                "kJAMGcVmg6xOvnJXhHHew1yG7hlrZligefM120lUQHiKUQAC2wQrW3iKGwnqNwEovbHsnVcniE" +
                                "tCADF0PM5078zWJx8QqKluSEIp6GrpUIjNfDSz1B8vMIJkd3VGs8Jdj0JrFz9h2wimiTaZXqGc" +
                                "lkwOGxJHPbG8dRRZDuDAJLrsL1usW5WigUPvDei5CzLR_N3nyZUF2ixwkvK8/2111V.png?ps" +
                                "id=1",
                        ""));
                add(new Classroom(2112,
                        "https://tvmkyg-bn1305.files.1drv.com/y4mTSjPFbj0YEdPVxEMDjaXBQO9XO7" +
                                "guLZXH_9sW2Ne8nxISDsK7iXoriiYu9E_v3fnzkPKuSTdTBs0As5yijpiU2rG6IX3qnbxCCjJNv" +
                                "YJYJjrKl-2pOYAYAWsbPNyaiMN4mnoo7TLAOK9ieMWEW3ULwrXy8lSGKEtlwLo_rCOdwoxH2U1j" +
                                "MbVGBhYS60heEcS0KpN7ApLKzKoNo_vYxNriwrvYEWFg02kfH_3jKLD1og/2112M.png?psid=1",
                        "https://upmkyg-bn1305.files.1drv.com/y4mFNxJ65PwfS5AzDiQaeAnjUKavExD" +
                                "B8JRunTXHnc9cuxPvbXnqexofomatPo-KF7HxFrTPk6HcM0LtahCsC6I6aOZFUowWKsW87SBdS7F" +
                                "seRWpkK3bPVJmmjfedvsRmOYa6mvuZizAh3MLZz0Y7FN0RUc4LLtWjC6X7g56nNqE8JyH__PMFuh" +
                                "NVEuZGzu4_cRLrSX02Xfa2LUFE37j1n7iLrAZ4Iz5J_FsyDoTPNyVX0/2112V.png?psid=1",
                        ""));
                add(new Classroom(2113,
                        "https://ufmkyg-bn1305.files.1drv.com/y4mC25JjOJdWTT8cq8ZOOq9Huf88qWx_L" +
                                "ypKdQzH9pGcJiKWB_X3YQOuhDMnZX2WszE8uVqPu7oG_Wy7QeEVFZJK5J-IMZ_F_4oCIpc1WL22yPK" +
                                "w2CpiIGjat2d4ayA5cygE3XA5vZOczMv6v8rm9vWBnkhdHtdRga3F_HFAHfxbyO6ikXSzm_-fztFAo" +
                                "0TPGg9zafTTmqO4q8OxPVf8UOWJw92BfaAJdJRt2EMj8FB3OU/2113M.png?psid=1",
                        "https://uvmkyg-bn1305.files.1drv.com/y4mTFL5omCuuhv8Jee5KKajUq9vxQJ0WcY" +
                                "hs3mQ9nVy_E1MqlXaAiJE97HePHWQL0ZDkYF1cJ95Gh8mrTRba5PQdezdqH6o9wO4Z4ZHaTbAFV_Db" +
                                "U46S1l3MZMHi_yH2hvCphhVsoshTBsrguDsObl7uW8NJP3s_y7tDT6i-CNtjXVNRjBbzKhwbvGpXC8" +
                                "BIPL8FuSUqYeLI9oSpLi_wCjISahk7WZSvtM5_VRmZGhF1f8/2113V.png?psid=1",
                        ""));
            }
        };
    }
    private ArrayList<Classroom> getClassrooms_2Floor(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(2201,
                        "",
                        "",
                        ""));
                add(new Classroom(2202,
                        "https://vpmkyg-bn1305.files.1drv.com/y4m1AeAs8ea91nKBCLdgXtTHQeL0d44f" +
                                "eT6v5HpOh72--bkBJ2LjhKX71TYyRmXPBXoOfXPReaRTsmzP4eFBUbd-11Zhd_CeOJDY0DodwZmBt" +
                                "ddZWNkCmtL6VIBKpTke6yt08ytIxZCal-rGLFbkKwGDzy1mRxiDCdd2-fbnc1CHdh5FV3cY_ISf1o" +
                                "qJ9L8YrYHvYuwQdI2TReKKN6yu4W2satspwTeNu5quWbhDDpiYuA/2202M.PNG?psid=1",
                        "https://vpmkyg-bn1305.files.1drv.com/y4mzTAZ14SRw_j5vCHOLcZLNZg6AlEVfm" +
                                "djW-1Mw571gwm-wU5eyazs5KFMl6BRYbXhAXV6tCOZTVMTmn2e4Ta0kTsc51Ihn2MANITtvnSHDGD" +
                                "EnsCDtsk1x-oxGrmyB1GhKWFm0oND2t3zYqQw3F31DjdNsi0LEtzr0ABwciQDSdKN13Z1FCZfZv1-" +
                                "joPFHSM3HO_GL0Th1C_hlH2GcFspLUjVvmZdDFlK0bIlt6SsL5U/2202V.PNG?psid=1",
                        ""));
                add(new Classroom(2203,
                        "https://vpmkyg-bn1305.files.1drv.com/y4minVafnOZqxqb8REkBCuJKtMc4IxWN" +
                                "-p7r1PBlQh4CEH8pNIupxbviv1159NwpawdNYlRxr6viudb_FO3aBa7UQmVKpjI-Azcg3yowCbuS" +
                                "59dpeeKbgPr57W-nNGULY62ePZFxKUmhc9q2X899WMvYt94P2Pv_8ic6sv9yzoEHvxbJCIVSAZdJ" +
                                "Rlxq6Jdt5qaQ9iWtJizyqXNHx1XJXg8MaKqfKzSzDbkFvCVAh_st4A/2203M.PNG?psid=1",
                        "https://vpmkyg-bn1305.files.1drv.com/y4mxJ1--kHIeIbv7_2jWUPfJcKAvRCxm" +
                                "UqypchKkuEnGaBboVto6AN7cHOaZ-SqmKM6ZcvbTz9N7pPJxNAfDSsWBkmf9kmM7Zcm9ctkpSzq" +
                                "EGqTC054DB3vRfUg4DL6QgLErhnFRlZCY130ZMKwevi29ZQTAjD-loMXakJ4GFKHur4wGtRB_tS" +
                                "3j6HellzSsH1ggHInBnfu1FfQec9Wp2SIveLeznk5Bur2VGHLTaATpv0/2203V.PNG?psid=1",
                        ""));
                add(new Classroom(2204,
                        "https://vpmkyg-bn1305.files.1drv.com/y4mTlyncHNv6NZi8mqxS77wwa7rpSQC3" +
                                "n4HdeW66GqwxNBEab9BQrUu9LPpS4J17K50AFlKI1eR8pCoP7FQyP90CqFi_CfLJKEJudH5cL_Cx5" +
                                "Pbmg2Xr07PYwI0sLC5rJcF_m3GwtVVb377Nj1DRdNeM_9rN1kRn7ij3Qqlbh5q2kGmPQseNd73ib9" +
                                "7dHEoMqjSAvGLLCQV4AAZ6tnKeO9-C7bWB_UBYGo8QF9uwPx82WI/2204M.PNG?psid=1",
                        "https://vpmkyg-bn1305.files.1drv.com/y4mknQyAe0_Jo2aWnrV0vZ3Wnc6OUwJZr" +
                                "BI5zu3C_u3e2enVUYPtLurU3dcZkyCT8tQDz7LqGyy6qBavpIvggE9M3xU8AJRyQhBr84EfKscYNi" +
                                "zUeqwZZvEAeWj1leaVYYTRpf68UJkBHzhjjs-TSlj6q1YX22wLUTexuJhZDR2eZj_qIMgOPO8cwef" +
                                "lkmKUYZtMry1Z5_DboGDuO7IifhvekTbdOfvchUkzx7i5Uyg8y8/2204V.PNG?psid=1",
                        ""));
                add(new Classroom(2205,
                        "https://vpmkyg-bn1305.files.1drv.com/y4mKVPAm9znW7F3UK69bQEnvpHXdgHLiM" +
                                "ybD406t_cYlnhdgcNkF2DM05crK44w4oRY2FK26_m8Wq2c68Ve4mcdG2g7x3MSTBZtL3Bc1aj_kJC" +
                                "Hj9RN7y-zWpd_RX6MHohwUPiRBdttFhcxVedRugJt9CLp-AuS8VjD7QaXbG4pdzLmM4Tue5il5qAz" +
                                "Bd_C6dytexCoim2dIdNFAfiBIJWpohoLkld8IyDp_mzVa0cll9M/2205M.PNG?psid=1",
                        "https://vpmkyg-bn1305.files.1drv.com/y4m7LiEHiCu9Qvf2UmO4wPnRGZth3wU0F-" +
                                "xkCKXy3PDgz6dUPO3pSWbfes48ckGX7-pYUQZSIbasWoyJDgGzdsXQ43OlZZ2n-ixEAh2RokWH0BiT" +
                                "SFt7x0_uKTGStBt6NMFxLLtOyE-iprDul6ATPpT-qrmrmg0cgQDubRB3XxDOJ3tBFx58wslE2i5eP_" +
                                "vh2IpsFB6SzLfeZl0-Wbp7IVX_jC64tNBOnR2sr3BgsSFZp8/2205V.PNG?psid=1",
                        ""));
                add(new Classroom(2206,
                        "https://vpmkyg-bn1305.files.1drv.com/y4mFiPL7o09bX9nGReaOyWW51ChFz6h6bt" +
                                "QHQbTd4DO07n4o8EM2oPLjY2bHjkiUmkVqvAxcfeXx-lppQMcIjOOuNGs_NtZSZzhPlxdGHinksaBk" +
                                "Hi9Jvc9ESF3wKKFPshrobKFOlBlImCvdpM-l_sTgZEOAXYTE4QbYvHx3LDVonUgnZBaUdu8DIJJk_H" +
                                "w1jdvzUBUI8ETSgtV5BiQgNHxytBhFyXPbqfUAU0B9yfZ3Bc/2206M.PNG?psid=1",
                        "https://vpmkyg-bn1305.files.1drv.com/y4mbcLi6-Rp-dvuYKcFQ3U_toBARxFpJ7Qs" +
                                "gAmBp6ZOGNMd9IEhU74N1pYFuBwe0oNEROgF6IZ1U4JxOBuzVQImUlOYt-m_sZe5V_Iv_eC_crYUduo" +
                                "V_GLYDYM3HwQNcQQUBbDnrEOwLM4_O2JU9j_9m1BkGOuDgT5grbHTlk8TOftjdN2RE2_ntY77q5za1p" +
                                "BtVQTYA-B5Y-j8ciLltXZsJILr_M86g0ugg1B_OGejVuY/2206V.PNG?psid=1",
                        ""));
                add(new Classroom(2207,
                        "https://vpmkyg-bn1305.files.1drv.com/y4m3SKfBih86cCXLl46jhPTGrqInBAnlBL" +
                                "rQ1qJ8j4RjZ3oLW6z0nt_3uNbJXcUgE9RAguW11t_k2zPFi92F5o52kTU5b4hT-aF7u2a98aWvBkrBU" +
                                "vgHvCrAwg4eUuMVPR-fs-rbwjreKU7YtyDY5P34xP-A5nKQ6VfxLvo1BI7_W7da6SkRimwngtDY_nl" +
                                "S77csXYsOI9FySixfaJwu-mVspwdg6Iif33rJNusWQB_Dc8/2207M.PNG?psid=1",
                        "https://vpmkyg-bn1305.files.1drv.com/y4mDBbD0ijE_QziV_SSVZCB3he49x1pDkCp" +
                                "2MSa5qiLonLzWkDTV3t0MEH3KWazfWDaDx7mX0iYD7oqkYCRI1xikvQs1eQKptO5Ica3eHevFiifnAQ" +
                                "6Pjl0lFVzJcvU6x-EdXQTtMTcap4oZ4eBfiCVujz1UgBCoYD1QiYa4G4kXzKYKKSOMcjRYTaG85kkuJ" +
                                "SOZhUh5B66ZFQGP08z-v7yoSfHyFyMmsrqlQCCIYjkvao/2207V.PNG?psid=1",
                        ""));
                add(new Classroom(2208,
                        "",
                        "",
                        "Sala de Profesores de Ciencias Básicas"));
                add(new Classroom(2209,
                        "https://vpmkyg-bn1305.files.1drv.com/y4mBWJFmchN76japJmUN4GRbQ4iAnSw5_ex" +
                                "IxIsRTWkm4jhWnN8hJisHyGJRirMGqgB0b_AXuwGDplMU25IgA1bk5ygZfwA1e2dcWCQliaB5DVoclc_" +
                                "gmVj0i2_nnYP6FMNEaW6VcNu2UJRK6HLLpwsYrm-vF7XEjv0Tn2Aia0G_vZYwcq6vg7hzD-SBxW2kXPq" +
                                "QNxINzMeZaer53lK6DQprsAjwtyqUF6ozSWSEP3qfh8/2209M.PNG?psid=1",
                        "https://vpmkyg-bn1305.files.1drv.com/y4mO-v0pRrBgDhSpxS3nj6oQU1RTGPB0oZ4A" +
                                "nahFEAXA5hd35jNrnUqMzOvkIYsJJg4S5ivrLQf3fRtSBsBi-gFGAVcnM68eYGiqOIxoZhSPpomK0C4v" +
                                "DBIGIe9gWIipeFiWdmnyIeeXiT3q9ylaJ6xWT-MbPMwHhndQtwtZFms2WuXYVz6M5zp7Hu7quNhzMjuN" +
                                "ORnIuNhwybg1VD9EFbSX7YZDwRmGuWP9tOYEPGO4sM/2209V.PNG?psid=1",
                        ""));
                add(new Classroom(2210,
                        "https://vpmkyg-bn1305.files.1drv.com/y4mtKKP9QNMfSkcKB_1dpSJ4dDnuelU7xYV" +
                                "w_oo7FPFa_8sHfbnAzV1zQJ-aq1O9u17qUlNk9YPjxtqsiadNVoL5jZ1Mjb-RtZ5FO_-W98O-DXNGufu" +
                                "wJcrYwZI0Tu51dKXRZRWBMxqzR6kugIakQhucNV7xMeqbK9peGMMi9JXjl8UriU-PB2BaJd47-BzU6bB" +
                                "AJDe2C-pCTZGqkZ1-_erHVri4mASEJVSj_di7h-94SQ/2210M.PNG?psid=1",
                        "https://vpmkyg-bn1305.files.1drv.com/y4m_oymiqX2PEShJnyxS8JgYIcsbMQVNBby" +
                                "3vs1tLffXoTgir1cNPw53vGfTorH1QX86qfl8gNfNBf-WaiS-21RBedfCNJHNk0dtvRghhQrTmyIxgl" +
                                "JmmYJepqV9hOAlkAGbbN7qMxDtuwJxPcQRIByQw7XlxyntcpLotBmYdTWN_7DwShGncUetA6bcyjKGv" +
                                "f54N-ixwN1KqZ9c-z6--bmnVilNsGOg6IsQaCZchIYrgU/2210V.PNG?psid=1",
                        ""));
                add(new Classroom(2211,
                        "https://vpmkyg-bn1305.files.1drv.com/y4mgTqRp1oHqR3lXMdiPm1momVwtNtdUMCD" +
                                "EgWIbBnT8SthCa8kfOiXeJWMINL_x7raQy66O8KatYZh_mx0CyPh7fn6pr90CAGzZLfLEhyWcrSyVsm" +
                                "Baa_gHHUFsch-f0sIMI6_Ac3X5iJ19XXJbU-8CMOtvcXPxCuQA2s8wbjtKWk8-djFQ6O84T7gV25lcL" +
                                "BvG0rb0VN4Z8D1_QvCTF3IWoUcESiQbuEXClK_JSH-3xs/2211M.PNG?psid=1",
                        "https://vpmkyg-bn1305.files.1drv.com/y4mahrHT5XLPoBZh1sUQ6EUII0NTHAenpA7j" +
                                "WV8UG-Yo1ARLnVadiZE-4fDLfB3ENIfItfnZDudMr73hXVISNL1Xzpz6PGXXPs--2l2mzaETqYNVM77w" +
                                "OpT5fSp38hVxtfphs9wEb4K4_Uc6ezZYEMfHNKD4zTBarZ-KYlHbqmrVNVaz20SI9EE1caIFMpYEYAZ7" +
                                "d77i_aaOQC7wqq7RfdFwPzoFDgVt-Xgc1YqK7xJrDo/2211V.PNG?psid=1",
                        ""));
                add(new Classroom(2212,
                        "https://vpmkyg-bn1305.files.1drv.com/y4mi8Mc7wFk5mhyUcGCbOl2NUhaE5JIz8_PU" +
                                "rT2vghb05SLc3TV_eQ1PHjYblxn4N0ZMIIyjekcYZXXD-YX4WVRA8HzxqgJNnPcN1CYpiMoQqNKrxaLY" +
                                "sDdwxwBzCCIjgyGMxNWC8yKEiwkqhwceHCtGk9gRG8-rEgC0InYjUSuFyjOF8Z6zQq3X1ovugQNSWgR" +
                                "lh1blPAupfpVOOyMjL-fCtiXXYcQHf2SF8YA4xpQtUU/2212M.PNG?psid=1",
                        "https://vpmkyg-bn1305.files.1drv.com/y4mPd8ELMaAY9dhIG6jvnaszyKYtkzhF9ef2" +
                                "OngXfyjtqEmKj8gfeZX8v5umZD5jC67YRIze8cDP8gEvU8pAtqy97BsKxy20NDzb3XmRqDCCtnuL8fgO" +
                                "-Fqjc-6fl8qP18Jco4hFILU74bmyu9VV3XaepLGga19Iv57EH5EYBP-gNWUBrdVYffWj_rJcWlZN01lt" +
                                "EiVzVLFNljMvgCfVsCzph559CX32ATmXbA5CgqO8vo/2212V.PNG?psid=1",
                        ""));
                add(new Classroom(2213,
                        "https://vpmkyg-bn1305.files.1drv.com/y4mBrQRal9e3CU0wP-_Cw9srnK82C3QAYAYU" +
                                "A4WnIBV4igiFEFxni1_172eNH1ZqnjAE0bXVeuh32t25ir0t4vD1L6JlbPJkLaoDonDQVyVsfgqXHkjD" +
                                "sBfVpCLLPcV1YGGr6H_nkZ_77sT5GE23oAAjbPGBI2rlqyfrTYyB0J4feU70jJSfVf7GkkiUt2nc5VPY" +
                                "WXVSagnpux3OUBtS7hVdGKPR2OlLF0V975upVRPevU/2213M.PNG?psid=1",
                        "https://vpmkyg-bn1305.files.1drv.com/y4m29YeahvyKB0LK8CDje6t78F3exO6XFONK" +
                                "Q-85pnAoOAwGeRKGgP_WBV5_jKvZhJaINhWsnP1SwEUlioa_o5XJXg5L-BBUZs4HUsSX7g4K81ow0RGa" +
                                "vK0YCWZ0rN1-49NA4K5zbE2hZX-d1Te9QCgZa9_tOtg_sW3bSiJqEWdQ6-uds0NBunv0-sXGN89XL0bb" +
                                "QSqRzpJqi2nciyDXy8WWZBsseBejOuLGf24N8ZsS68/2213V.PNG?psid=1",
                        ""));
            }
        };
    }
}
