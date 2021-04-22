package com.tbhutiyal.itemcalculator

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.ActivityCompat

class Payment : AppCompatActivity() {
    lateinit var sp:SharedPreferences
    lateinit var txtPayment: TextView
    lateinit var txtTotal: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        //It will get shared information from adapter class to payment activity
        sp = getSharedPreferences(getString(R.string.shared_prefrence), MODE_PRIVATE)
        //Initializing variables
        txtPayment=findViewById(R.id.txtPayment)
        txtTotal=findViewById(R.id.txtTotal)
        //showing total on the screen
        txtPayment.text=sp.getInt("total",0).toString()

    }

   //on pressing back this function will happen
    override fun onBackPressed() {
        //this will put the value of total =0
        sp.edit().putInt("total",0).commit()
       //on pressing back you will move to dashboard activity
        val intent=Intent(this,Dashboard::class.java)
        startActivity(intent)


    }
}