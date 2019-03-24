package com.varol.hepsi.extension

import java.util.*


fun Date.getRemaininTimeAsString(): String {

    val rightNow = Calendar.getInstance()

    var diffInMilliSeconds = this.time.minus(rightNow.time.time)

    val secondsInMilli: Long = 1000
    val minutesInMilli = secondsInMilli * 60
    val hoursInMilli = minutesInMilli * 60
    val daysInMilli = hoursInMilli * 24

    val elapsedDays = diffInMilliSeconds / daysInMilli
    diffInMilliSeconds %= daysInMilli

    val elapsedHours = diffInMilliSeconds / hoursInMilli
    diffInMilliSeconds %= hoursInMilli

    val elapsedMinutes = diffInMilliSeconds / minutesInMilli
    diffInMilliSeconds %= minutesInMilli

    val elapsedSeconds = diffInMilliSeconds / secondsInMilli

    var remainingText = ""

    if (elapsedDays > 0) remainingText += ("$elapsedDays days,")
    if (elapsedHours > 0) remainingText += ("$elapsedHours hours,")
    if (elapsedMinutes > 0) remainingText += ("$elapsedMinutes minutes,")
    if (elapsedSeconds > 0) remainingText += ("$elapsedSeconds seconds")

    return if (remainingText.isEmpty()) {
        "Deal Expired!"
    } else {
        remainingText.plus(" remaining")
    }


}