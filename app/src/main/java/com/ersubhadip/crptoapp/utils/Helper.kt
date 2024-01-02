package com.ersubhadip.crptoapp.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.pow
import kotlin.math.roundToInt

//Utility to format the millis time to hh:mm a format
fun formatMillisToTime(millis: Long): String {
    val dateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
    val date = Date(millis)
    return dateFormat.format(date)
}

//Utility to round the double to specified place
fun Double.roundToXDecimalPlaces(x: Double): Double {
    val factor = 10.0.pow(x)
    return (this * factor).roundToInt() / factor
}