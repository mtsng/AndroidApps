package com.derek.fate_gr.model;

import com.derek.fate_gr.model.children.Children;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Michael on 7/28/2017.
 */

public class Data {

    @SerializedName("children")
    @Expose
    private ArrayList<Children> children;

    @SerializedName("after")
    @Expose
    private String after_id;

    public ArrayList<Children> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Children> children) {
        this.children = children;
    }

    public String getAfter_id() {
        return after_id;
    }

    public void setAfter_id(String after_id) {
        this.after_id = after_id;
    }

    @Override
    public String toString() {
        return "Data{" +
                "children=" + children +
                '}';
    }
}
