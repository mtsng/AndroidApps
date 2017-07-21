package com.derek.fategr.features.news.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.derek.fategr.R
import com.derek.fategr.commons.adapter.ViewType
import com.derek.fategr.commons.extensions.inflate

/**
 * Created by Michael on 7/20/2017.
 */

class LoadingDelegateAdapter: ViewTypeDelegateAdapter{
    override fun onCreateViewHolder(parent: ViewGroup) = TurnsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType){

    }

    class LoadingViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
        parent.inflate(R.layout.news_item_loading)){
    }
}