package com.tbhutiyal.itemcalculator

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.room.Room
import com.tbhutiyal.itemcalculator.database.Database
import com.tbhutiyal.itemcalculator.database.RegisterEntity
import com.tbhutiyal.itemcalculator.model.Register

class MainActivity : AppCompatActivity() {
    lateinit var txtPhone: TextView
    lateinit var etPhone: EditText
    lateinit var imgLog: ImageView
    lateinit var txtPassword: TextView
    lateinit var etPassword: EditText
    lateinit var btnLogin: Button
    lateinit var txtName: TextView
    lateinit var etName: EditText
    lateinit var txtConfirmPassword: TextView
    lateinit var etConfirmPassword: EditText
    lateinit var sp: SharedPreferences
    var userid = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initializing the variables
        txtPhone = findViewById(R.id.txtPhone)
        txtPassword = findViewById(R.id.txtPassword)
        etPhone = findViewById(R.id.etPhone)
        etPassword = findViewById(R.id.etPassword)
        imgLog = findViewById(R.id.imgLog)
        btnLogin = findViewById(R.id.btnLogin)
        txtName = findViewById(R.id.txtName)
        etName = findViewById(R.id.etName)
        txtConfirmPassword = findViewById(R.id.txtConfirmPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        sp = getSharedPreferences(getString(R.string.shared_prefrence), MODE_PRIVATE)

        //Setting on click listener
        if(sp.getBoolean("isLoggedIn",false)){
            sp.edit().putInt("Total",0).apply()
            val intent= Intent(this,Dashboard::class.java)
            startActivity(intent)
        }
        else{ btnLogin.setOnClickListener {
            //Check edit text Confirm password is equal to Edit text password
            if ((etConfirmPassword.text.toString()) == (etPassword.text.toString())) {
                //Checking the mobile number is of length 10 or not
                if (etPhone.text.toString().length == 10) {
                    //giving user a user id
                    userid += 1
                    //adding the user information into register variable
                    val register = Register(
                        userid,
                        etPhone.text.toString(),
                        etName.text.toString(),
                        etPassword.text.toString()
                    )
                    //adding the register entity
                    val registerEntity = RegisterEntity(
                        register.user_id,
                        register.mobile,
                        register.name,
                        register.password
                    )
                    //performing database operation on other thread
                    val thread = Thread {
                        //insertion in database
                        var db= Room.databaseBuilder(this, Database::class.java,"register_db").build()
                        db.dao().insertRegister(registerEntity)

                        //fetch all Records and display it to logcat
                        db.dao().getallRegister().forEach()
                        {
                            Log.i("Fetch Records", "Id:  : ${it.user_id}")
                            Log.i("Fetch Records", "Name:  : ${it.name}")
                            Log.i("Fetch records","Password: ${it.password}")
                            Log.i("Fetch records","Mobile: ${it.mobile}")
                        }
                    }
                    thread.start()

                    //saving the user information in saveprefrence
                    saveprefrences()
                    sp.edit().putInt("user_id", userid).apply()
                    sp.edit().putString("name", etName.text.toString()).apply()
                    sp.edit().putString("phone", etPhone.text.toString()).apply()
                    sp.edit().putString("password", etPassword.text.toString()).apply()

                    //going to next activity
                    val intent= Intent(this,Dashboard::class.java)
                    startActivity(intent)

                } else {
                    etPhone.error = "Invalid Phone number"
                }
            } else {
                etConfirmPassword.error = "Password doesn't match"
            }
        }}

    }

    //function to save register preference
    fun saveprefrences() {
        sp.edit().putBoolean("isLoggedIn", true).apply()
    }

    //on back pressed this activity will get finished
    override fun onBackPressed() {
        ActivityCompat.finishAffinity(this)
    }

}