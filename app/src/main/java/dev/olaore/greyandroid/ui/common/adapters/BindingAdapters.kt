package dev.olaore.greyandroid.ui.common.adapters

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.core.content.ContextCompat
import dev.olaore.greyandroid.R

fun TextView.addOwnerSpan() {
    val spannable = SpannableString(this.text)

    val slashIndex = text.indexOf("/") + 1
    val repositoryNameLength = text.split("/")[1].length

    spannable.setSpan(
        ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorPurple)),
        0,
        slashIndex,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    spannable.setSpan(
        StyleSpan(Typeface.BOLD),
        slashIndex,
        slashIndex + repositoryNameLength,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    this.text = spannable
}
