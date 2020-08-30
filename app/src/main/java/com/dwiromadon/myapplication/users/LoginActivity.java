package com.dwiromadon.myapplication.users;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dwiromadon.myapplication.R;
import com.dwiromadon.myapplication.admin.HomeAdmin;
import com.dwiromadon.myapplication.pengguna.HomePengguna;

public class LoginActivity extends AppCompatActivity {

    LinearLayout skipLogin;
    Button btnLogin;
    EditText edtUserName, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        skipLogin = (LinearLayout) findViewById(R.id.skipLogin);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        edtUserName = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        skipLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, HomePengguna.class);
                startActivity(i);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edtUserName.getText().toString();
                String password = edtPassword.getText().toString();
                if (userName.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Username Tidak Boleh Kosong",
                            Toast.LENGTH_LONG).show();
                }else if (password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Password Tidak Boleh Kosong",
                            Toast.LENGTH_LONG).show();
                }else {
                    if (userName.equals("admin")){
                        if (password.equals("123")){
                            Intent i = new Intent(LoginActivity.this, HomeAdmin.class);
                            startActivity(i);
                            finish();
                        }else {
                            Toast.makeText(getApplicationContext(), "Password Salah",
                                    Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Username Tidak Terdaftar",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
