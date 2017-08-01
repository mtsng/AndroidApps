package com.derek.fate_gr.model.comments.children;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Michael on 7/29/2017.
 */

public class Comment {

    @SerializedName("author")
    @Expose
    private String author;

 /*   @SerializedName("replies")
    @Expose
    private Reply replies; */

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("body")
    @Expose
    private String body;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
