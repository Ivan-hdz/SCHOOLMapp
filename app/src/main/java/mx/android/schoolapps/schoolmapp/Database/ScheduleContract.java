package mx.android.schoolapps.schoolmapp.Database;

import android.provider.BaseColumns;

/**
 * Created by shiro on 30/03/18.
 */

public class ScheduleContract {

        public class ScheduleColumns implements BaseColumns {
            public static final String TABLE_NAME= "schedules";

            public static final String NOMBRE_PROFESOR= "nombreProf";
            public static final String GRUPO= "grupo";
            public static final String NOMBRE_ASIGNATURA= "nombreAsignatura";
            public static final String CLASSROOM= "classroom";
            public static final String CLAVE_LAB= "claveLab";
            public static final String LUNES= "lunes";
            public static final String MARTES= "martes";
            public static final String MIERCOLES= "miercoles";
            public static final String JUEVES= "jueves";
            public static final String VIERNES= "viernes";

            public static final int NOMBRE_PROFESOR_COLUMN_INDEX= 1;
            public static final int GRUPO_COLUMN_INDEX= 2;
            public static final int NOMBRE_ASIGNATURA_COLUMN_INDEX= 3;
            public static final int CLASSROOM_COLUMN_INDEX= 4;
            public static final int CLAVE_LAB_COLUMN_INDEX= 5;
            public static final int LUNES_COLUMN_INDEX= 6;
            public static final int MARTES_COLUMN_INDEX= 7;
            public static final int MIERCOLES_COLUMN_INDEX= 8;
            public static final int JUEVES_COLUMN_INDEX= 9;
            public static final int VIERNES_COLUMN_INDEX= 10;
        }
}