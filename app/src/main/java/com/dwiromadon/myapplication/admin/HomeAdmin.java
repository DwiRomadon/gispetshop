package com.dwiromadon.myapplication.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dwiromadon.myapplication.R;
import com.dwiromadon.myapplication.pengguna.HomePengguna;
import com.dwiromadon.myapplication.users.LoginActivity;

public class HomeAdmin extends AppCompatActivity {

    CardView cardInputData, cardDataPetshop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        getSupportActionBar().hide();

        cardInputData = (CardView) findViewById(R.id.cardInputData);
        cardDataPetshop = (CardView) findViewById(R.id.cardDatapetshop);
        cardInputData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdmin.this, InputData.class);
                startActivity(i);
                finish();
            }
        });

        cardDataPetshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdmin.this, DataPetshop.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(HomeAdmin.this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}
