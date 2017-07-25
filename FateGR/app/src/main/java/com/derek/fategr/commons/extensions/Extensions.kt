@file:JvmName("ExtensionUtils")

package com.derek.fategr.commons.extensions

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.derek.fategr.R
import com.squareup.picasso.Picasso

/**
 * Created by Michael on 7/20/2017.
 */

//This adds a new method to ViewGroup, but not modifying the ViewGroup class
//Internally, it is a static method
//it's like a regular class method, accesses instance of the class with "this' and access to local variable "context"
//You can define default values in parameters with Kotlin
    fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
    }
    fun ImageView.loadImg(imageUrl: String){
        if(TextUtils.isEmpty(imageUrl)){
            Picasso.with(context).load(R.mipmap.ic_launcher).into(this)
        }
        else{
            Picasso.with(context).load(imageUrl).into(this)
        }
    }