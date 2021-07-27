package com.stegano.randomlottonumber

import android.util.Log
import org.jsoup.Jsoup

class Get645Lotto {
    private var TAG: String = "Get645Lotto"
    private var SHOWLOG: Boolean = true

    companion object {
        var getLottoNumFlag: Boolean = false
        var recentLottoNum: Int = 0
    }

    // 로또의 최근 회차를 가져오는 코드 Jsoup -----------------------------------------------------------
    fun GetRecentLottoNum() {
        if(getLottoNumFlag == false) {
            Thread(Runnable {
                val url = "https://www.dhlottery.co.kr/common.do?method=main"  // 동행복권 사이트 주소
                val doc = Jsoup.connect(url)
                    .timeout(1000 * 10)  // 타임아웃 10초
                    .get()  // GET

                getLottoNumFlag = true  // 로또 번호를 가져왔을 때 한 번 더 안가져오게 플래그를 true로 지정함
                val title = doc.title()

                val lottoDrwNo = doc.select("strong#lottoDrwNo").text()  // 최신회차 텍스트만 가져옴, 태그 제거
                recentLottoNum = lottoDrwNo.toInt()

                if (SHOWLOG) {
                    Log.d(TAG, "onActivityCreated: -----------------------------------")
                    Log.d(TAG, "onActivityCreated: getLottoNumFlag: $getLottoNumFlag")
                    Log.d(TAG, "onActivityCreated: title: $title")
                    Log.d(TAG, "onActivityCreated: lottoDrwNo: $lottoDrwNo")
                }
            }).start()
        }
    }

    fun GetNum() : Int {
        return recentLottoNum
    }
}