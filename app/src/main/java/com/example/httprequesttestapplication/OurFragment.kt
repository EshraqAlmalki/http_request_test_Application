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

private const val TAG = "OurFragment"

class OurFragment : Fragment() {

    private lateinit var bmiTv : TextView
    private lateinit var weightTv : TextView
    private lateinit var heightTv : TextView

    private lateinit var weightEt: EditText
    private lateinit var heightEt: EditText

    private val repo = Repo()

    private val bmiResponse = BMIResponse()

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

        bmiResponse.weight = weightEt.text.toString()
        bmiResponse.height = heightEt.text.toString()


        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //nothing here
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                bmiResponse.height = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
// nothing
            }

        }

        val textWatcherw = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //nothing here
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                bmiResponse.weight = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
// nothing
            }

        }

        heightEt.addTextChangedListener(textWatcher)
        weightEt.addTextChangedListener(textWatcherw)

        Log.d(TAG, "onCreateView: height and weight ${bmiResponse.height} , ${bmiResponse.weight}")
        repo.calculateBMI("89","1.75")

        Log.d(TAG, "onCreateView: this person ${repo.person}")

        bmiTv.text = repo.person.bmi
        weightTv.text = repo.person.weight
        heightTv.text = repo.person.height

        Log.d(TAG, "onCreateView: ${bmiTv.text}")

        return view
    }


}
