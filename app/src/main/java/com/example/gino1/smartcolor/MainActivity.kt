package com.example.gino1.smartcolor

import android.graphics.Color
import android.graphics.PorterDuff
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.madrapps.pikolo.listeners.SimpleColorSelectionListener
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()

    private val JSON = MediaType.parse("application/json; charset=utf-8")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        colorPicker_main.setColorSelectionListener(object: SimpleColorSelectionListener() {
            override fun onColorSelected(color: Int) {
                val hexColor = String.format("#%06X", 0xFFFFFF and color)
//                println("selected $hexColor")
                sendSingleColorData()
            }
        })
    }

    fun sendSingleColorData() {
        val powerModel = LEDPower(false)
        val jsonObject = Gson().toJson(powerModel)

        val requestBody = RequestBody.create(JSON, jsonObject)

        val request = Request.Builder()
                .url("http://192.168.0.52:3000/api/ledpower")
                .post(requestBody)
                .build()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                println(e?.message)
            }

            override fun onResponse(call: Call?, response: Response?) {
                println(response)
            }
        })

    }
}
