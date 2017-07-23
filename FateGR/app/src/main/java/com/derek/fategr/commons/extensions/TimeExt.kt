@file:JvmName("TimeUtils")

package com.derek.fategr.commons.extensions

import java.util.*
/**
 * Created by Michael on 7/22/2017.
 */

fun Long.getFriendly(): String{
    val dateTime = Date(this * 1000)
    val sb = StringBuffer()
    val current = Calendar.getInstance().time
    var diffInSeconds = ((current.time - dateTime.time)/1000).toInt()

    val sec = if (diffInSeconds >= 60){
        (diffInSeconds % 60).toInt()
    } else{
        diffInSeconds.toInt()
    }
    diffInSeconds = diffInSeconds / 60

    val min = if (diffInSeconds >= 60){
        (diffInSeconds % 60).toInt()
    } else{
        diffInSeconds.toInt()
    }
    diffInSeconds = diffInSeconds / 60

    val hrs = if (diffInSeconds >= 24){
        (diffInSeconds % 24).toInt()
    } else{
        diffInSeconds.toInt()
    }
    diffInSeconds = diffInSeconds / 24

    val days = if (diffInSeconds >= 30){
        (diffInSeconds % 24).toInt()
    } else{
        diffInSeconds.toInt()
    }
    diffInSeconds = diffInSeconds / 30

    var months = if (diffInSeconds >= 12){
        (diffInSeconds % 12).toInt()
    } else{
        diffInSeconds.toInt()
    }
    diffInSeconds = diffInSeconds / 12

    val years = diffInSeconds.toInt()

    if(years > 0){
        if(years == 1){
            sb.append("a year")
        } else {
            sb.append("$years years")
        }
        if(years <= 6 && months > 0){
            if(months == 1){
                sb.append(" and a month")
            } else{
                sb.append(" and $months months")
            }
        }
    }
}