package com.santosh.navi.util

import android.os.Build
import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object TimeManager {
    private const val DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ssX"
    private const val DATE_FORMAT = "EEE, d MMM yyyy"
    private const val TAG = "TimeManager"

    fun formatTime(input: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val inputFormat = SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.getDefault())
            val parse = try {
                inputFormat.parse(input)
            } catch (e: ParseException) {
                Log.e(TAG, e.toString())
            }
            SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(parse)
        } else {
            input
        }
    }
}
