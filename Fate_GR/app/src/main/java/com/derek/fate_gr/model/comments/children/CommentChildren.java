package com.derek.fate_gr.model.comments.children;

import com.derek.fate_gr.model.children.ChildData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Michael on 7/29/2017.
 */

public class CommentChildren {

    @SerializedName("data")
    @Expose
    private Comment comments;

    public Comment getComments() {
        return comments;
    }

    public void setComments(Comment comments) {
        this.comments = comments;
    }
}
