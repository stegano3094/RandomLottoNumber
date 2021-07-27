package com.stegano.randomlottonumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "TestAcitivity"
        private val fragment1 = FirstFragment()
        private val fragment2 = SecondFragment()
        private val fragment3 = ThirdFragment()
    }

    private var logShowState: Boolean = true  // 로그를 보여줄지 결정하는 변수

    var backButtonPressedTime: Long = 0L  // 뒤로가기 버튼 클릭 시 시간 저장하는 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)  // 화면 켜짐 유지
        supportFragmentManager.beginTransaction().replace(R.id.mainFrameLayout, fragment1).commit()  // 첫 화면(컨텐츠영역)에서 프래그먼트1 화면을 보여줌

        if(logShowState) Log.d(TAG, "onCreate")

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.page_1 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.mainFrameLayout, fragment1).commit()
                    // Respond to navigation item 1 click
                    true
                }
                R.id.page_2 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.mainFrameLayout, fragment2).commit()
                    // Respond to navigation item 2 click
                    true
                }
                R.id.page_3 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.mainFrameLayout, fragment3).commit()
                    // Respond to navigation item 2 click
                    true
                }
                else -> false
            }
        }
    }

    override fun onBackPressed() {
        val currentTime = System.currentTimeMillis()

        if((currentTime - backButtonPressedTime) <= 2000) {  // 짧은 시간동안 2번의 뒤로가기 버튼 클릭 시 앱 종료함
            finish()
            return
        }
        backButtonPressedTime = System.currentTimeMillis()
        Toast.makeText(this, "'뒤로가기' 버튼을 한 번 더 눌러주세요.", Toast.LENGTH_SHORT).show()
//        super.onBackPressed()
    }
}