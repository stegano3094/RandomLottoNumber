package com.stegano.randomlottonumber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<CardView>(R.id.randomCard).setOnClickListener {
            val intent = Intent(this@MainActivity, ResultActivity::class.java)
            startActivity(intent)
        }

        findViewById<CardView>(R.id.constellationCard).setOnClickListener {
            val intent = Intent(this@MainActivity, ConstellationActivity::class.java)
            startActivity(intent)
        }

        findViewById<CardView>(R.id.nameCard).setOnClickListener {
            val intent = Intent(this@MainActivity, NameActivity::class.java)
            startActivity(intent)
        }
    }
}