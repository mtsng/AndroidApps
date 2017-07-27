package com.derek.fategr.api

/**
 * Created by Michael on 7/26/2017.
 */

//Data classes not used b/c we did not need all the extra methods that a data class provides
// (e.g. copy, hash toString, etc)
class RedditNewsResponse(val data: RedditDataResponse)

class RedditDataResponse(
        val children: List<RedditChildrenResponse>,
        val after: String?,
        val before: String?
)

class RedditChildrenResponse(val data: RedditNewsDataResponse)

class RedditNewsDataResponse(
        val author: String,
        val title: String,
        val num_comments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String
)