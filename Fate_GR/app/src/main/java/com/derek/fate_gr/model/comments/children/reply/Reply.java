package com.derek.fate_gr.model.comments.children.reply;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Michael on 7/29/2017.
 */

public class Reply {

    @SerializedName("data")
    @Expose
    private ReplyData children;

    public ReplyData getChildren() {
        return children;
    }

    public void setChildren(ReplyData children) {
        this.children = children;
    }
}
