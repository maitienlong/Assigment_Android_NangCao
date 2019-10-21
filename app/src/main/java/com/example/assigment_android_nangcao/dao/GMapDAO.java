package com.example.assigment_android_nangcao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.assigment_android_nangcao.database.DatabaseAssigment;
import com.example.assigment_android_nangcao.model.GMap;
import com.example.assigment_android_nangcao.model.KhoaHoc;

import java.util.ArrayList;
import java.util.List;

import static com.example.assigment_android_nangcao.database.DatabaseAssigment.C_GHICHUKHOAHOC;
import static com.example.assigment_android_nangcao.database.DatabaseAssigment.C_GIAKHOAHOC;
import static com.example.assigment_android_nangcao.database.DatabaseAssigment.C_GIANGVIENKHOAHOC;
import static com.example.assigment_android_nangcao.database.DatabaseAssigment.C_IDLOCATION;
import static com.example.assigment_android_nangcao.database.DatabaseAssigment.C_LAT;
import static com.example.assigment_android_nangcao.database.DatabaseAssigment.C_LNG;
import static com.example.assigment_android_nangcao.database.DatabaseAssigment.C_MAKHOAHOC;
import static com.example.assigment_android_nangcao.database.DatabaseAssigment.C_NAMELOCATION;
import static com.example.assigment_android_nangcao.database.DatabaseAssigment.C_NOIDUNGKHOAHOC;
import static com.example.assigment_android_nangcao.database.DatabaseAssigment.C_TENKHOAHOC;
import static com.example.assigment_android_nangcao.database.DatabaseAssigment.T_GMAP;
import static com.example.assigment_android_nangcao.database.DatabaseAssigment.T_KHOAHOC;

public class GMapDAO {

    private DatabaseAssigment databaseAssigment;

    public GMapDAO(Context context) {
        databaseAssigment = new DatabaseAssigment(context);
    }

    //------------------------------------------------ THÊM MARK -------------------------------------------------------

    public long insertMark(GMap gMap) {
        SQLiteDatabase sqLiteDatabase = databaseAssigment.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(C_NAMELOCATION, gMap.nameLocation);
        contentValues.put(C_LAT, gMap.lat);
        contentValues.put(C_LNG, gMap.lng);

        long result = sqLiteDatabase.insert(T_GMAP, null, contentValues);

        sqLiteDatabase.close();

        return result;
    }

    public List<GMap> getAll() {

        List<GMap> gMapList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = databaseAssigment.getReadableDatabase();

        String SELECT = "SELECT * FROM " + T_GMAP;

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);
        if (cursor.moveToFirst()) {
            do {
                GMap gMap = new GMap();
                gMap.idLocation = Integer.parseInt(cursor.getString(0));
                gMap.nameLocation = cursor.getString(1);
                gMap.lat = Double.parseDouble(cursor.getString(2));
                gMap.lng = Double.parseDouble(cursor.getString(3));

                gMapList.add(gMap);

            } while (cursor.moveToNext());
        }

        sqLiteDatabase.close();

        return gMapList;
    }

    public List<GMap> findByName(String nameLocation) {
        List<GMap> gMapList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = databaseAssigment.getReadableDatabase();

        String SELECT = "SELECT * FROM " + T_GMAP + " WHERE " + C_NAMELOCATION + " = " + nameLocation;

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);
        if (cursor.moveToFirst()) {
            do {
                GMap gMap = new GMap();
                gMap.idLocation = Integer.parseInt(cursor.getString(0));
                gMap.nameLocation = cursor.getString(1);
                gMap.lat = Double.parseDouble(cursor.getString(2));
                gMap.lng = Double.parseDouble(cursor.getString(3));

                gMapList.add(gMap);
                Log.d("GETBYNAME",gMapList.toString());
            } while (cursor.moveToNext());
        }

        sqLiteDatabase.close();

        return gMapList;
    }
}
