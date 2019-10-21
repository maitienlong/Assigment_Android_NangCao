package com.example.assigment_android_nangcao.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseAssigment extends SQLiteOpenHelper {
    public DatabaseAssigment(Context context) {
        super(context, "SQLiteVer3.db", null, 1);
    }

    public final static String T_KHOAHOC = "khoahocTable";
    public final static String T_GMAP = "gmapTable";

    public final static String C_MAKHOAHOC = "makhoahoc";
    public final static String C_TENKHOAHOC = "tenkhoahoc";
    public final static String C_GIAKHOAHOC = "giakhoahoc";
    public final static String C_GIANGVIENKHOAHOC = "giangvienkhoahoc";
    public final static String C_NOIDUNGKHOAHOC = "noidungkhoahoc";
    public final static String C_GHICHUKHOAHOC = "ghichukhoahoc";

    public final static String C_IDLOCATION = "idlocation";
    public final static String C_NAMELOCATION = "namelocation";
    public final static String C_LAT = "lat";
    public final static String C_LNG = "lng";

    public final static String CREATE_KHOAHOC = "CREATE TABLE khoahocTable (makhoahoc INTEGER PRIMARY KEY, tenkhoahoc NVARCHAR, giakhoahoc NVARCHAR, giangvienkhoahoc NVARCHAR, noidungkhoahoc NVARCHAR, ghichukhoahoc NVARCHAR)";
    public final static String CREATE_GMAP = "CREATE TABLE gmapTable (idlocation INTEGER PRIMARY KEY AUTOINCREMENT, namelocation NVARCHAR, lat DOUBLE, lng DOUBLE)";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_KHOAHOC);
        db.execSQL(CREATE_GMAP);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
