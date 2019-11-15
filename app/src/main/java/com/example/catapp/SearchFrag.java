package com.example.catapp;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFrag extends Fragment {

    private RecyclerView recyclerView;
    private ImageView search;
    private TextView catID;
    private String searchID;
    private String url;
    CatAdapter catAdapter;

    public SearchFrag() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerView = view.findViewById(R.id.rv_search);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        catAdapter = new CatAdapter();
        final RequestQueue requestQueue =  Volley.newRequestQueue(getContext());

        catID = view.findViewById(R.id.cat_search);
        search = view.findViewById(R.id.search_button);



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchID = catID.getText().toString();
                url = "https://api.thecatapi.com/v1/breeds/search?q="+searchID;

                InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                mgr.hideSoftInputFromWindow(catID.getWindowToken(), 0);

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();

                        Cat[] catsResponse = gson.fromJson(response, Cat[].class);
                        List<Cat> catsToAdapt = Arrays.asList(catsResponse);

                        catAdapter.setData(catsToAdapt);
                        recyclerView.setAdapter(catAdapter);

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

            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity parent = (MainActivity) getActivity();
    }

}
