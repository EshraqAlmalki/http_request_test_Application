package com.example.httprequesttestapplication

import android.util.Log
import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "Repo"

class Repo {

    var person = BMIResponse()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://body-mass-index-bmi-calculator.p.rapidapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(Api::class.java)




        fun calculateBMI( weight:String, height:String) {

             api.calculateBMI(weight, height).enqueue(object : Callback<BMIResponse> {
                override fun onResponse(call: Call<BMIResponse>, response: Response<BMIResponse>) {


                    if (response.isSuccessful) {
                        Log.d(TAG, "onResponse: ${response.body()}")
                        person = response.body()!!
                        Log.d(TAG, "onResponse:this is person $person")

                    } else {
                        Log.d(TAG, "onResponse: not successful")
                    }

                }

                override fun onFailure(call: Call<BMIResponse>, t: Throwable) {
                    Log.d(TAG, "onFailure: no response")
                }

            })


        }



}