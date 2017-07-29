package com.derek.fate_gr.model.comments.children.reply;

import com.derek.fate_gr.model.comments.children.CommentChildren;
import com.derek.fate_gr.model.comments.children.reply.children.ReplyChildren;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Michael on 7/29/2017.
 */

public class ReplyData {

    @SerializedName("children")
    @Expose
    private ArrayList<ReplyChildren> children;

    public ArrayList<ReplyChildren> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<ReplyChildren> children) {
        this.children = children;
    }
}
