package mx.android.schoolapps.schoolmapp.Database;

import android.provider.BaseColumns;

public class CafeteriaHallContract {
    public class CafeteriaHallColumns implements BaseColumns {
        public static final String TABLE_NAME= "cafeteriaHall";

        public static final String NOMBRE_COMIDA= "nombreComida";
        public static final String PRECIO= "precio";

        public static final int NOMBRE_COMIDA_COLUMN_INDEX= 1;
        public static final int PRECIO_COLUMN_INDEX= 2;
    }
}
