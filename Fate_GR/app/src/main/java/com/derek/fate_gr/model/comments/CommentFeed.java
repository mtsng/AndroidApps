package com.derek.fate_gr.model.comments;

import com.derek.fate_gr.model.Data;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Michael on 7/29/2017.
 */

public class CommentFeed {

    @SerializedName("data")
    @Expose
    private CommentData data;

    public CommentData getCommentData() {
        return data;
    }

    public void setCommentData(CommentData data) {
        this.data = data;
    }
}
