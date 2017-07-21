package com.derek.fategr.features.news
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.derek.fategr.R
import com.derek.fategr.commons.extensions.inflate
import kotlinx.android.synthetic.main.news_fragment.*

/**
 * Created by Michael on 7/20/2017.
 */
class NewsFragment : Fragment(){

    private val newsList  by lazy{
        news_list
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        return container?.inflate(R.layout.news_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?){
        super.onActivityCreated(savedInstanceState)

        news_list.setHasFixedSize(true)
        news_list.layoutManager = LinearLayoutManager(context)
    }
}
