package com.stegano.randomlottonumber

object Models{
    // 아래 데이터 클래스들을 각각 kt파일로 만들어도 되지만 객체가 늘어났을 때 보기 어려움
    data class Result(val query: Query)
    data class Query(val searchinfo: SearchInfo)
    data class SearchInfo(val totalhits: Int)
}