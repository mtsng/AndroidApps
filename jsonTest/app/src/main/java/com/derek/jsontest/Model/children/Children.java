package com.derek.jsontest.Model.children;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Michael on 7/26/2017.
 */

public class Children {

    @SerializedName("data")
    @Expose
    private ChildData data;

    @SerializedName("kind")
    @Expose
    private String kind;

    public ChildData getData() {
        return data;
    }

    public String getKind() {
        return kind;
    }
}
