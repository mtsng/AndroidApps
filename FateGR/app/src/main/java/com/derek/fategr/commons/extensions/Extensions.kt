@file:JvmName("ExtensionUtils")

package com.derek.fategr.commons.extensions

import android.view.View
import android.view.ViewGroup

/**
 * Created by Michael on 7/20/2017.
 */

//This adds a new method to ViewGroup, but not modifying the ViewGroup class
//Internally, it is a static method
//it's like a regular class method, accesses instance of the class with "this' and access to local variable "context"
//You can define default values in parameters with Kotlin
    fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
        return android.view.LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
    }