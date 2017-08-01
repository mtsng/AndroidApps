package com.derek.fate_gr.model.children;

import com.derek.fate_gr.model.children.image.Preview;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Michael on 7/28/2017.
 */

public class ChildData {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("selftext")
    @Expose
    private String selftext;

    @SerializedName("link_flair_text")
    @Expose
    private String flair_text;

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("permalink")
    @Expose
    private String permalink;

    @SerializedName("created")
    @Expose
    private String created_date;

   /* @SerializedName("preview")
    @Expose
    private Preview preview; */

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSelftext() {
        return selftext;
    }

    public void setSelftext(String selftext) {
        this.selftext = selftext;
    }

    public String getFlair_text() {
        return flair_text;
    }

    public void setFlair_text(String flair_text) {
        this.flair_text = flair_text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ChildData{" +
                "title='" + title + '\'' +
                ", selftext='" + selftext + '\'' +
                ", flair_text='" + flair_text + '\'' +
                ", author='" + author + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", url='" + url + '\'' +
                ", permalink='" + permalink + '\'' +
                ", created_data='" + created_date + '\'' +
                '}';
    }
}
