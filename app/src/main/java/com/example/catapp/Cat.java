package com.example.catapp;

import androidx.room.Entity;
import androidx.room.Ignore;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class Cat implements Serializable{


    @SerializedName("id")
    private String catID;
    @SerializedName("name")
    private String catName;
    @SerializedName("description")
    private String catDescription;
    @SerializedName("temperament")
    private String catTemp;
    @SerializedName("origin")
    private String catOrigin;
    @SerializedName("life_span")
    private String catLifeSpan;
    @SerializedName("dog_friendly")
    private int catDogLvl;
    @SerializedName("wikipedia_url")
    private String wikiURL;
    @Ignore
    @SerializedName("weight")
    private Weight catWeight;

    private Boolean isFavourited = false;


    public Cat(){

    }

    public Cat(String catID, String catName, String catDescription, Weight catWeight, String catTemp, String catOrigin,
               String catLifeSpan, int catDogLvl, String wikiURL) {
        this.catID = catID;
        this.catName = catName;
        this.catDescription = catDescription;
        this.catWeight = catWeight;
        this.catTemp = catTemp;
        this.catOrigin = catOrigin;
        this.catLifeSpan = catLifeSpan;
        this.catDogLvl = catDogLvl;
        this.wikiURL = wikiURL;


    }

    public String getCatID() {
        return catID;
    }

    public void setCatID(String catID) {
        this.catID = catID;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatDescription() {
        return catDescription;
    }

    public void setCatDescription(String catDescription) {
        this.catDescription = catDescription;
    }

    public String getCatTemp() {
        return catTemp;
    }

    public void setCatTemp(String catTemp) {
        this.catTemp = catTemp;
    }

    public String getCatOrigin() {
        return catOrigin;
    }

    public void setCatOrigin(String catOrigin) {
        this.catOrigin = catOrigin;
    }

    public String getCatLifeSpan() {
        return catLifeSpan;
    }

    public void setCatLifeSpan(String catLifeSpan) {
        this.catLifeSpan = catLifeSpan;
    }

    public int getCatDogLvl() {
        return catDogLvl;
    }

    public void setCatDogLvl(int catDogLvl) {
        this.catDogLvl = catDogLvl;
    }

    public String getWikiURL() {
        return wikiURL;
    }

    public void setWikiURL(String wikiURL) {
        this.wikiURL = wikiURL;
    }

    public Weight getCatWeight() {
        return catWeight;
    }

    public Boolean getFavourited() {
        return isFavourited;
    }

    public void setFavourited(Boolean favourited) {
        isFavourited = favourited;
    }

    public class Weight implements Serializable {
        private String imperial;
        private String metric;

        public String getImperial() {
            return imperial;
        }

        public void setImperial(String imperial) {
            this.imperial = imperial;
        }

        public String getMetric() {
            return metric;
        }

        public void setMetric(String metric) {
            this.metric = metric;
        }
    }
}