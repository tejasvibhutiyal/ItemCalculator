package com.tbhutiyal.itemcalculator

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tbhutiyal.itemcalculator.adapter.DashboardRecyclerAdapter
import com.tbhutiyal.itemcalculator.database.ListEntity
import com.tbhutiyal.itemcalculator.model.listItems

class Dashboard : AppCompatActivity() {
    lateinit var recyclerDashboard: RecyclerView
    lateinit var recyclerAdapter: DashboardRecyclerAdapter
    lateinit var layoutmagaer: RecyclerView.LayoutManager
    lateinit var toolbar: Toolbar
    lateinit var btnPayment: Button
    lateinit var sp: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        //Here initializing the variables
        sp=getSharedPreferences(getString(R.string.shared_prefrence), MODE_PRIVATE)
        sp.edit().putInt("total",0).commit()
        recyclerDashboard=findViewById(R.id.recyclerDashboard)
        layoutmagaer= LinearLayoutManager(this)

        //setting the toolbar
        toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title="Dashboard"
        btnPayment=findViewById(R.id.btnPayment)
        //adding the items of the list
        val itemlist= arrayListOf<listItems>(
            listItems(100,"Tomato",50,"gram"),
            listItems(101,"Onion",10,"gram"),
            listItems(102,"Milk",5,"gram"),
            listItems(103,"Sauce",20,"gram"),
            listItems(104,"Noodles",10,"gram"),
            listItems(105,"Pulses",20,"gram")
        )
        //adding adapter class
        recyclerAdapter=DashboardRecyclerAdapter(this,itemlist)
        recyclerDashboard.adapter=recyclerAdapter
        recyclerDashboard.layoutManager=layoutmagaer
        recyclerDashboard.addItemDecoration(DividerItemDecoration(recyclerDashboard.context,(layoutmagaer as LinearLayoutManager).orientation))
        //setting the button
        btnPayment.setOnClickListener {
            //moving to other activity
            val intent1= Intent(this,Payment::class.java)
            startActivity(intent1)
            ActivityCompat.finishAffinity(this)

        }



    }
//back pressed function
    override fun onBackPressed() {
        //it will finish the app
        ActivityCompat.finishAffinity(this)


    }



}

