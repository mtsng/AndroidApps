package com.derek.fategr.features.news
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.derek.fategr.R
import com.derek.fategr.commons.RedditNewsItem
import com.derek.fategr.commons.extensions.inflate
import com.derek.fategr.features.news.adapter.NewsAdapter
import kotlinx.android.synthetic.main.news_fragment.*

/**
 * Created by Michael on 7/20/2017.
 */
class NewsFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        return container?.inflate(R.layout.news_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?){
        super.onActivityCreated(savedInstanceState)

        news_list.setHasFixedSize(true)
        news_list.layoutManager = LinearLayoutManager(context)

        initAdapter()

        if(savedInstanceState == null){
            //a mutable list can be modified
            val news = mutableListOf<RedditNewsItem>()
            //this returns an IntRange; it extends from IntProgression and this implements Iterable
            //decremental 10 downTo 1
            for(i in 1..10){
                news.add(RedditNewsItem(
                        "author$i",
                        "Title $i",
                        i, //number of comments
                        1457207701L - (i * 200),
                        "https://i.imgur.com/tGbaZCY.jpg", //image url
                        "url"
                ))
            }
            (news_list.adapter as NewsAdapter).addNews(news)
        }
    }

    private fun initAdapter(){
        if(news_list.adapter == null){
            news_list.adapter = NewsAdapter()
        }
    }
}
