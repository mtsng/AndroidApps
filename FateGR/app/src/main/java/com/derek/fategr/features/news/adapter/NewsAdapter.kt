package com.derek.fategr.features.news.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.derek.fategr.commons.RedditNewsItem
import com.derek.fategr.commons.adapter.AdapterConstants
import com.derek.fategr.commons.adapter.ViewType
import com.derek.fategr.commons.adapter.ViewTypeDelegateAdapter

/**
 * Created by Michael on 7/20/2017.
 */

class NewsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val loadingItem = object: ViewType{
        override fun getViewType() = AdapterConstants.LOADING
    }

    init{
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstants.NEWS, NewsDelegateAdapter())
        items = ArrayList()
        items.add(loadingItem)
    }

    override fun getItemCount(): Int{
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        return delegateAdapters.get(viewType).onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int){
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, this.items[position])
    }

    override fun getItemViewType(position: Int): Int{
        return this.items.get(position).getViewType()
    }

    fun addNews(news: List<RedditNewsItem>){
        //first remove loading and notify
        val initPosition = items.size - 1
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition)

        //insert news and the loading at the end of the list
        items.addAll(news)
        items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size + 1/*plus loading item */)
    }

    fun clearAndAddNews(news: List<RedditNewsItem>){
        items.clear()
        notifyItemRangeRemoved(0, getLastPosition())

        items.addAll(news)
        items.add(loadingItem)
        notifyItemRangeInserted(0, items.size)
    }

    fun getNews(): List<RedditNewsItem>{
        //filters and transforms every item from a list to another one
        //filter allows us to iterate a list and filter items that don't apply certain conditions
        //returns only news items
        //map transforms the items from a list
        //we cast a ViewType object to a RedditNewsItem
        //Lambda function, a function that is not delcared but passed immediately as an expression
        return items.filter{it.getViewType() == AdapterConstants.NEWS}.map{it as RedditNewsItem}
    }

    private fun getLastPosition() = if(items.lastIndex == -1){
        0
    } else{
        items.lastIndex
    }
}