package com.derek.fategr.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Michael on 7/26/2017.
 */

interface RedditApi{
    //Defined a synchronous API that will receive the "after and "limit" query string
    //Call class will allow us to execute the request and know if the request was successful or not
    //Also extract the response data with given generic type
    @GET("r/grandorder/new.json")
    fun getNew(@Query("after") after: String, @Query("limit") limit: String): Call<RedditNewsResponse>
}