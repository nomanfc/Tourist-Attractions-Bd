package com.tourism.app.models;

import java.io.Serializable;

public class PlaceListModel implements Serializable {
    String address, division, img_url, img_url1,img_url2,img_url3,name,description,type,cord1,cord2,rating;

    public PlaceListModel() {
        address = "";
        division = "";
        img_url = "";
        img_url1 = "";
        img_url2 ="";
        img_url3 = "";
        name="";
        description = "";
        type = "";
        cord1 = "";
        cord2 = "";
        rating="";
    }

    public PlaceListModel(String address, String division, String img_url, String img_url1, String img_url2, String img_url3, String name, String description, String type, String cord1, String cord2, String rating) {
        this.address = address;
        this.division = division;
        this.img_url = img_url;
        this.img_url1 = img_url1;
        this.img_url2 = img_url2;
        this.img_url3 = img_url3;
        this.name = name;
        this.description = description;
        this.type = type;
        this.cord1 = cord1;
        this.cord2 = cord2;
        this.rating = rating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getImg_url1() {
        return img_url1;
    }

    public void setImg_url1(String img_url1) {
        this.img_url1 = img_url1;
    }

    public String getImg_url2() {
        return img_url2;
    }

    public void setImg_url2(String img_url2) {
        this.img_url2 = img_url2;
    }

    public String getImg_url3() {
        return img_url3;
    }

    public void setImg_url3(String img_url3) {
        this.img_url3 = img_url3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCord1() {
        return cord1;
    }

    public void setCord1(String cord1) {
        this.cord1 = cord1;
    }

    public String getCord2() {
        return cord2;
    }

    public void setCord2(String cord2) {
        this.cord2 = cord2;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
