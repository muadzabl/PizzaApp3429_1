package com.example.weather3429

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private fun getWeather(city: String, callback:(String) -> Unit){
        val apiKey = "eab8bce7d2c294de801476b5ffe4658a"
        val url =  "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$apiKey"

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val  client = OkHttpClient()
                val request = Request.Builder().url(url).build()
                val response = client.newCall(request).execute()
                val body = response.body?.string()

                if (body != null) {
                    val json = JSONObject(body)
                    if (json.getInt("cod") != 200) {
                        callback("Error: ${json.getString("message")}")
                        return@launch
                    }

                    val temp = json.getJSONObject("main").getDouble("temp")
                    val weatherDesc = json.getJSONArray("weather").getJSONObject(0).getString("description")
                    val humidity = json.getJSONObject("main").getInt("humidity")

                    callback("City: $city\nTemperature: $temp°C\nWeather: $weatherDesc\nHumidity: $humidity%")
                } else {
                    callback("Failed to get data")
                }
            } catch (e: Exception) {
                callback("Error: ${e.message}")
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val inputCity = findViewById<EditText>(R.id.inputcity)
        val btnGetWeather = findViewById<Button>(R.id.GetWeather)
        val resultText = findViewById<TextView>(R.id.resultText)

        btnGetWeather.setOnClickListener {
            val city = inputCity.text.toString()
            getWeather(city) { result ->
                runOnUiThread {
                    resultText.text = result
                }
            }
        }
    }
}