package org.jik.retrofit_project

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

class CoronaOpenApi{
    companion object {
        const val DOMAIN = "https://api.odcloud.kr/"
    }
}

interface CoronaService {
    @GET("api/15077586/v1/centers")
    fun datas(
        @Query("page") page: Int,
        @Query("perPage") perPage: Int,
        @Query("serviceKey") serviceKey: String,
    ): Call<Data>
}