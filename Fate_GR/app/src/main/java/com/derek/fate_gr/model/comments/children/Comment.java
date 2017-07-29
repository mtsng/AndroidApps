package com.derek.fate_gr.model.comments.children;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Michael on 7/29/2017.
 */

public class Comment {

    @SerializedName("author")
    @Expose
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
