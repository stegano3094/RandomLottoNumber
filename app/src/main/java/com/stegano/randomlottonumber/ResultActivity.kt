package com.stegano.randomlottonumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    val lottoImageStartId = R.drawable.ball_01

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val resultLabel = findViewById<TextView>(R.id.resultLabel)

        val result = intent.getIntegerArrayListExtra("result")
        val name = intent.getStringExtra("name")
        val constellation = intent.getStringExtra("constellation")

        if(!TextUtils.isEmpty(name)) {
            resultLabel.text = "${name} 님의\n로또번호입니다."
        } else if (!TextUtils.isEmpty(constellation)) {
            resultLabel.text = "${constellation}로 생성된\n로또번호입니다."
        } else {
            resultLabel.text = "랜덤으로 생성된\n로또번호입니다."
        }

        result?.let {
            updateLottoBallImage(result.sortedBy { it })
        }
    }

    private fun updateLottoBallImage(result: List<Int>) {
        if(result.size != 6) return  // 6개의 번호가 아닐 경우 바로 리턴

        val imageView01 = findViewById<ImageView>(R.id.imageView01)
        val imageView02 = findViewById<ImageView>(R.id.imageView02)
        val imageView03 = findViewById<ImageView>(R.id.imageView03)
        val imageView04 = findViewById<ImageView>(R.id.imageView04)
        val imageView05 = findViewById<ImageView>(R.id.imageView05)
        val imageView06 = findViewById<ImageView>(R.id.imageView06)

        imageView01.setImageResource(lottoImageStartId + (result[0] - 1) )
        imageView02.setImageResource(lottoImageStartId + (result[1] - 1) )
        imageView03.setImageResource(lottoImageStartId + (result[2] - 1) )
        imageView04.setImageResource(lottoImageStartId + (result[3] - 1) )
        imageView05.setImageResource(lottoImageStartId + (result[4] - 1) )
        imageView06.setImageResource(lottoImageStartId + (result[5] - 1) )
    }
}