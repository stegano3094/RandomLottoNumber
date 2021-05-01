package com.stegano.randomlottonumber

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.cardview.widget.CardView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<CardView>(R.id.randomCard).setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putIntegerArrayListExtra("result", ArrayList(getRandomLottoNumbers()))  // 난수 데이터를 포함시킴
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
    }

    // Random을 이용한 방법으로 난수 생성
    private fun getRandomLottoNumbers(): MutableList<Int> {
        val lottoNumbers = mutableListOf<Int>()

        for(i in 1..6) {
            var number = 0

            do {
                number = getRandomLottoNumber()
            } while(lottoNumbers.contains(number))  // 중복값이 없을 때까지 반복

            lottoNumbers.add(number)
        }
        return lottoNumbers
    }

    private fun getRandomLottoNumber(): Int {
        // 1~45 중 랜덤 번호를 생성
        return Random.nextInt(45) + 1  // import kotlin.random.Random
    }

    // Shuffle로 간편하게 중복없는 난수를 생성할 수도 있음
    private fun getShuffleLottoNumbers(): MutableList<Int> {
        val list = mutableListOf<Int>()
        for(number in 1..45) {
            list.add(number)  // 1~45까지 자례대로 배열에 추가
        }
        list.shuffle()  // 무작위로 섞음
        return list.subList(0, 6)  // 리스트의 앞에서부터 6개를 짤라 반환
    }
}