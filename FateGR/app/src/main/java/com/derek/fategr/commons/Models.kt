package com.derek.fategr.commons

import com.derek.fategr.commons.adapter.AdapterConstants
import com.derek.fategr.commons.adapter.ViewType

/**
 * Created by Michael on 7/21/2017.
 */

data class RedditNewsItem(
        val author: String,
        val title: String,
        val numComments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String
): ViewType {
    override fun getViewType() = AdapterConstants.NEWS
}