package com.example.tt_bssd5250_hw52

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    companion object {
        const val COLOR_RESULT: String = "com.example.MultipleActivities.COLOR_RESULT"
    }

    private lateinit var color1: TextView
    private lateinit var color2: TextView
    private lateinit var color3: TextView

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data

                val returnColor_1 = intent?.getStringExtra("returnColor_1").toString()
                val returnColor_2 = intent?.getStringExtra("returnColor_2").toString()
                val returnColor_3 = intent?.getStringExtra("returnColor_3").toString()

                Log.d("MactResult_1", returnColor_1)
                Log.d("MactResult_2", returnColor_2)
                Log.d("MactResult_3", returnColor_3)


                color1.setText(returnColor_1)
                color2.setText(returnColor_2)
                color3.setText(returnColor_3)
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        color1 = EditText(this).apply {
            hint = "Enter Hex Color without #"
        }
        color2 = EditText(this).apply {
            hint = "Enter Hex Color without #"
        }
        color3 = EditText(this).apply {
            hint = "Enter Hex Color without #"
        }

        val submitButton = Button(this).apply {
            text = "Submit"
            setOnClickListener {
                val passableData = Intent(applicationContext, ColorActivity::class.java).apply {
                    putExtra(ColorActivity.COLOUR_REQUESTED_1, "#" + color1.text.toString())
                    putExtra(ColorActivity.COLOUR_REQUESTED_2, "#" + color2.text.toString())
                    putExtra(ColorActivity.COLOUR_REQUESTED_3, "#" + color3.text.toString())
                }
                startForResult.launch(passableData)
            }
        }

        val linearLayout = LinearLayoutCompat(this).apply {
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.MATCH_PARENT
            )
            orientation = LinearLayoutCompat.VERTICAL
            addView(color1)
            addView(color2)
            addView(color3)
            addView(submitButton)
        }

        findViewById<ConstraintLayout>(R.id.main_layout).apply {
            setBackgroundColor(Color.GRAY)
            addView(linearLayout)
        }
    }
}