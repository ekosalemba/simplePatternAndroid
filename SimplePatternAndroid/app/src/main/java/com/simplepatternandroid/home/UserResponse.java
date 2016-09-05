package com.simplepatternandroid.home;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserResponse {
    @SerializedName("name")
    private String name;

    public UserResponse() {
    }

    public UserResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "name='" + name + '\'' +
                '}';
    }
}
