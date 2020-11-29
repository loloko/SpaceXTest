package com.fernando.spacex.extension


import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import com.fernando.spacex.R
import java.text.DecimalFormat

val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if (tag.length <= 23) tag else tag.substring(0, 23)
    }

val Int.getColorSuccessRate: Int
    get() {
        return when (this) {
            in 0..29 -> Color.RED
            in 30..59 -> Color.rgb(255, 165, 0) // Orange
            else -> Color.GREEN
        }
    }

fun Double.toPrice(currency: String): String {
    val pattern = "#,###.00"
    val decimalFormat = DecimalFormat(pattern)
    decimalFormat.groupingSize = 3

    return "$currency  ${decimalFormat.format(this)}"
}

fun Context.inflate(@LayoutRes layoutRes: Int): View {
    return LayoutInflater.from(this).inflate(layoutRes, null)
}

fun Context.isNetworkAvailable(view: View): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    if (capabilities != null) {
        when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                return true
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                return true
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                return true
            }
        }
    }

    view.snackBar(R.string.no_internet, isWarning = true)

    return false
}