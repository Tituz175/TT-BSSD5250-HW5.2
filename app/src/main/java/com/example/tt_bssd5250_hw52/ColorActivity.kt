package com.example.tt_bssd5250_hw52

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout

class ColorActivity : AppCompatActivity() {

    companion object {
        const val COLOUR_REQUESTED_1: String = "com.example.MultipleActivities.COLOUR_REQUESTED_1"
        const val COLOUR_REQUESTED_2: String = "com.example.MultipleActivities.COLOUR_REQUESTED_2"
        const val COLOUR_REQUESTED_3: String = "com.example.MultipleActivities.COLOUR_REQUESTED_3"
    }

    private var col1: Int = 0
    private var col2: Int = 0
    private var col3: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color)

        val color1 = View(this).apply {
            id = View.generateViewId()
            layoutParams = LinearLayoutCompat.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                0.1f
            )
            try {
                col1 = Color.parseColor(intent.getStringExtra(COLOUR_REQUESTED_1))
                setBackgroundColor(col1)
            } catch (e: NumberFormatException) {
                setBackgroundColor(Color.WHITE)
            }

            setOnClickListener {
                col1 += 10
                it.setBackgroundColor(col1)
            }

        }
        val color2 = View(this).apply {
            layoutParams = LinearLayoutCompat.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                0.1f
            )
            try {
                col2 = Color.parseColor(intent.getStringExtra(COLOUR_REQUESTED_2))
                setBackgroundColor(col2)
            } catch (e: NumberFormatException) {
                setBackgroundColor(Color.WHITE)
            }

            setOnClickListener {
                col2 += 10
                it.setBackgroundColor(col2)
            }

        }
        val color3 = View(this).apply {
            layoutParams = LinearLayoutCompat.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                0.1f
            )
            try {
                col3 = Color.parseColor(intent.getStringExtra(COLOUR_REQUESTED_3))
                setBackgroundColor(col3)
            } catch (e: NumberFormatException) {
                setBackgroundColor(Color.WHITE)
            }

            setOnClickListener {
                col3 += 10
                it.setBackgroundColor(col3)
            }

        }
        val linearLayoutCompat = LinearLayoutCompat(this).apply {
            setBackgroundColor(Color.LTGRAY)

            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.MATCH_PARENT
            )
            orientation = LinearLayoutCompat.VERTICAL

            addView(color1)
            addView(color2)
            addView(color3)
        }

        val getIntentDataButton = Button(this).apply {
            text = "Save"
            setOnClickListener {

                var hexVal1 = "%X".format(col1)
                var hexValC1 = hexVal1.subSequence(2, hexVal1.length).toString()

                var hexVal2 = "%X".format(col2)
                var hexValC2 = hexVal2.subSequence(2, hexVal2.length).toString()

                var hexVal3 = "%X".format(col3)
                var hexValC3 = hexVal3.subSequence(2, hexVal3.length).toString()

                val passableData = Intent().apply {
                    putExtra("returnColor_1", hexValC1)
                    putExtra("returnColor_2", hexValC2)
                    putExtra("returnColor_3", hexValC3)
                }
                setResult(RESULT_OK, passableData)
                finish()
            }
        }

        findViewById<ConstraintLayout>(R.id.color_layout).apply {
            addView(linearLayoutCompat)
            addView(getIntentDataButton)
        }

    }
}