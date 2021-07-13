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
            val intent = Intent(this, ResultActivity::class.java)
            intent.putIntegerArrayListExtra("result", ArrayList(LottoNumberMake.getRandomLottoNumbers()))  // 난수 데이터를 포함시킴
            startActivity(intent)
        }

        findViewById<CardView>(R.id.constellationCard).setOnClickListener {
            val intent = Intent(this, ConstellationActivity::class.java)
            startActivity(intent)
        }

        findViewById<CardView>(R.id.nameCard).setOnClickListener {
            val intent = Intent(this@MainActivity, NameActivity::class.java)
            startActivity(intent)
        }


        // 테스트용으로 바로 진입함 (나중에 메인으로 쓸 화면임)
        val intent = Intent(this, TestActivity::class.java)
        startActivity(intent)
        finish()
    }
}