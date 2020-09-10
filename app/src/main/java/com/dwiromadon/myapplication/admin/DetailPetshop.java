package com.dwiromadon.myapplication.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dwiromadon.myapplication.R;
import com.dwiromadon.myapplication.server.BaseURL;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;
import com.synnapps.carouselview.ViewListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DetailPetshop extends AppCompatActivity {

    Intent i;
    String _id, namaPetshop, alamat, noTelp, gambar, jamBuka, produk, jasa, lat, lon;
    ArrayList gam = new ArrayList();
    ArrayList listJamBuka = new ArrayList();
    ArrayList listProduk = new ArrayList();
    ArrayList listJasa = new ArrayList();
    CarouselView carouselView;

    EditText edtPetshop, edtAlamat, edtNoTelp;
    Spinner spnJamBuka, spnProduk, spnJasa;
    FloatingActionButton fabAddGambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_petshop);

        getSupportActionBar().setTitle("Detail Petshop");

        i = getIntent();
        _id = i.getStringExtra("_id");
        namaPetshop = i.getStringExtra("namaPetshop");
        alamat = i.getStringExtra("alamat");
        noTelp = i.getStringExtra("noTelp");
        gambar = i.getStringExtra("gambar");
        jamBuka = i.getStringExtra("jambuka");
        produk = i.getStringExtra("produk");
        jasa = i.getStringExtra("jasa");
        lat = i.getStringExtra("lat");
        lon = i.getStringExtra("lon");

        carouselView = (CarouselView) findViewById(R.id.carouselView);
        edtPetshop = (EditText) findViewById(R.id.edtPetshop);
        edtAlamat = (EditText) findViewById(R.id.edtAlamat);
        edtNoTelp = (EditText) findViewById(R.id.edtNotelp);

        spnJamBuka = (Spinner) findViewById(R.id.jamBuka);
        spnProduk = (Spinner) findViewById(R.id.produk);
        spnJasa = (Spinner) findViewById(R.id.jasa);

        fabAddGambar = (FloatingActionButton) findViewById(R.id.fabAddGambar);

        edtPetshop.setText(namaPetshop);
        edtAlamat.setText(alamat);
        edtNoTelp.setText(noTelp);

        try {
            JSONArray arrayGambar = new JSONArray(gambar);
            JSONArray arrayJamBuka = new JSONArray(jamBuka);
            JSONArray arrayProduk = new JSONArray(produk);
            JSONArray arrayJasa = new JSONArray(jasa);
            for (int i = 0; i < arrayGambar.length(); i++){
                gam.add(BaseURL.baseUrl + "gambar/" + arrayGambar.get(i).toString());
            }

            listJamBuka.add("--Jam Buka--");
            for (int i = 0; i < arrayJamBuka.length(); i++){
                JSONObject objJamBuka = arrayJamBuka.getJSONObject(i);
                listJamBuka.add(objJamBuka.getString("hari") + " / " + objJamBuka.getString("jam"));
            }

            listProduk.add("--Produk--");
            for (int i = 0; i < arrayProduk.length(); i++){
                JSONObject objProduk = arrayProduk.getJSONObject(i);
                listProduk.add(objProduk.getString("namaProduk") + " / Rp." + objProduk.getString("hargaProduk"));
            }

            listJasa.add("--Jasa--");
            for (int i = 0; i < arrayJasa.length(); i++){
                JSONObject objJasa = arrayJasa.getJSONObject(i);
                listJasa.add(objJasa.getString("namaJasa") + " / Rp." + objJasa.getString("hargaJasa"));
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listJamBuka);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnJamBuka.setAdapter(adapter);

            ArrayAdapter<String> adapterProduk = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listProduk);
            adapterProduk.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnProduk.setAdapter(adapterProduk);

            ArrayAdapter<String> adapterJasa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listJasa);
            adapterJasa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnJasa.setAdapter(adapterJasa);

            carouselView.setPageCount(gam.size());
            carouselView.setImageListener(imageListener);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        fabAddGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailPetshop.this, TambahGambar.class);
                i.putExtra("_id", _id);
                startActivity(i);
                finish();
            }
        });

    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Picasso.get().load(String.valueOf(gam.get(position))).fit().centerCrop().into(imageView);
            //imageView.setImageResource(sampleImages[position]);
        }
    };

    View.OnClickListener pauseOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            carouselView.pauseCarousel();
        }
    };
}
