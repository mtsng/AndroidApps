package com.derek.fategr.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Michael on 7/26/2017.
 */

class RestAPI(){
    //val are kind of like final variables (i.e. final int num)
    private val redditApi: RedditApi


    init{
        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.reddit.com/")
                //Setting the Moshi converter
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        //gets the Java Class instance corresponding to the given KClass instance
        //A KClas is an inteface that represents a class and provides introspection functions
        redditApi = retrofit.create(RedditApi::class.java)
    }

    fun getNews(after: String, limit: String): Call<RedditNewsResponse> {
        return redditApi.getNew(after, limit)
    }
}