package com.derek.jsontest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import com.derek.jsontest.Model.Feed;


/**
 * Created by Michael on 7/26/2017.
 */

public interface redditAPI {

    //Retrofit does not allow the entire url all at once
    String BASE_URL = "https://www.reddit.com/";

    //header are a parameter you add to urls
    @Headers("Content-Type: application/json")
    @GET("r/grandorder/new.json")
    Call<Feed> getData();
}
