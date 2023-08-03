package com.example.doordelivery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)
        val back = findViewById<Button>(R.id.btnBack)
        back.setOnClickListener{
            finish()
        }
    }
}