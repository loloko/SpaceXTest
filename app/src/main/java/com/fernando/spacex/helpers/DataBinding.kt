package com.fernando.spacex.helpers

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.api.load
import com.fernando.spacex.R
import com.fernando.spacex.extension.getColorSuccessRate
import com.fernando.spacex.extension.toPrice


@BindingAdapter(value = ["setImageUrl"])
fun ImageView.setImageUrl(imageList: List<String>?) {
    load(imageList?.get(0)) {
        error(R.drawable.image_not_found)
        placeholder(R.drawable.ic_loading_image)
        crossfade(true)
    }
}

@BindingAdapter(value = ["setRateText"])
fun TextView.setRateText(rate: Int) {
    text = "$rate".plus("%")
}

@BindingAdapter(value = ["convertAmount"])
fun TextView.convertAmount(amount: Int) {
    text = amount.toDouble().toPrice()
}

@BindingAdapter(value = ["successRateColor"])
fun TextView.successRateColor(rate: Int) {
    setTextColor(rate.getColorSuccessRate)
}

@BindingAdapter(value = ["setStatusColor"])
fun TextView.setStatusColor(status: Boolean) {
    if (status)
        setTextColor(Color.GREEN)
    else
        setTextColor(Color.RED)
}
