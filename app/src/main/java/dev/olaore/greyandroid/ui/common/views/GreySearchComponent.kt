package dev.olaore.greyandroid.ui.common.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doAfterTextChanged
import dev.olaore.greyandroid.R
import java.lang.Exception

class GreySearchComponent @JvmOverloads constructor(
    private val ctx: Context,
    private val attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(ctx, attributeSet, defStyleAttr, defStyleRes) {

    private val searchInput: EditText
    private val searchButton: Button

    init {
        val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.component_grey_search, this)

        layoutParams = LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ctx.resources.getDimension(R.dimen.component_search_height).toInt()
        )

        searchInput = findViewById(R.id.search_input)
        searchButton = findViewById(R.id.search_button)

        initialize()

    }

    private fun initialize() {
        setBackgroundResource(R.drawable.bg_border_grey)
        try {
            val typedArray = ctx.obtainStyledAttributes(attributeSet, R.styleable.GreySearchComponent)
            val placeholder = typedArray.getString(R.styleable.GreySearchComponent_inputPlaceholder).toString()

            searchInput.hint = placeholder

            typedArray.recycle()
        } catch (ex: Exception) {
            searchInput.hint = "Please enter something"
        }

        searchInput.doAfterTextChanged {
            val text = it.toString()
            setBackgroundResource(if (text.isEmpty()) {
                R.drawable.bg_border_grey
            } else {
                R.drawable.bg_border_black
            })
        }
    }

    fun setOnSearchClickListener(action: (String) -> Unit) {
        searchButton.setOnClickListener {
            val query = searchInput.text.toString()
            if (query.isNotEmpty()) {
                action.invoke(query)
            } else {
                Toast.makeText(ctx, "Please enter something in the input box", Toast.LENGTH_LONG).show()
            }
        }
    }

}
