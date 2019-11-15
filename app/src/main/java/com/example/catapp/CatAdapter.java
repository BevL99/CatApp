package com.example.catapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {
    private List<Cat> catsToAdapt;

    public void setData(List<Cat> catsToAdapt) {
        this.catsToAdapt = catsToAdapt;
    }

    @NonNull
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cat_basic, parent, false);

        CatViewHolder CatViewHolder = new CatViewHolder(view);
        return CatViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        final Cat catAtPosition = catsToAdapt.get(position);

        holder.catName.setText(catAtPosition.getCatName());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();

                Intent intent = new Intent(context, CatDetailActivity.class);
                intent.putExtra("cat", catAtPosition);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return catsToAdapt.size();
    }

    public static class CatViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView catName;



        public CatViewHolder(View v) {
            super(v);
            view = v;
            catName = v.findViewById(R.id.name);
        }
    }
}
