package com.tourism.app.models;

import java.io.Serializable;

public class DivisionModel implements Serializable {
    String name, img_url;

    public DivisionModel() {
    }

    public DivisionModel(String name, String img_url) {
        this.name = name;
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
