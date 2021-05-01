package com.stegano.randomlottonumber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class NameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        val editTextName = findViewById<EditText>(R.id.editTextName)

        findViewById<Button>(R.id.goButton).setOnClickListener {
            if(TextUtils.isEmpty(editTextName.text.toString())) {
                Toast.makeText(this, "이름을 입력하세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, ResultActivity::class.java)
            intent.putIntegerArrayListExtra("result", ArrayList(LottoNumberMake.getLottoNumbersFromHash(editTextName.text.toString())) )
            intent.putExtra("name", editTextName.text.toString())
            startActivity(intent)
            finish()
        }

        findViewById<Button>(R.id.backButton).setOnClickListener {
            finish()
        }
    }
}