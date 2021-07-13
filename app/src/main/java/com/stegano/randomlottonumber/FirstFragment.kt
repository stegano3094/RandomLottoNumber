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
import kotlinx.android.synthetic.main.fragment_first.*
import okhttp3.*
import org.jsoup.Jsoup
import java.io.IOException

private const val TAG = "FirstFragment"
private const val SHOWLOG = false

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        textView8.text = "afsdf"

//        val url = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=970"
//        val client = OkHttpClient()
//        val request = Request.Builder().url(url).build()
//
//        client.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                Log.e(TAG, "onFailure e : ${e.printStackTrace()}")
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                val receiveData = response.body?.string()  // json을 String 형식으로 받아옴
//                activity?.runOnUiThread {  // UI 변경은 Ui스레드를 이용해서 변경함
//                    textView8.text = receiveData
//                }
//
//                Log.i(TAG, "onResponse : $receiveData")
//            }
//        })


        // retrofit2 참고 출처 : https://realapril.tistory.com/39
        // https://github.com/realapril/kotlin-retrofit-rxjava
        beginSearch("cat")


        // Thread, Jsoup으로 로또 최신회차 가져오기
        GetRecentLottoNum()
    }

    // retrofit2, rxjava2 --------------------------------------------------------------------------
    fun GetRecentLottoNum() {
        Thread(Runnable {
            val url = "https://www.dhlottery.co.kr/common.do?method=main"
            val doc = Jsoup.connect(url)
                .timeout(1000 * 10)  // 타임아웃 10초
                .get()  // GET
            val title = doc.title()

            Log.d(TAG, "onActivityCreated: -----------------------------------")
            Log.d(TAG, "onActivityCreated: title: $title")

//            val lottoDrwNo = doc.select("div h3 a strong")
            val lottoDrwNo = doc.select("strong[id=lottoDrwNo]").text()  // 최신회차 텍스트만 가져옴, 태그 제거
            Log.d(TAG, "onActivityCreated: lottoDrwNo: $lottoDrwNo")
        }).start()
    }


    // retrofit2, rxjava2 --------------------------------------------------------------------------
    val wikiApiServe by lazy {
        GetApiService.create()
    }

    var disposable: Disposable? = null

    private fun beginSearch(srsearch: String) {
        disposable =
            wikiApiServe.hitCountCheck("query", "json", "search", srsearch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result -> showResult(result.query.searchinfo.totalhits) },
                    { error -> showError(error.message) }
                )
    }

    private fun showResult(totalhits: Int){
        Log.d("Result hits: ", totalhits.toString())
        textView8.text = totalhits.toString()
    }

    private fun showError(message: String?){
        Log.d("Error Msg:", "$message")
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}