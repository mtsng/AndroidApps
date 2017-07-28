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

    public ArrayList<Children> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Children> children) {
        this.children = children;
    }


    @Override
    public String toString() {
        return "Data{" +
                "children=" + children +
                '}';
    }
}
