package com.hpb.thecatapp.retrofit

import com.hpb.thecatapp.models.CatModel
import com.hpb.thecatapp.models.CatModelItem
import retrofit2.http.GET
import retrofit2.http.Query

interface CatAPI {

    @GET("images/search?limit=100&page=11&order=Desc")
    suspend fun getCats(@Query("page") page: Int): ArrayList<CatModelItem>

}