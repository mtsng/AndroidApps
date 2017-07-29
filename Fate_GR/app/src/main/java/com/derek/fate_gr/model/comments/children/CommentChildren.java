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
    private Comments comments;

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }
}
