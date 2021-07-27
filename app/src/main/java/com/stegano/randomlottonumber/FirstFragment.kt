package com.stegano.randomlottonumber

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_first_win_result.*
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import org.jsoup.Jsoup
import java.io.IOException

private const val TAG = "FirstFragment"
private const val SHOWLOG = true  // 로그를 보여줄지 플래그, true = 보여줌, false = 안보여줌

//var recentLottoNum: Int = 0  // 로또 최신회차를 담는 변수
//var getLottoNumFlag: Boolean = false  // 로또 번호를 가져왔는지 확인하는 플래그, 앱 첫 시작시에만 가져오도록 함


val lotto645 = Get645Lotto()

class FirstFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // retrofit2 참고 출처 : https://realapril.tistory.com/39
        // https://github.com/realapril/kotlin-retrofit-rxjava
        //beginSearch("cat")

        // Thread, Jsoup으로 로또 최신회차 가져오기
//        GetRecentLottoNum()
        lotto645.GetRecentLottoNum()

        if (SHOWLOG) {
            Log.d(TAG, "onActivityCreated: lotto645.GetNum() : ${lotto645.GetNum()}")
        }
    }

//    // 로또 회차를 인자로 받아 그 회차의 로또 데이터를 보여주는 코드, OkHttp3 --------------------------------
//    fun GetLottoData(lottoNum: Int) {
//        val url = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=$lottoNum"
//        val client = OkHttpClient()
//        val request = Request.Builder().url(url).build()
//
//        client.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                if (SHOWLOG) {
//                    Log.e(TAG, "onFailure e : ${e.printStackTrace()}")
//                }
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                val receiveData = response.body?.string()  // json을 String 형식으로 받아옴
//                if (SHOWLOG) {
//                    Log.i(TAG, "onResponse : $receiveData")
//                }
//
//                try {
//                    val returnValue = JSONObject(receiveData).getString("returnValue")  // success, fail
//                    val drwNoDate = JSONObject(receiveData).getString("drwNoDate")  // 추첨한 날짜
//                    val drwtNo = mutableListOf<Int>()
//                    drwtNo.add(JSONObject(receiveData).getInt("drwtNo1"))  // 1번
//                    drwtNo.add(JSONObject(receiveData).getInt("drwtNo2"))  // 2번
//                    drwtNo.add(JSONObject(receiveData).getInt("drwtNo3"))  // 3번
//                    drwtNo.add(JSONObject(receiveData).getInt("drwtNo4"))  // 4번
//                    drwtNo.add(JSONObject(receiveData).getInt("drwtNo5"))  // 5번
//                    drwtNo.add(JSONObject(receiveData).getInt("drwtNo6"))  // 6번
//                    drwtNo.add(JSONObject(receiveData).getInt("bnusNo"))  // 보너스 번호
//
//                    ChangeUI(returnValue, lottoNum, drwNoDate, drwtNo)  // UI에 적용시키기기
//                } catch (e: JSONException) {
//                    e.printStackTrace()
//                }
//
//            }
//        })
//    }
//
//    fun ChangeUI(returnValue: String, lottoNum: Int, drwNoDate: String, drwtNo: MutableList<Int>) {
//        activity?.runOnUiThread {  // UI 변경은 Ui스레드를 이용해서 변경함
//            when(returnValue) {
//                "success" -> {
//                    textView9.text = "${lottoNum}회 당첨결과"
//                    textView8.text = "(${drwNoDate} 추첨)"
//                    setBallNumberAndBackground(textView10, drwtNo[0])
//                    setBallNumberAndBackground(textView11, drwtNo[1])
//                    setBallNumberAndBackground(textView12, drwtNo[2])
//                    setBallNumberAndBackground(textView13, drwtNo[3])
//                    setBallNumberAndBackground(textView14, drwtNo[4])
//                    setBallNumberAndBackground(textView15, drwtNo[5])
//                    setBallNumberAndBackground(textView16, drwtNo[6])
//                }
//                "fail" -> {
//                    textView9.text = "잘못된 참조입니다."
//                }
//            }
//        }
//    }
//
//    fun setBallNumberAndBackground(view: TextView, number: Int) {  // 당첨 번호에 백그라운드 이미지를 세팅함
//        view.text = number.toString()  // 텍스트뷰에 당첨 번호를 넣음
//        when (number) {  // 텍스트뷰의 백그라운드에 색상의 공을 이미지를 넣음
//            in 1..10 -> view.background = resources.getDrawable(R.drawable.ball_yellow, null)
//            in 11..20 -> view.background = resources.getDrawable(R.drawable.ball_blue, null)
//            in 21..30 -> view.background = resources.getDrawable(R.drawable.ball_red, null)
//            in 31..40 -> view.background = resources.getDrawable(R.drawable.ball_grey, null)
//            else -> {
//                view.background = resources.getDrawable(R.drawable.ball_green, null)
//            }
//        }
//    }

    // retrofit2, rxjava2 --------------------------------------------------------------------------
//    val wikiApiServe by lazy {
//        GetApiService.create()
//    }
//
//    var disposable: Disposable? = null
//
//    private fun beginSearch(srsearch: String) {
//        disposable =
//            wikiApiServe.hitCountCheck("query", "json", "search", srsearch)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                    { result -> showResult(result.query.searchinfo.totalhits) },
//                    { error -> showError(error.message) }
//                )
//    }
//
//    private fun showResult(totalhits: Int){
//        Log.d("Result hits: ", totalhits.toString())
////        textView8.text = totalhits.toString()
//    }
//
//    private fun showError(message: String?){
//        Log.d("Error Msg:", "$message")
//    }

    override fun onPause() {
        super.onPause()
//        disposable?.dispose()
    }
}