package com.dwiromadon.myapplication.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dwiromadon.myapplication.R;
import com.dwiromadon.myapplication.pengguna.HomePengguna;
import com.dwiromadon.myapplication.users.LoginActivity;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.lang.reflect.Method;
import java.util.List;

public class HomeAdmin extends AppCompatActivity {

    CardView cardInputData, cardDataPetshop;

    Intent i;
    String idUser, namaPetshop, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        getSupportActionBar().hide();

        if (Build.VERSION.SDK_INT >= 24) {
            try {
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        cardInputData = (CardView) findViewById(R.id.cardInputData);
        cardDataPetshop = (CardView) findViewById(R.id.cardDatapetshop);
        i = getIntent();
        idUser = i.getStringExtra("_id");
        namaPetshop = i.getStringExtra("namaPetshop");
        username = i.getStringExtra("username");

        Log.d("ID User = ", idUser);

        cardInputData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdmin.this, InputData.class);
                i.putExtra("namaPetshop", namaPetshop);
                i.putExtra("idUser", idUser);
                startActivity(i);
                finish();
            }
        });

        cardDataPetshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdmin.this, DataPetshop.class);
                i.putExtra("namaPetshop", namaPetshop);
                i.putExtra("idUser", idUser);
                startActivity(i);
                finish();
            }
        });

        addPermission();

    }

    public void addPermission() {
        Dexter.withActivity(HomeAdmin.this)
                .withPermissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            //Toast.makeText(getActivity(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings

                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(HomeAdmin.this, "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(HomeAdmin.this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}
