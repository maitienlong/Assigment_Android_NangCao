package com.example.assigment_android_nangcao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assigment_android_nangcao.dao.KhoaHocDAO;
import com.example.assigment_android_nangcao.model.KhoaHoc;

import java.util.ArrayList;
import java.util.List;

public class ThemKhoaHocActivity extends AppCompatActivity {
    private EditText edtMaKhoaHoc, edtTenKhoaHoc, edtGiaKhoaHoc, edtGiangVien, edtNoiDungKhoaHoc, edtGhiChuKhoaHoc;
    private KhoaHocDAO khoaHocDAO;
    private List<KhoaHoc> khoaHocList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_khoa_hoc);
        edtMaKhoaHoc = findViewById(R.id.edtMaKhoaHoc);
        edtTenKhoaHoc = findViewById(R.id.edtTenKhoaHoc);
        edtGiaKhoaHoc = findViewById(R.id.edtGiaKhoaHoc);
        edtGiangVien = findViewById(R.id.edtGiangVien);
        edtNoiDungKhoaHoc = findViewById(R.id.edtNoiDungKhoaHoc);
        edtGhiChuKhoaHoc = findViewById(R.id.edtGhiChuKhoaHoc);
        khoaHocDAO = new KhoaHocDAO(this);
    }

    public void btnThemTheLoai(View view) {
        try {


            List<KhoaHoc> khoaHocList = new ArrayList<>();
            KhoaHoc khoaHoc = new KhoaHoc();
            khoaHoc.maKhoaHoc = Integer.parseInt(edtMaKhoaHoc.getText().toString().trim());
            khoaHoc.tenKhoaHoc = edtTenKhoaHoc.getText().toString().trim();
            khoaHoc.giaKhoaHoc = edtGiaKhoaHoc.getText().toString().trim();
            khoaHoc.giangVienKhoaHoc = edtGiangVien.getText().toString().trim();
            khoaHoc.noiDungKhoaHoc = edtNoiDungKhoaHoc.getText().toString().trim();
            khoaHoc.ghiChuKhoaHoc = edtGhiChuKhoaHoc.getText().toString().trim();
            khoaHocList.add(khoaHoc);

            long result = khoaHocDAO.insertKhoaHoc(khoaHoc);
            if (result > 0) {
                Toast.makeText(this, "Bạn đã thêm thành công", Toast.LENGTH_SHORT).show();


            } else {
                Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Lỗi thêm Khoa Hoc", Toast.LENGTH_SHORT).show();
        }


    }
}
