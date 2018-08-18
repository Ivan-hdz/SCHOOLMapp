package mx.android.schoolapps.schoolmapp.Database;

import android.provider.BaseColumns;

public class CafeteriaEntryContract {
    public class CafeteriaEntryColumns implements BaseColumns {
        public static final String TABLE_NAME= "cafeteriaEntry";

        public static final String NOMBRE_COMIDA= "nombreComida";
        public static final String PRECIO= "precio";

        public static final int NOMBRE_COMIDA_COLUMN_INDEX= 1;
        public static final int PRECIO_COLUMN_INDEX= 2;
    }
}
