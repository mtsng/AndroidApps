package com.derek.fate_gr.model.comments.children.reply.children;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Michael on 7/29/2017.
 */

public class ReplyContent {

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("parent_id")
    @Expose
    private String parent_id;

    @SerializedName("body")
    @Expose
    private String body;

    @SerializedName("depth")
    @Expose
    private String depth;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }
}
