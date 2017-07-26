package com.derek.fategr.commons

import android.support.v4.app.Fragment
import rx.subscriptions.CompositeSubscription

/**
 * Created by Michael on 7/26/2017.
 */

open class RxBaseFragment(): Fragment(){
    //CompositeSubscription object is provided by RxJava and allows you to unsubscribe all
    // the subscriptions that is has with just one mehtod call
    protected var subscriptions = CompositeSubscription()

    override fun onResume(){
        super.onResume()
        subscriptions = CompositeSubscription()
    }

    override fun onPause(){
        super.onPause()
        if(!subscriptions.isUnsubscribed){
            subscriptions.unsubscribe()
        }
        subscriptions.clear()
    }
}