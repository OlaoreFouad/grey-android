package dev.olaore.greyandroid.ui.common.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import dev.olaore.greyandroid.R

class EmptyStateComponent @JvmOverloads constructor(
    private val ctx: Context,
    private val attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(ctx, attributeSet, defStyleAttr, defStyleRes) {

    private val emptyText: TextView
    private val emptyImage: ImageView

    init {
        val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.component_empty_state, this)

        emptyImage = findViewById(R.id.empty_image)
        emptyText = findViewById(R.id.empty_text)

        initialize()
    }

    private fun initialize() {
        try {
            val typedArray = ctx.obtainStyledAttributes(attributeSet, R.styleable.EmptyStateComponent)
            val text = typedArray.getString(R.styleable.EmptyStateComponent_emptyText).toString()
            val imageRes = typedArray.getResourceId(R.styleable.EmptyStateComponent_emptyImage, R.drawable.ic_empty_search_state)

            emptyText.text = text
            emptyImage.setImageResource(imageRes)

            typedArray.recycle()
        } catch (ex: Exception) {
            emptyText.text = ""
        }
    }

    fun setEmptyText(text: String) {
        emptyText.text = text
    }
}
