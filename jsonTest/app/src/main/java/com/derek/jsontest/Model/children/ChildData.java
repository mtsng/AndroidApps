package com.derek.jsontest.Model.children;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Michael on 7/26/2017.
 */

public class ChildData {

    @SerializedName("subreddit")
    @Expose
    private String subreddit;

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("link_flair_text")
    @Expose
    private String flair;

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFlair() {
        return flair;
    }

    public void setFlair(String flair) {
        this.flair = flair;
    }

    @Override
    public String toString() {
        return "Data{" +
                "subreddit='" + subreddit + '\'' +
                ", author='" + author + '\'' +
                ", flair='" + flair + '\'' +
                '}';
    }
}
