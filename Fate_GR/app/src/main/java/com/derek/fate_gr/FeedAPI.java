package com.derek.fate_gr;

import com.derek.fate_gr.model.Feed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by Michael on 7/28/2017.
 */

public interface FeedAPI {

    String BASE_URL = "https://www.reddit.com/";

    @Headers("Content-Type: application/json")
    @GET("r/grandorder/new.json")
    Call<Feed> getFeed();
}
