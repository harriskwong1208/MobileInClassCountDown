package com.example.inclassactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    lateinit var TextView1: TextView
    lateinit var button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TextView1 = findViewById(R.id.TextView1)
        button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val scope = CoroutineScope(Job() + Dispatchers.Default)
            scope.launch {
                countdownTimer()
            }
        }
    }
    suspend fun countdownTimer() {
        repeat(100) {
            (100 -it).toString().run{
                Log.d("Countdown",this)
                withContext(Dispatchers.Main) {
                    TextView1.text = this@run
                }
            }
            delay(1000)
        }
    }
}