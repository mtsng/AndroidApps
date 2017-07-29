package com.derek.fate_gr.model.comments;

import com.derek.fate_gr.model.Feed;

import org.w3c.dom.Comment;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by Michael on 7/29/2017.
 */

public interface CommentFeedAPI {

        String BASE_URL = "https://www.reddit.com/";

        @Headers("Content-Type: application/json")
        @GET(BASE_URL + "{permalink}/.json")
        Call<CommentFeed> getFeed(@Path("permalink") String permalink);

}
