package com.fernando.spacex.extension

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.fernando.spacex.R
import com.google.android.material.snackbar.Snackbar

fun View.snackBar(@StringRes stringRef: Int, isWarning: Boolean = false) {
    val snackBar = Snackbar.make(this, stringRef, Snackbar.LENGTH_LONG)

    if (isWarning)
        snackBar.view.setBackgroundColor(ContextCompat.getColor(this.context, R.color.app_red))
    else
        snackBar.view.setBackgroundColor(ContextCompat.getColor(this.context, R.color.app_blue))

    snackBar.show()
}

fun Context.createLoadingPopup(): AlertDialog {
    val builder = AlertDialog.Builder(this)
    val view: View = inflate(R.layout.dialog_loading)

    val text = view.findViewById<TextView>(R.id.tv_dialog_msg)
    text.setText(R.string.loading)
    builder.setView(view)
    builder.setCancelable(false)
    val dialog = builder.create()
    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    return dialog
}


