package com.derek.fate_gr.model.comments;

import com.derek.fate_gr.model.children.Children;
import com.derek.fate_gr.model.comments.children.CommentChildren;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Michael on 7/29/2017.
 */

public class CommentArray {

    @SerializedName("array")
    @Expose
    private ArrayList<CommentChildren> children;

    public ArrayList<CommentChildren> getCommentChildren() {
        return children;
    }

    public void setChildren(ArrayList<CommentChildren> children) {
        this.children = children;
    }

}
