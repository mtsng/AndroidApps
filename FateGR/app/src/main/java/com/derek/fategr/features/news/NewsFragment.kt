package com.derek.fategr.features.news
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.derek.fategr.R
import com.derek.fategr.commons.RxBaseFragment
import com.derek.fategr.commons.extensions.inflate
import com.derek.fategr.features.news.adapter.NewsAdapter
import kotlinx.android.synthetic.main.news_fragment.*
import rx.schedulers.Schedulers
import rx.android.schedulers.AndroidSchedulers

/**
 * Created by Michael on 7/20/2017.
 */
class NewsFragment : RxBaseFragment(){

    //this will initiailze the NewsManager() only the first time that we use newManagers field
    private val newsManager by lazy{NewsManager()}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        return container?.inflate(R.layout.news_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?){
        super.onActivityCreated(savedInstanceState)

        news_list.setHasFixedSize(true)
        news_list.layoutManager = LinearLayoutManager(context)

        initAdapter()

        if(savedInstanceState == null){
            requestNews()
        }
    }

    //returns a subscription object
    private fun requestNews(){
        val subscription = newsManager.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        //sets NewsAdapter with the received news
                        {retrievedNews ->
                            (news_list.adapter as NewsAdapter).addNews(retrievedNews)
                        },
                        //shows error message
                        {e ->
                            Snackbar.make(news_list, e.message?: "", Snackbar.LENGTH_LONG).show()
                        }
                )
        subscriptions.add(subscription)
    }

    private fun initAdapter(){
        if(news_list.adapter == null){
            news_list.adapter = NewsAdapter()
        }
    }
}
