package com.example.assigment_android_nangcao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assigment_android_nangcao.database.DatabaseAssigment;
import com.example.assigment_android_nangcao.model.KhoaHoc;

import java.util.ArrayList;
import java.util.List;

import static com.example.assigment_android_nangcao.database.DatabaseAssigment.C_GHICHUKHOAHOC;
import static com.example.assigment_android_nangcao.database.DatabaseAssigment.C_GIAKHOAHOC;
import static com.example.assigment_android_nangcao.database.DatabaseAssigment.C_GIANGVIENKHOAHOC;
import static com.example.assigment_android_nangcao.database.DatabaseAssigment.C_MAKHOAHOC;
import static com.example.assigment_android_nangcao.database.DatabaseAssigment.C_NOIDUNGKHOAHOC;
import static com.example.assigment_android_nangcao.database.DatabaseAssigment.C_TENKHOAHOC;
import static com.example.assigment_android_nangcao.database.DatabaseAssigment.T_KHOAHOC;

public class KhoaHocDAO {

    private DatabaseAssigment databaseAssigment;

    public KhoaHocDAO(Context context) {
        databaseAssigment = new DatabaseAssigment(context);
    }

//------------------------------------------------ THÊM NGƯỜI DÙNG -------------------------------------------------------

    public long insertKhoaHoc(KhoaHoc khoaHoc) {
        SQLiteDatabase sqLiteDatabase = databaseAssigment.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(C_MAKHOAHOC, khoaHoc.maKhoaHoc);
        contentValues.put(C_TENKHOAHOC, khoaHoc.tenKhoaHoc);
        contentValues.put(C_GIAKHOAHOC, khoaHoc.giaKhoaHoc);
        contentValues.put(C_GIANGVIENKHOAHOC, khoaHoc.giangVienKhoaHoc);
        contentValues.put(C_NOIDUNGKHOAHOC, khoaHoc.noiDungKhoaHoc);
        contentValues.put(C_GHICHUKHOAHOC, khoaHoc.ghiChuKhoaHoc);


        long result = sqLiteDatabase.insert(T_KHOAHOC, null, contentValues);

        sqLiteDatabase.close();

        return result;
    }


    //------------------------------------------------ LẤY DANH SÁCH NGƯỜI DÙNG -------------------------------------------------------

    public List<KhoaHoc> getAll() {

        List<KhoaHoc> khoaHocList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = databaseAssigment.getReadableDatabase();

        String SELECT = "SELECT * FROM " + T_KHOAHOC;

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);
        if (cursor.moveToFirst()) {
            do {
                KhoaHoc khoaHoc = new KhoaHoc();
                khoaHoc.maKhoaHoc = Integer.parseInt(cursor.getString(0));
                khoaHoc.tenKhoaHoc = cursor.getString(1);
                khoaHoc.giaKhoaHoc = cursor.getString(2);
                khoaHoc.giangVienKhoaHoc = cursor.getString(3);
                khoaHoc.noiDungKhoaHoc = cursor.getString(4);
                khoaHoc.ghiChuKhoaHoc = cursor.getString(4);

                khoaHocList.add(khoaHoc);
            } while (cursor.moveToNext());
        }

        sqLiteDatabase.close();

        return khoaHocList;
    }


}
