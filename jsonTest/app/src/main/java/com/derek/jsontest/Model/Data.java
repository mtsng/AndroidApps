package com.derek.jsontest.Model;

import com.derek.jsontest.Model.children.Children;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Michael on 7/26/2017.
 */

public class Data {

    @SerializedName("modhash")
    @Expose
    private String modhash;

    @SerializedName("children")
    @Expose
    private ArrayList<Children> children;

    public String getModhash() {
        return modhash;
    }

    public void setModhash(String modhash) {
        this.modhash = modhash;
    }

    public ArrayList<Children> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Children> children) {
        this.children = children;
    }

    @Override
    public String toString(){
        return "Data{" + "modhash='" + modhash + '\'' + ", children=" + children + "}";
    }
}
