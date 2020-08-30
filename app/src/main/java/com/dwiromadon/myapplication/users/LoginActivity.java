package com.dwiromadon.myapplication.users;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.dwiromadon.myapplication.R;
import com.dwiromadon.myapplication.pengguna.HomePengguna;

public class LoginActivity extends AppCompatActivity {

    LinearLayout skipLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        skipLogin = (LinearLayout) findViewById(R.id.skipLogin);
        skipLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, HomePengguna.class);
                startActivity(i);
                finish();
            }
        });
    }
}
