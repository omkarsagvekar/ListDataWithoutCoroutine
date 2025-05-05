package com.example.recyclerviewkotlinexample.apiInterface

import com.example.recyclerviewkotlinexample.data.Users
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("users")
    fun getUserList(): Call<List<Users>>
}