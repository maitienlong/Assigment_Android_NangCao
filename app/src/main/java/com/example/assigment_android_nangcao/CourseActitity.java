package com.example.assigment_android_nangcao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assigment_android_nangcao.adapter.AdapterGioHangRV;
import com.example.assigment_android_nangcao.adapter.AdapterKhoaHocRV;
import com.example.assigment_android_nangcao.dao.KhoaHocDAO;
import com.example.assigment_android_nangcao.model.GioHang;
import com.example.assigment_android_nangcao.model.KhoaHoc;

import java.util.ArrayList;
import java.util.List;

public class CourseActitity extends AppCompatActivity {
    private RecyclerView rvKhoaHoc;
    private LinearLayoutManager linearLayoutManager;
    private KhoaHocDAO khoaHocDAO;
    private AdapterKhoaHocRV adapterKhoaHocRV;
    private List<GioHang> gioHangList;
    private TextView tvGioHangCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        rvKhoaHoc = findViewById(R.id.rvKhoaHoc);
        tvGioHangCount = findViewById(R.id.tvGioHangCount);
        gioHangList = new ArrayList<>();
        tvGioHangCount.setText(gioHangList.size() + "");

        khoaHocDAO = new KhoaHocDAO(this);

        final List<KhoaHoc> khoaHocList = khoaHocDAO.getAll();

        linearLayoutManager = new LinearLayoutManager(this);

        adapterKhoaHocRV = new AdapterKhoaHocRV(this, khoaHocList);

        rvKhoaHoc.setAdapter(adapterKhoaHocRV);

        rvKhoaHoc.setLayoutManager(linearLayoutManager);

        AdapterKhoaHocRV.ItemClickSupport.addTo(rvKhoaHoc).setOnItemClickListener(new AdapterKhoaHocRV.ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                GioHang gioHang = new GioHang();
                gioHang.maKhoaHoc = khoaHocList.get(position).maKhoaHoc;
                gioHang.tenKhoaHoc = khoaHocList.get(position).tenKhoaHoc;
                gioHang.giaKhoaHoc = khoaHocList.get(position).giaKhoaHoc;
                gioHang.giangVienKhoaHoc = khoaHocList.get(position).giangVienKhoaHoc;
                gioHang.noiDungKhoaHoc = khoaHocList.get(position).noiDungKhoaHoc;
                gioHang.ghiChuKhoaHoc = khoaHocList.get(position).ghiChuKhoaHoc;

                for (int i = 0; i < gioHangList.size(); i++) {
                    if (khoaHocList.get(position).maKhoaHoc == gioHangList.get(i).maKhoaHoc) {
                        Toast.makeText(CourseActitity.this, "Khóa học này đã có trong giỏ hàng", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                gioHangList.add(gioHang);

                if (gioHangList.size() > 0) {
                    Toast.makeText(CourseActitity.this, "Bạn đã thêm thành công 1 khóa học", Toast.LENGTH_SHORT).show();
                }
                tvGioHangCount.setText(gioHangList.size() + "");
            }
        });
    }


    public void btnThemKhoaHoc(View view) {

        Intent intent = new Intent(this, ThemKhoaHocActivity.class);
        startActivity(intent);
    }

    public void btnGioHang(View view) {
// ----------------------------------- MY DIALOG ----------------------------------------------------------------
        final Dialog dialog = new Dialog(CourseActitity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_giohang);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
// --------------------------------------------------------------------------------------------------------------
        LinearLayoutManager linearLayoutManager;
        AdapterGioHangRV adapterGioHangRV;
        RecyclerView rvGioHang = dialog.findViewById(R.id.rvGioHang);

        linearLayoutManager = new LinearLayoutManager(this);

        adapterGioHangRV = new AdapterGioHangRV(this, gioHangList);

        rvGioHang.setAdapter(adapterGioHangRV);

        rvGioHang.setLayoutManager(linearLayoutManager);

        AdapterGioHangRV.ItemClickSupport.addTo(rvGioHang).setOnItemClickListener(new AdapterGioHangRV.ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

            }
        });


        AdapterGioHangRV.ItemClickSupport.addTo(rvGioHang).setOnItemLongClickListener(new AdapterGioHangRV.ItemClickSupport.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
                gioHangList.remove(position);
                LinearLayoutManager linearLayoutManager;
                AdapterGioHangRV adapterGioHangRV;
                RecyclerView rvGioHang = dialog.findViewById(R.id.rvGioHang);

                linearLayoutManager = new LinearLayoutManager(CourseActitity.this);

                adapterGioHangRV = new AdapterGioHangRV(CourseActitity.this, gioHangList);

                rvGioHang.setAdapter(adapterGioHangRV);

                rvGioHang.setLayoutManager(linearLayoutManager);

                tvGioHangCount.setText(gioHangList.size() + "");
                Toast.makeText(CourseActitity.this, "Bạn đã xõa 1 khóa học", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
