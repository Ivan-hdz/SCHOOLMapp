package mx.android.schoolapps.schoolmapp.Models;

import java.util.ArrayList;

/**
 * Created by Shiro on 30/11/2017.
 */

public class Days{

    public static class Monday{
        public ArrayList<Classroom> _0700_0830(){
            return get_0700_0830_Monday();
        }
        public ArrayList<Classroom> _0830_1000(){
            return get_0830_1000_Monday();
        }
        public ArrayList<Classroom> _1030_1200(){
            return get_1030_1200_Monday();
        }
        public ArrayList<Classroom> _1200_1330(){
            return get_1200_1330_Monday();
        }
        public ArrayList<Classroom> _1330_1500(){
            return get_1330_1500_Monday();
        }
        public ArrayList<Classroom> _1500_1630(){
            return get_1500_1630_Monday();
        }
        public ArrayList<Classroom> _1630_1800(){
            return get_1630_1800_Monday();
        }
        public ArrayList<Classroom> _1830_2000(){
            return get_1830_2000_Monday();
        }
        public ArrayList<Classroom> _2000_2200(){
            return get_2000_2200_Monday();
        }
        public ArrayList<Classroom> getAllClassrooms(){
            return get_All_Classrooms();
        }
    }

    public static class Tuesday{
        public ArrayList<Classroom> _0700_0830(){
            return get_0700_0830_Tuesday();
        }
        public ArrayList<Classroom> _0830_1000(){
            return get_0830_1000_Tuesday();
        }
        public ArrayList<Classroom> _1030_1200(){
            return get_1030_1200_Tuesday();
        }
        public ArrayList<Classroom> _1200_1330(){
            return get_1200_1330_Tuesday();
        }
        public ArrayList<Classroom> _1330_1500(){
            return get_1330_1500_Tuesday();
        }
        public ArrayList<Classroom> _1500_1630(){
            return get_1500_1630_Tuesday();
        }
        public ArrayList<Classroom> _1630_1800(){
            return get_1630_1800_Tuesday();
        }
        public ArrayList<Classroom> _1830_2000(){
            return get_1830_2000_Tuesday();
        }
        public ArrayList<Classroom> _2000_2200(){
            return get_2000_2200_Tuesday();
        }
        public ArrayList<Classroom> getAllClassrooms(){
            return get_All_Classrooms();
        }
    }

    public static class Wednesday{
        public ArrayList<Classroom> _0700_0830(){
            return get_0700_0830_Wednesday();
        }
        public ArrayList<Classroom> _0830_1000(){
            return get_0830_1000_Wednesday();
        }
        public ArrayList<Classroom> _1030_1200(){
            return get_1030_1200_Wednesday();
        }
        public ArrayList<Classroom> _1200_1330(){
            return get_1200_1330_Wednesday();
        }
        public ArrayList<Classroom> _1330_1500(){
            return get_1330_1500_Wednesday();
        }
        public ArrayList<Classroom> _1500_1630(){
            return get_1500_1630_Wednesday();
        }
        public ArrayList<Classroom> _1630_1800(){
            return get_1630_1800_Wednesday();
        }
        public ArrayList<Classroom> _1830_2000(){
            return get_1830_2000_Wednesday();
        }
        public ArrayList<Classroom> _2000_2200(){
            return get_2000_2200_Wednesday();
        }
        public ArrayList<Classroom> getAllClassrooms(){
            return get_All_Classrooms();
        }
    }

    public static class Thursday{
        public ArrayList<Classroom> _0700_0830(){
            return get_0700_0830_Thursday();
        }
        public ArrayList<Classroom> _0830_1000(){
            return get_0830_1000_Thursday();
        }
        public ArrayList<Classroom> _1030_1200(){
            return get_1030_1200_Thursday();
        }
        public ArrayList<Classroom> _1200_1330(){
            return get_1200_1330_Thursday();
        }
        public ArrayList<Classroom> _1330_1500(){
            return get_1330_1500_Thursday();
        }
        public ArrayList<Classroom> _1500_1630(){
            return get_1500_1630_Thursday();
        }
        public ArrayList<Classroom> _1630_1800(){
            return get_1630_1800_Thursday();
        }
        public ArrayList<Classroom> _1830_2000(){
            return get_1830_2000_Thursday();
        }
        public ArrayList<Classroom> _2000_2200(){
            return get_2000_2200_Thursday();
        }
        public ArrayList<Classroom> getAllClassrooms(){
            return get_All_Classrooms();
        }
    }

    public static class Friday{
        public ArrayList<Classroom> _0700_0830(){
            return get_0700_0830_Friday();
        }
        public ArrayList<Classroom> _0830_1000(){
            return get_0830_1000_Friday();
        }
        public ArrayList<Classroom> _1030_1200(){
            return get_1030_1200_Friday();
        }
        public ArrayList<Classroom> _1200_1330(){
            return get_1200_1330_Friday();
        }
        public ArrayList<Classroom> _1330_1500(){
            return get_1330_1500_Friday();
        }
        public ArrayList<Classroom> _1500_1630(){
            return get_1500_1630_Friday();
        }
        public ArrayList<Classroom> _1630_1800(){
            return get_1630_1800_Friday();
        }
        public ArrayList<Classroom> _1830_2000(){
            return get_1830_2000_Friday();
        }
        public ArrayList<Classroom> _2000_2200(){
            return get_2000_2200_Friday();
        }
        public ArrayList<Classroom> getAllClassrooms(){
            return get_All_Classrooms();
        }
    }

    private static ArrayList<Classroom> get_0700_0830_Monday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1011,"","",""));
                add(new Classroom(1013,"","",""));
                add(new Classroom(1112,"","",""));
                add(new Classroom(1204,"","",""));
                add(new Classroom(1205,"","",""));
                add(new Classroom(1212,"","",""));
                add(new Classroom(2109,"","",""));
                add(new Classroom(2111,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2212,"","",""));
                add(new Classroom(2213,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_0830_1000_Monday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1112,"","",""));
                add(new Classroom(2113,"","",""));

            }
        };
    }
    private static ArrayList<Classroom> get_1030_1200_Monday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1112,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_1200_1330_Monday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1112,"","",""));
                add(new Classroom(2110,"","",""));
                add(new Classroom(2111,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_1330_1500_Monday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1202,"","",""));
                add(new Classroom(1203,"","",""));
                add(new Classroom(1204,"","",""));
                add(new Classroom(2004,"","",""));
                add(new Classroom(2006,"","",""));
                add(new Classroom(2007,"","",""));
                add(new Classroom(2109,"","",""));
                add(new Classroom(2110,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2113,"","",""));
                add(new Classroom(2203,"","",""));
                add(new Classroom(2204,"","",""));
                add(new Classroom(2205,"","",""));
                add(new Classroom(2207,"","",""));
                add(new Classroom(2212,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_1500_1630_Monday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1111,"","",""));
                add(new Classroom(1112,"","",""));
                add(new Classroom(1203,"","",""));
                add(new Classroom(1205,"","",""));
                add(new Classroom(2109,"","",""));
                add(new Classroom(2110,"","",""));
                add(new Classroom(2111,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2113,"","",""));
                add(new Classroom(2211,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_1630_1800_Monday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1110,"","",""));
                add(new Classroom(1210,"","",""));
                add(new Classroom(2109,"","",""));
                add(new Classroom(2111,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2113,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_1830_2000_Monday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1012,"","",""));
                add(new Classroom(1110,"","",""));
                add(new Classroom(1111,"","",""));
                add(new Classroom(1204,"","",""));
                add(new Classroom(1205,"","",""));
                add(new Classroom(2005,"","",""));
                add(new Classroom(2109,"","",""));
                add(new Classroom(2110,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2113,"","",""));
                add(new Classroom(2211,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_2000_2200_Monday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1011,"","",""));
                add(new Classroom(1013,"","",""));
                add(new Classroom(1014,"","",""));
                add(new Classroom(1110,"","",""));
                add(new Classroom(1111,"","",""));
                add(new Classroom(1203,"","",""));
                add(new Classroom(1206,"","",""));
                add(new Classroom(1207,"","",""));
                add(new Classroom(1210,"","",""));
                add(new Classroom(2109,"","",""));
                add(new Classroom(2111,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2113,"","",""));
                add(new Classroom(2206,"","",""));
                add(new Classroom(2210,"","",""));
                add(new Classroom(2211,"","",""));
                add(new Classroom(2212,"","",""));
                add(new Classroom(2213,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_0700_0830_Tuesday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1205,"","",""));
                add(new Classroom(1212,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2113,"","",""));
                add(new Classroom(2212,"","",""));
                add(new Classroom(2213,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_0830_1000_Tuesday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1112,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_1030_1200_Tuesday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1212,"","",""));
                add(new Classroom(2109,"","",""));
                add(new Classroom(2110,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_1200_1330_Tuesday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1011,"","",""));
                add(new Classroom(1112,"","",""));
                add(new Classroom(1202,"","",""));
                add(new Classroom(1203,"","",""));
                add(new Classroom(1205,"","",""));
                add(new Classroom(1212,"","",""));
                add(new Classroom(2203,"","",""));
                add(new Classroom(2004,"","",""));
                add(new Classroom(2005,"","",""));
                add(new Classroom(2006,"","",""));
                add(new Classroom(2007,"","",""));
                add(new Classroom(2110,"","",""));
                add(new Classroom(2111,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2113,"","",""));
                add(new Classroom(2205,"","",""));
                add(new Classroom(2206,"","",""));
                add(new Classroom(2213,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_1330_1500_Tuesday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1202,"","",""));
                add(new Classroom(1203,"","",""));
                add(new Classroom(1204,"","",""));
                add(new Classroom(1205,"","",""));
                add(new Classroom(2004,"","",""));
                add(new Classroom(2005,"","",""));
                add(new Classroom(2006,"","",""));
                add(new Classroom(2007,"","",""));
                add(new Classroom(2109,"","",""));
                add(new Classroom(2110,"","",""));
                add(new Classroom(2111,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2113,"","",""));
                add(new Classroom(2202,"","",""));
                add(new Classroom(2203,"","",""));
                add(new Classroom(2204,"","",""));
                add(new Classroom(2205,"","",""));
                add(new Classroom(2207,"","",""));
                add(new Classroom(2209,"","",""));
                add(new Classroom(2212,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_1500_1630_Tuesday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1203,"","",""));
                add(new Classroom(1206,"","",""));
                add(new Classroom(2111,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2113,"","",""));
                add(new Classroom(2213,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_1630_1800_Tuesday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1012,"","",""));
                add(new Classroom(1110,"","",""));
                add(new Classroom(1111,"","",""));
                add(new Classroom(1204,"","",""));
                add(new Classroom(2005,"","",""));
                add(new Classroom(2109,"","",""));
                add(new Classroom(2110,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2113,"","",""));
                add(new Classroom(2211,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_1830_2000_Tuesday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1012,"","",""));
                add(new Classroom(1013,"","",""));
                add(new Classroom(1111,"","",""));
                add(new Classroom(1113,"","",""));
                add(new Classroom(1204,"","",""));
                add(new Classroom(1213,"","",""));
                add(new Classroom(2109,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2113,"","",""));
                add(new Classroom(2205,"","",""));
                add(new Classroom(2211,"","",""));
                add(new Classroom(2212,"","",""));
                add(new Classroom(2213,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_2000_2200_Tuesday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1011,"","",""));
                add(new Classroom(1012,"","",""));
                add(new Classroom(1013,"","",""));
                add(new Classroom(1014,"","",""));
                add(new Classroom(1110,"","",""));
                add(new Classroom(1111,"","",""));
                add(new Classroom(1112,"","",""));
                add(new Classroom(1113,"","",""));
                add(new Classroom(1202,"","",""));
                add(new Classroom(1203,"","",""));
                add(new Classroom(1204,"","",""));
                add(new Classroom(1206,"","",""));
                add(new Classroom(1207,"","",""));
                add(new Classroom(1209,"","",""));
                add(new Classroom(1210,"","",""));
                add(new Classroom(1211,"","",""));
                add(new Classroom(1212,"","",""));
                add(new Classroom(1213,"","",""));
                add(new Classroom(1214,"","",""));
                add(new Classroom(2004,"","",""));
                add(new Classroom(2005,"","",""));
                add(new Classroom(2006,"","",""));
                add(new Classroom(2007,"","",""));
                add(new Classroom(2109,"","",""));
                add(new Classroom(2110,"","",""));
                add(new Classroom(2111,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2113,"","",""));
                add(new Classroom(2202,"","",""));
                add(new Classroom(2203,"","",""));
                add(new Classroom(2204,"","",""));
                add(new Classroom(2206,"","",""));
                add(new Classroom(2207,"","",""));
                add(new Classroom(2210,"","",""));
                add(new Classroom(2211,"","",""));
                add(new Classroom(2212,"","",""));
                add(new Classroom(2213,"","",""));
            }
        };
    }

    private static ArrayList<Classroom> get_0700_0830_Wednesday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1205,"","",""));
                add(new Classroom(1212,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2113,"","",""));
                add(new Classroom(2212,"","",""));
                add(new Classroom(2213,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_0830_1000_Wednesday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1112,"","",""));
                add(new Classroom(2113,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_1030_1200_Wednesday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1212,"","",""));
                add(new Classroom(2109,"","",""));
                add(new Classroom(2110,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_1200_1330_Wednesday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1112,"","",""));
                add(new Classroom(2110,"","",""));
                add(new Classroom(2111,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2207,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_1330_1500_Wednesday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1011,"","",""));
                add(new Classroom(1112,"","",""));
                add(new Classroom(1202,"","",""));
                add(new Classroom(1203,"","",""));
                add(new Classroom(1212,"","",""));
                add(new Classroom(2004,"","",""));
                add(new Classroom(2005,"","",""));
                add(new Classroom(2006,"","",""));
                add(new Classroom(2007,"","",""));
                add(new Classroom(2110,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2113,"","",""));
                add(new Classroom(2202,"","",""));
                add(new Classroom(2203,"","",""));
                add(new Classroom(2204,"","",""));
                add(new Classroom(2205,"","",""));
                add(new Classroom(2207,"","",""));
                add(new Classroom(2213,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_1500_1630_Wednesday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1203,"","",""));
                add(new Classroom(1204,"","",""));
                add(new Classroom(1205,"","",""));
                add(new Classroom(1206,"","",""));
                add(new Classroom(2111,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2113,"","",""));
                add(new Classroom(2213,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_1630_1800_Wednesday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1110,"","",""));
                add(new Classroom(1210,"","",""));
                add(new Classroom(2109,"","",""));
                add(new Classroom(2111,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2113,"","",""));

            }
        };
    }
    private static ArrayList<Classroom> get_1830_2000_Wednesday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1012,"","",""));
                add(new Classroom(1013,"","",""));
                add(new Classroom(1111,"","",""));
                add(new Classroom(1113,"","",""));
                add(new Classroom(1204,"","",""));
                add(new Classroom(1205,"","",""));
                add(new Classroom(1213,"","",""));
                add(new Classroom(2109,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2113,"","",""));
                add(new Classroom(2211,"","",""));
                add(new Classroom(2212,"","",""));
                add(new Classroom(2213,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_2000_2200_Wednesday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1011,"","",""));
                add(new Classroom(1013,"","",""));
                add(new Classroom(1014,"","",""));
                add(new Classroom(1110,"","",""));
                add(new Classroom(1111,"","",""));
                add(new Classroom(1203,"","",""));
                add(new Classroom(1206,"","",""));
                add(new Classroom(1207,"","",""));
                add(new Classroom(1210,"","",""));
                add(new Classroom(2109,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2113,"","",""));
                add(new Classroom(2206,"","",""));
                add(new Classroom(2210,"","",""));
                add(new Classroom(2211,"","",""));
                add(new Classroom(2212,"","",""));
                add(new Classroom(2213,"","",""));
            }
        };
    }

    private static ArrayList<Classroom> get_0700_0830_Thursday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1011,"","",""));
                add(new Classroom(1013,"","",""));
                add(new Classroom(1112,"","",""));
                add(new Classroom(1204,"","",""));
                add(new Classroom(1205,"","",""));
                add(new Classroom(1212,"","",""));
                add(new Classroom(2109,"","",""));
                add(new Classroom(2111,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2212,"","",""));
                add(new Classroom(2213,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_0830_1000_Thursday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1112,"","",""));
                add(new Classroom(2109,"","",""));
                add(new Classroom(2113,"","",""));

            }
        };
    }
    private static ArrayList<Classroom> get_1030_1200_Thursday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1112,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_1200_1330_Thursday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1112,"","",""));
                add(new Classroom(2110,"","",""));
                add(new Classroom(2111,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_1330_1500_Thursday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1202,"","",""));
                add(new Classroom(1203,"","",""));
                add(new Classroom(2004,"","",""));
                add(new Classroom(2005,"","",""));
                add(new Classroom(2006,"","",""));
                add(new Classroom(2007,"","",""));
                add(new Classroom(2109,"","",""));
                add(new Classroom(2110,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2113,"","",""));
                add(new Classroom(2202,"","",""));
                add(new Classroom(2203,"","",""));
                add(new Classroom(2204,"","",""));
                add(new Classroom(2205,"","",""));
                add(new Classroom(2207,"","",""));
                add(new Classroom(2212,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_1500_1630_Thursday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1111,"","",""));
                add(new Classroom(1112,"","",""));
                add(new Classroom(1203,"","",""));
                add(new Classroom(1205,"","",""));
                add(new Classroom(2109,"","",""));
                add(new Classroom(2110,"","",""));
                add(new Classroom(2111,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2113,"","",""));
                add(new Classroom(2211,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_1630_1800_Thursday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1110,"","",""));
                add(new Classroom(1210,"","",""));
                add(new Classroom(2109,"","",""));
                add(new Classroom(2111,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2113,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_1830_2000_Thursday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1012,"","",""));
                add(new Classroom(1110,"","",""));
                add(new Classroom(1111,"","",""));
                add(new Classroom(1204,"","",""));
                add(new Classroom(1205,"","",""));
                add(new Classroom(2005,"","",""));
                add(new Classroom(2109,"","",""));
                add(new Classroom(2110,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2113,"","",""));
                add(new Classroom(2211,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_2000_2200_Thursday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1011,"","",""));
                add(new Classroom(1013,"","",""));
                add(new Classroom(1014,"","",""));
                add(new Classroom(1110,"","",""));
                add(new Classroom(1111,"","",""));
                add(new Classroom(1203,"","",""));
                add(new Classroom(1206,"","",""));
                add(new Classroom(1207,"","",""));
                add(new Classroom(1210,"","",""));
                add(new Classroom(2109,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2113,"","",""));
                add(new Classroom(2206,"","",""));
                add(new Classroom(2210,"","",""));
                add(new Classroom(2211,"","",""));
                add(new Classroom(2212,"","",""));
                add(new Classroom(2213,"","",""));
            }
        };
    }

    private static ArrayList<Classroom> get_0700_0830_Friday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1205,"","",""));
                add(new Classroom(1212,"","",""));
                add(new Classroom(2109,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2113,"","",""));
                add(new Classroom(2212,"","",""));
                add(new Classroom(2213,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_0830_1000_Friday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1011,"","",""));
                add(new Classroom(1013,"","",""));
                add(new Classroom(1112,"","",""));
                add(new Classroom(1204,"","",""));
                add(new Classroom(1205,"","",""));
                add(new Classroom(1212,"","",""));
                add(new Classroom(2111,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2205,"","",""));
                add(new Classroom(2212,"","",""));
                add(new Classroom(2213,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_1030_1200_Friday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1212,"","",""));
                add(new Classroom(2109,"","",""));
                add(new Classroom(2110,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_1200_1330_Friday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1011,"","",""));
                add(new Classroom(1112,"","",""));
                add(new Classroom(1202,"","",""));
                add(new Classroom(1203,"","",""));
                add(new Classroom(1205,"","",""));
                add(new Classroom(1212,"","",""));
                add(new Classroom(2005,"","",""));
                add(new Classroom(2006,"","",""));
                add(new Classroom(2007,"","",""));
                add(new Classroom(2110,"","",""));
                add(new Classroom(2111,"","",""));
                add(new Classroom(2113,"","",""));
                add(new Classroom(2202,"","",""));
                add(new Classroom(2204,"","",""));
                add(new Classroom(2206,"","",""));
                add(new Classroom(2213,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_1330_1500_Friday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1011,"","",""));
                add(new Classroom(1012,"","",""));
                add(new Classroom(1013,"","",""));
                add(new Classroom(1014,"","",""));
                add(new Classroom(1110,"","",""));
                add(new Classroom(1111,"","",""));
                add(new Classroom(1112,"","",""));
                add(new Classroom(1113,"","",""));
                add(new Classroom(1202,"","",""));
                add(new Classroom(1203,"","",""));
                add(new Classroom(1204,"","",""));
                add(new Classroom(1205,"","",""));
                add(new Classroom(1206,"","",""));
                add(new Classroom(1207,"","",""));
                add(new Classroom(1209,"","",""));
                add(new Classroom(1210,"","",""));
                add(new Classroom(1211,"","",""));
                add(new Classroom(1212,"","",""));
                add(new Classroom(1213,"","",""));
                add(new Classroom(1214,"","",""));
                add(new Classroom(2005,"","",""));
                add(new Classroom(2006,"","",""));
                add(new Classroom(2007,"","",""));
                add(new Classroom(2109,"","",""));
                add(new Classroom(2110,"","",""));
                add(new Classroom(2111,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2113,"","",""));
                add(new Classroom(2202,"","",""));
                add(new Classroom(2204,"","",""));
                add(new Classroom(2205,"","",""));
                add(new Classroom(2209,"","",""));
                add(new Classroom(2210,"","",""));
                add(new Classroom(2211,"","",""));
                add(new Classroom(2212,"","",""));
                add(new Classroom(2213,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_1500_1630_Friday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1203,"","",""));
                add(new Classroom(1206,"","",""));
                add(new Classroom(2111,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2113,"","",""));
                add(new Classroom(2213,"","",""));

            }
        };
    }
    private static ArrayList<Classroom> get_1630_1800_Friday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1111,"","",""));
                add(new Classroom(1112,"","",""));
                add(new Classroom(1203,"","",""));
                add(new Classroom(1205,"","",""));
                add(new Classroom(2109,"","",""));
                add(new Classroom(2110,"","",""));
                add(new Classroom(2111,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2113,"","",""));
                add(new Classroom(2211,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_1830_2000_Friday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1012,"","",""));
                add(new Classroom(1013,"","",""));
                add(new Classroom(1111,"","",""));
                add(new Classroom(1113,"","",""));
                add(new Classroom(1204,"","",""));
                add(new Classroom(2109,"","",""));
                add(new Classroom(1213,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2113,"","",""));
                add(new Classroom(2211,"","",""));
                add(new Classroom(2212,"","",""));
                add(new Classroom(2213,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_2000_2200_Friday(){
        return new ArrayList<Classroom>(){
            {
                add(new Classroom(1011,"","",""));
                add(new Classroom(1012,"","",""));
                add(new Classroom(1013,"","",""));
                add(new Classroom(1014,"","",""));
                add(new Classroom(1110,"","",""));
                add(new Classroom(1111,"","",""));
                add(new Classroom(1112,"","",""));
                add(new Classroom(1113,"","",""));
                add(new Classroom(1202,"","",""));
                add(new Classroom(1203,"","",""));
                add(new Classroom(1204,"","",""));
                add(new Classroom(1205,"","",""));
                add(new Classroom(1206,"","",""));
                add(new Classroom(1207,"","",""));
                add(new Classroom(1209,"","",""));
                add(new Classroom(1210,"","",""));
                add(new Classroom(1211,"","",""));
                add(new Classroom(1212,"","",""));
                add(new Classroom(1213,"","",""));
                add(new Classroom(1214,"","",""));
                add(new Classroom(2004,"","",""));
                add(new Classroom(2005,"","",""));
                add(new Classroom(2006,"","",""));
                add(new Classroom(2007,"","",""));
                add(new Classroom(2109,"","",""));
                add(new Classroom(2110,"","",""));
                add(new Classroom(2111,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2113,"","",""));
                add(new Classroom(2202,"","",""));
                add(new Classroom(2203,"","",""));
                add(new Classroom(2205,"","",""));
                add(new Classroom(2206,"","",""));
                add(new Classroom(2207,"","",""));
                add(new Classroom(2209,"","",""));
                add(new Classroom(2210,"","",""));
                add(new Classroom(2211,"","",""));
                add(new Classroom(2212,"","",""));
                add(new Classroom(2213,"","",""));
            }
        };
    }
    private static ArrayList<Classroom> get_All_Classrooms(){
        return new ArrayList<Classroom>(){
            {

                add(new Classroom(1011,"","",""));
                add(new Classroom(1012,"","",""));
                add(new Classroom(1013,"","",""));
                add(new Classroom(1014,"","",""));

                add(new Classroom(1110,"","",""));
                add(new Classroom(1111,"","",""));
                add(new Classroom(1112,"","",""));
                add(new Classroom(1113,"","",""));

                add(new Classroom(1202,"","",""));
                add(new Classroom(1203,"","",""));
                add(new Classroom(1204,"","",""));
                add(new Classroom(1205,"","",""));
                add(new Classroom(1206,"","",""));
                add(new Classroom(1207,"","",""));
                add(new Classroom(1209,"","",""));
                add(new Classroom(1210,"","",""));
                add(new Classroom(1211,"","",""));
                add(new Classroom(1212,"","",""));
                add(new Classroom(1213,"","",""));
                add(new Classroom(1214,"","",""));

                add(new Classroom(2004,"","",""));
                add(new Classroom(2005,"","",""));
                add(new Classroom(2006,"","",""));
                add(new Classroom(2007,"","",""));

                add(new Classroom(2109,"","",""));
                add(new Classroom(2110,"","",""));
                add(new Classroom(2111,"","",""));
                add(new Classroom(2112,"","",""));
                add(new Classroom(2113,"","",""));

                add(new Classroom(2202,"","",""));
                add(new Classroom(2203,"","",""));
                add(new Classroom(2204,"","",""));
                add(new Classroom(2205,"","",""));
                add(new Classroom(2206,"","",""));
                add(new Classroom(2207,"","",""));
                add(new Classroom(2209,"","",""));
                add(new Classroom(2210,"","",""));
                add(new Classroom(2211,"","",""));
                add(new Classroom(2212,"","",""));
                add(new Classroom(2213,"","",""));
            }
        };
    }
}
