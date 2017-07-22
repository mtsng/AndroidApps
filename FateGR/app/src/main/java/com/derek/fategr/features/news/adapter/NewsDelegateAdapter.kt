package com.derek.fategr.features.news.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.derek.fategr.commons.RedditNewsItem
import com.derek.fategr.commons.adapter.ViewType
import com.derek.fategr.commons.adapter.ViewTypeDelegateAdapter
import kotlinx.android.synthetic.main.news_item.view.*

/**
 * Created by Michael on 7/21/2017.
 */

class NewsDelegateAdapter: ViewTypeDelegateAdapter{

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder{
        return TurnsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as TurnsViewHolder
        holder.bind(item as RedditNewsItem)
    }

    class TurnsViewHolder(parent: ViewGroup): RecyclerView.ViewHolder{
        parent.inflate(R.layout.news_item)){
            fun bind(item: RedditNewsItem) = with(itemView){
                img_thumbnail.loadImg(item.thumbnail)
                description.text = item.title
                author.text = item.author
                comments.text = "${item.numComments} comments"
                time.text = item.created.getFriendlyTime()
            }
        }
    }
}