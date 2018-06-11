package com.example.gino1.smartcolor

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.madrapps.pikolo.listeners.SimpleColorSelectionListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        colorPicker_main.setColorSelectionListener(object: SimpleColorSelectionListener() {
            override fun onColorSelected(color: Int) {
                println("color $color")

            }
        })
    }

    fun sendSingleColorData() {

    }
}
