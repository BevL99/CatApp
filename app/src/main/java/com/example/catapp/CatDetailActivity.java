package com.example.catapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import static com.example.catapp.MainActivity.favourites;

import java.util.Arrays;
import java.util.List;

public class CatDetailActivity extends AppCompatActivity {

    private TextView catName;
    public TextView catDesc;
    public TextView catTemp;
    public TextView catOrigin;
    public TextView catLifeSpan;
    public TextView catDogFriend;
    public TextView catWikiUrl;
    public TextView catWeight;
    private ImageView catImage;
    private ImageView favourite;
    private String url;
    private String imageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_detail);

        Intent intent = getIntent();

        catName = findViewById(R.id.name);
        catDesc = findViewById(R.id.description);
        catTemp = findViewById(R.id.tempVal);
        catOrigin = findViewById(R.id.originVal);
        catLifeSpan = findViewById(R.id.lifeSpanVal);
        catDogFriend = findViewById(R.id.dogFVal);
        catWikiUrl = findViewById(R.id.wikiURL);
        catWeight = findViewById(R.id.weightVal);
        catImage = findViewById(R.id.catImage);
        favourite = findViewById(R.id.favourite);

        final Cat cat = (Cat)intent.getSerializableExtra("cat");
        final String name = cat.getCatName();

        catName.setText(cat.getCatName());
        catDesc.setText(cat.getCatDescription());
        catTemp.setText(cat.getCatTemp());
        catOrigin.setText(cat.getCatOrigin());
        catLifeSpan.setText(cat.getCatLifeSpan());
        catDogFriend.setText(String.valueOf(cat.getCatDogLvl()));
        catWikiUrl.setText(cat.getWikiURL());
        catWeight.setText(cat.getCatWeight().getImperial());

        final String catID = cat.getCatID();
        final String wikiURL = cat.getWikiURL();

        if(cat.getFavourited()) {
            favourite.setImageResource(R.drawable.favourited);
        }
        else {
            favourite.setImageResource(R.drawable.not_favourited);
        }
            favourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (cat.getFavourited()) {
                        favourite.setImageResource(R.drawable.not_favourited);
                        cat.setFavourited(false);
                        favourites.remove(cat);
                        Toast removed = Toast.makeText(getApplicationContext(),
                                name + " removed from Favourites", Toast.LENGTH_SHORT);
                        removed.show();
                    } else {
                        favourite.setImageResource(R.drawable.favourited);
                        cat.setFavourited(true);
                        favourites.add(cat);
                        Toast added = Toast.makeText(getApplicationContext(),
                                name + " added to Favourites", Toast.LENGTH_SHORT);
                        added.show();
                    }
                }
            });

        final RequestQueue requestQueue =  Volley.newRequestQueue(getApplicationContext());

        url = "https://api.thecatapi.com/v1/images/search?breed_ids="+catID;
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();

                CatImages[] catImageResponse = gson.fromJson(response, CatImages[].class);
                List<CatImages> catImages = Arrays.asList(catImageResponse);

                CatImages specificCat = catImages.get(0);
                imageURL = specificCat.getUrl();

                Glide.with(getApplicationContext()).load(imageURL).into(catImage);

                requestQueue.stop();
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestQueue.stop();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);

        requestQueue.add(stringRequest);

        catWikiUrl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (wikiURL.startsWith("https://") || wikiURL.startsWith("http://")) {
                    Uri uri = Uri.parse(wikiURL);
                    Intent intent2 = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent2);
                }else{
                    Toast.makeText(getApplicationContext(), "Invalid Url", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}