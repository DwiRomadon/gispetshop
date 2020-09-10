package com.dwiromadon.myapplication.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dwiromadon.myapplication.R;
import com.dwiromadon.myapplication.adapter.AdapterPetShop;
import com.dwiromadon.myapplication.model.ModelPetshop;
import com.dwiromadon.myapplication.server.BaseURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataPetshop extends AppCompatActivity {

    ProgressDialog pDialog;

    AdapterPetShop adapter;
    ListView list;

    ArrayList<ModelPetshop> newsList = new ArrayList<ModelPetshop>();
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_petshop);

        getSupportActionBar().setTitle("Data Petshop");
        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        list = (ListView) findViewById(R.id.array_list);
        newsList.clear();
        adapter = new AdapterPetShop(DataPetshop.this, newsList);
        list.setAdapter(adapter);
        getAllPet();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(DataPetshop.this, HomeAdmin.class);
        startActivity(i);
        finish();
    }

    private void getAllPet() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, BaseURL.getDataPetShop, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    final ModelPetshop petShop = new ModelPetshop();
                                    final String _id = jsonObject.getString("_id");
                                    final String namaPetshop = jsonObject.getString("namaPetshop");
                                    final String alamat = jsonObject.getString("alamat");
                                    final String notelp = jsonObject.getString("noTelp");
                                    final String arrGambar = jsonObject.getString("gambar");
                                    final String arrJamBuka = jsonObject.getString("jamBuka");
                                    final String arrProduk = jsonObject.getString("produk");
                                    final String arrJasa = jsonObject.getString("jasa");
                                    final String lat = jsonObject.getString("lat");
                                    final String lon = jsonObject.getString("lon");
                                    JSONArray arrayGambar = new JSONArray(arrGambar);
                                    String gambar = arrayGambar.get(0).toString();
                                    petShop.setNamaPetshop(namaPetshop);
                                    petShop.setAlamat(alamat);
                                    petShop.setNotelp(notelp);
                                    petShop.setGambar(gambar);
                                    petShop.setArrGambar(arrGambar);
                                    petShop.setJamBuka(arrJamBuka);
                                    petShop.setProduk(arrProduk);
                                    petShop.setJasa(arrJasa);
                                    petShop.set_id(_id);
                                    petShop.setLat(lat);
                                    petShop.setLon(lon);

                                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            // TODO Auto-generated method stub
                                            Intent a = new Intent(DataPetshop.this, DetailPetshop.class);
                                            a.putExtra("namaPetshop", newsList.get(position).getNamaPetshop());
                                            a.putExtra("_id", newsList.get(position).get_id());
                                            a.putExtra("alamat", newsList.get(position).getAlamat());
                                            a.putExtra("noTelp", newsList.get(position).getNotelp());
                                            a.putExtra("gambar", newsList.get(position).getArrGambar());
                                            a.putExtra("jambuka", newsList.get(position).getJamBuka());
                                            a.putExtra("produk", newsList.get(position).getProduk());
                                            a.putExtra("jasa", newsList.get(position).getJasa());
                                            a.putExtra("lat", newsList.get(position).getLat());
                                            a.putExtra("lon", newsList.get(position).getLon());
                                            startActivity(a);
                                        }
                                    });
                                    newsList.add(petShop);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });

        /* Add your Requests to the RequestQueue to execute */
        mRequestQueue.add(req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
