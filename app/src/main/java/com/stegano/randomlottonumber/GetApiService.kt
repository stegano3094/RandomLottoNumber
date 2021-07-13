package com.stegano.randomlottonumber

import com.google.gson.GsonBuilder
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GetApiService {
    @GET("api.php")
    fun hitCountCheck(@Query("action") action: String,
                      @Query("format") format: String,
                      @Query("list") list: String,
                      @Query("srsearch") srsearch: String):
            Observable<Models.Result>
//=====Part2==================

    companion object {
        var gson = GsonBuilder()
            .setLenient()
            .create()
        fun create(): GetApiService {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())

                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://en.wikipedia.org/w/")
                .build()
            return retrofit.create(GetApiService::class.java)
        }
    }

}