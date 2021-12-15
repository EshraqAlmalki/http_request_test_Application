package com.example.httprequesttestapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "OurFragment"

class OurFragment : Fragment() {

    private lateinit var bmiTv : TextView
    private lateinit var weightTv : TextView
    private lateinit var heightTv : TextView

    private lateinit var weightEt: EditText
    private lateinit var heightEt: EditText



    private var bmiResponse = BMIResponse()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_our, container, false)

        bmiTv = view.findViewById(R.id.bmi)
        weightTv = view.findViewById(R.id.weight)
        heightTv = view.findViewById(R.id.height)
        weightEt = view.findViewById(R.id.weight_et)
        heightEt = view.findViewById(R.id.height_et)


         val retrofit = Retrofit.Builder()
            .baseUrl("https://body-mass-index-bmi-calculator.p.rapidapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

         val api = retrofit.create(Api::class.java)

        api.calculateBMI()
            .enqueue(object : Callback<BMIResponse> {
                override fun onResponse(call: Call<BMIResponse>, response: Response<BMIResponse>) {


                    if (response.isSuccessful) {

                        weightTv.text = response.body()!!.weight
                        heightTv.text = response.body()!!.height
                        bmiTv.text = response.body()!!.bmi

                    } else {
                        Log.d(TAG, "onResponse: not successful")
                    }

                }

                override fun onFailure(call: Call<BMIResponse>, t: Throwable) {
                    Log.d(TAG, "onFailure: no response")


                }

            })


        return view
    }


}
