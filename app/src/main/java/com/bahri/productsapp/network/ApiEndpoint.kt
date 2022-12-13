package com.bahri.productsapp.network

import com.bahri.productsapp.model.ResponseList
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndpoint {
    @GET("products")
    fun getList(): Call<ResponseList>
}