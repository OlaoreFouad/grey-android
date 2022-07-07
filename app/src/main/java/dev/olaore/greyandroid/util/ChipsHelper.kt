package dev.olaore.greyandroid.util

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dev.olaore.greyandroid.R

class ChipsHelper(
    private val ctx: Context,
    private val group: ChipGroup
) {

    private val typeface = Typeface.create(
        ResourcesCompat.getFont(
        ctx, R.font.manrope_bold
    ), Typeface.BOLD)

    fun createChip(text: String): Chip {
        val c = LayoutInflater.from(ctx).inflate(
            R.layout.item_topic_chip, group, false
        ) as Chip

        c.text = text
        c.typeface = typeface

        return c
    }

}
