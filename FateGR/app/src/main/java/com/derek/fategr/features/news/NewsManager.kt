package com.derek.fategr.features.news

import com.derek.fategr.commons.RedditNewsItem
import rx.Observable

/**
 * Created by Michael on 7/26/2017.
 */

class NewsManager(){

    fun getNews(): Observable<List<RedditNewsItem>>{
        return Observable.create{
            subscriber ->

            val news = mutableListOf<RedditNewsItem>()
            for(i in 1..10){
                news.add(RedditNewsItem(
                        "author$i",
                        "Title $i",
                        i,
                        145207701L - (i * 200),
                        "http://lorempixel.com/200/200/technics/$i",
                        "url"
                        ))
            }
            subscriber.onNext(news)
        }
    }
}
