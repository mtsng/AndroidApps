package com.derek.fate_gr.model.comments.children.reply.children;

import com.derek.fate_gr.model.comments.children.Comment;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Michael on 7/29/2017.
 */

public class ReplyChildren {

    @SerializedName("data")
    @Expose
    private ReplyContent content;

    public ReplyContent getContent() {
        return content;
    }

    public void setContent(ReplyContent content) {
        this.content = content;
    }
}
