package com.retrafit.network

import com.retrafit.model.Users
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/users")
    fun getListUser(): Call<ArrayList<Users>>

    companion object {

        private var BASE_URL = "https://api.github.com/"

        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("")
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}