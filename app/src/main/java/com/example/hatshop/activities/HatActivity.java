package com.example.hatshop.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hatshop.R;

import java.util.ArrayList;

public class HatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hat);

        // get data from intent Extras
        String title = getIntent().getExtras().getString("title");
        String price = getIntent().getExtras().getString("price");
        ArrayList<String> sizes = getIntent().getStringArrayListExtra("sizes");
        String image_url = getIntent().getExtras().getString("image");
        String description = getIntent().getExtras().getString("description");
        String sellerName = getIntent().getExtras().getString("seller");

        // create views
        ImageView hat_image = findViewById(R.id.ha_imgURL);
        TextView tv_title = findViewById(R.id.ha_title);
        TextView tv_price = findViewById(R.id.ha_price);
        TextView tv_description = findViewById(R.id.ha_description);
        TextView tv_sellerName = findViewById(R.id.ha_seller);

        // pass values to each view
        tv_title.setText(title);
        tv_price.setText(price);
        tv_description.setText(description);
        tv_sellerName.setText(sellerName);

        // set image using Glide
        Glide.with(this).load(image_url).into(hat_image);

        // set sizes using a spinner
        Spinner spinner = findViewById(R.id.ha_sizes);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item);
        assert sizes != null;
        adapter.add("Sizes:");
        for (String size : sizes) {
            adapter.add(size);
        }

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
