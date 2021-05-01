package com.stegano.randomlottonumber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class NameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        findViewById<Button>(R.id.goButton).setOnClickListener {
            startActivity(Intent(this, ResultActivity::class.java))
        }

        findViewById<Button>(R.id.backButton).setOnClickListener {
            finish()
        }
    }
}