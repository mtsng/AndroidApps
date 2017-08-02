package com.derek.fate_gr;

import com.derek.fate_gr.model.Feed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Michael on 8/1/2017.
 */

public interface PaginationFeedAPI {

    String BASE_URL = "https://www.reddit.com/";

    @Headers("Content-Type: application/json")
    @GET("r/grandorder/new.json")
    Call<Feed> getPaginationFeed(@Query("count") int Count, @Query("after") String after_id);
}
