package com.example.hatshop.model;

import org.json.JSONArray;
import org.json.JSONObject;

public class Hat {

    private int id;
    private String title;
    private String price;
    private String description;
    private String imageURL;
    private JSONArray sizes;
    private JSONObject seller;

    public Hat() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public JSONArray getSizes() {
        return sizes;
    }

    public JSONObject getSeller() {
        return seller;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(int price) {
        this.price = currencyParser(price);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setSizes(JSONArray sizes) {
        this.sizes = sizes;
    }

    public void setSeller(JSONObject seller) {
        this.seller = seller;
    }

    // I'm not familiar with Java's int -> currency formatting methods,so I built a basic one here.
    private static String currencyParser(int rawPrice) {

        // get cents
        int iCents = rawPrice % 100;
        String sCents;

        if (iCents == 0) {
            sCents = "00";
        } else {
            sCents = String.valueOf(iCents);
        }

        // get dollars
        int iDollars = rawPrice / 100;
        String sDollars = String.valueOf(iDollars);

        // build currency string
        return "$" + sDollars + "." + sCents;
    }
}
