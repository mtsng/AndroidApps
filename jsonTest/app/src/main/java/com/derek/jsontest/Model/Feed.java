package com.derek.jsontest.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Michael on 7/26/2017.
 */

public class Feed {
    @SerializedName("kind")
    @Expose
    private String kind;

    @SerializedName("data")
    @Expose
    private Data data;

    public String getKind() {
        return kind;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }
}
