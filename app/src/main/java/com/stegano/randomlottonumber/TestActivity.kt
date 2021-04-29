package com.stegano.randomlottonumber

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        findViewById<View>(R.id.button).setOnClickListener {
            // 명시적 Intent 사용 (기본 구성 요소들의 이름을 정확히 알고 있을 때 사용)
            val intent = Intent(this@TestActivity, MainActivity::class.java)
            startActivity(intent)
        }

        findViewById<View>(R.id.button2).setOnClickListener {
            val intent = Intent(this@TestActivity, ConstellationActivity::class.java)
            startActivity(intent)
        }

        findViewById<View>(R.id.button3).setOnClickListener {
            val intent = Intent(this@TestActivity, NameActivity::class.java)
            startActivity(intent)
        }

        findViewById<View>(R.id.button4).setOnClickListener {
            val intent = Intent(this@TestActivity, ResultActivity::class.java)
            startActivity(intent)
        }

        // 암시적 Intent 사용 (다른 곳으로 작업을 요청하여 처리)
       findViewById<View>(R.id.button5).setOnClickListener {
           /**
            * 작업 설명(Action)과 Uri(Data)를 전달하면서 안드로이드 시스템이 Intent를
            * 브라우저에서 처리해야 한다는 것을 알게되고 Intent를 처리할 수 있는 다른 앱을
            * 실행하여 데이터를 전달하고 보여주게 된다.
            */
           val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.naver.com"))
           startActivity(intent)
        }
    }
}