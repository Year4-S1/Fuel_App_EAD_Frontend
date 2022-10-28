package com.example.ead_assignment.model;
import com.google.gson.annotations.SerializedName;

public class SearchQuery {

    @SerializedName("StationName")
    public String query;

    public SearchQuery(String query) {
        this.query = query;
    }
}
