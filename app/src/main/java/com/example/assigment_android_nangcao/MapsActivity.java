package com.example.assigment_android_nangcao;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.FragmentActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assigment_android_nangcao.dao.GMapDAO;
import com.example.assigment_android_nangcao.model.GMap;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public EditText edtLat, edtLng, edtNameLoaction;
    private GMapDAO gMapDAO;
    EditText inputLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        edtLat = findViewById(R.id.edtLat);
        edtLng = findViewById(R.id.edtLng);
        edtNameLoaction = findViewById(R.id.edtNameLoaction);
        inputLocation = findViewById(R.id.inputLocation);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        gMapDAO = new GMapDAO(this);
        final List<GMap> gMapList = gMapDAO.getAll();

        for (int i = 0; i < gMapList.size(); i++) {
            LatLng markls = new LatLng(gMapList.get(i).lat, gMapList.get(i).lng);
            mMap.addMarker(new MarkerOptions().position(markls).title(gMapList.get(i).nameLocation));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(markls));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(5.0f));
        }

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                final Dialog dialog = new Dialog(MapsActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_location);

                Window window = dialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();
                wlp.gravity = Gravity.CENTER;
                wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
                window.setAttributes(wlp);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();

                //-------------------------------------

                TextView tvNameLocation = dialog.findViewById(R.id.tvNameLocation);
                TextView tvLat = dialog.findViewById(R.id.tvLat);
                TextView tvLng = dialog.findViewById(R.id.tvLng);

                tvNameLocation.setText(marker.getTitle());
                tvLat.setText(marker.getPosition() + "");
                tvLng.setText(marker.getRotation() + "");

                return false;

                //------------------------


            }
        });

    }

    public void btnGetLoaction(View view) {

        LatLng marka = new LatLng(Double.parseDouble(edtLat.getText().toString().trim()), Double.parseDouble(edtLng.getText().toString().trim()));
        mMap.addMarker(new MarkerOptions().position(marka).title(edtNameLoaction.getText().toString()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(marka));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15.6f));

        try {

            List<GMap> gMapList = new ArrayList<>();
            GMap gMap = new GMap();

            gMap.nameLocation = edtNameLoaction.getText().toString().trim();
            gMap.lat = Double.parseDouble(edtLat.getText().toString().trim());
            gMap.lng = Double.parseDouble(edtLng.getText().toString().trim());
            gMapList.add(gMap);

            long result = gMapDAO.insertMark(gMap);
            Log.e("resultBook", result + "");
            if (result > 0) {
                Toast.makeText(MapsActivity.this, "Bạn đã thêm thành công", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(MapsActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(MapsActivity.this, "Lỗi thêm mark", Toast.LENGTH_SHORT).show();
        }


        gMapDAO = new GMapDAO(this);
        List<GMap> gMapList = gMapDAO.getAll();

        for (int i = 0; i < gMapList.size(); i++) {
            LatLng markls = new LatLng(gMapList.get(i).lat, gMapList.get(i).lng);
            mMap.addMarker(new MarkerOptions().position(markls).title(gMapList.get(i).nameLocation));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(markls));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(5.0f));
        }

    }

    public void btnSearch(View view) {
        List<GMap> find = gMapDAO.findByName("'"+inputLocation.getText().toString().trim()+"'");

        for (int i = 0; i < find.size(); i++) {
            LatLng markls = new LatLng(find.get(i).lat, find.get(i).lng);
            mMap.addMarker(new MarkerOptions().position(markls).title(find.get(i).nameLocation));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(markls));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(21.0f));
        }

    }
}
