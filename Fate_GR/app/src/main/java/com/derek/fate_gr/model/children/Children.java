package com.derek.fate_gr.model.children;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Michael on 7/28/2017.
 */

public class Children {

    @SerializedName("data")
    @Expose
    private ChildData data;

    public ChildData getData() {
        return data;
    }

    public void setData(ChildData data) {
        this.data = data;
    }
}
