package mx.android.schoolapps.schoolmapp.Database;

import android.provider.BaseColumns;

public class StationaryFirstFloorContract {
    public class StationaryFirstFloorColumns implements BaseColumns {
        public static final String TABLE_NAME= "stationaryFirstFloor";

        public static final String NOMBRE_ARTICULO= "nombreArticulo";
        public static final String DESCRIPCION= "descripcion";

        public static final int NOMBRE_ARTICULO_COLUMN_INDEX= 1;
        public static final int DESCRIPCION_COLUMN_INDEX= 2;
    }
}
