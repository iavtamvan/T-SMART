package com.iav.id.ituteam.model.newsModel;

import com.google.gson.annotations.SerializedName;

public class Source {

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private Object id;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getId() {
        return id;
    }
}