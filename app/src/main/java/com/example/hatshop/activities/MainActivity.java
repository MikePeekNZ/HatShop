package com.example.hatshop.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.hatshop.R;
import com.example.hatshop.adapters.RecyclerViewAdapter;
import com.example.hatshop.model.Hat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Hat> hats;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        hats = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerviewid);
        jsonrequest();
    }

    private void jsonrequest() {

        String JSON_URL = "http://wemakeapps.net/hats/products.json";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, JSON_URL, null,
                new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                JSONArray jsonArray = null;
                try {
                    jsonArray = response.getJSONArray("products");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JSONObject jsonObject;

                assert jsonArray != null;
                for (int i = 0; i < jsonArray.length(); i++) {

                    try {
                        jsonObject = jsonArray.getJSONObject(i);
                        Hat hat = new Hat();
                        hat.setTitle(jsonObject.getString("title"));
                        hat.setImageURL(jsonObject.getString("imageUrl"));
                        hat.setPrice(jsonObject.getInt("price"));
                        hat.setDescription(jsonObject.getString("description"));
                        hat.setSeller(jsonObject.getJSONObject("seller"));
                        hat.setSizes(jsonObject.getJSONArray("sizes"));
                        hats.add(hat);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                setuprecyclerview(hats);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("JSONerror");
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);
    }

    private void setuprecyclerview(List<Hat> hats) {

        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this, hats);
        recyclerView.setLayoutManager(new LinearLayoutManager((this)));

        recyclerView.setAdapter(myadapter);
    }
}
